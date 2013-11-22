package com.cloversystem.dao.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.*;
import java.util.*;
import com.cloversystem.domain.*;
import com.cloversystem.dao.base.*;
import com.cloversystem.dao.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.*;

import java.applet.*;
import javax.servlet.http.*;
import javax.servlet.*;

import com.sun.xml.internal.ws.client.RequestContext;
import org.apache.struts2.ServletActionContext;
import org.dom4j.io.XMLWriter;
import org.springframework.web.servlet.support.RequestContext.*;

import com.cloversystem.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 04/10/2013
 * Time: 4:32:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class PermissionDaoImpl implements PermissionDao{
    private XmlOperationHelper xmlOperationHelper;
    private String xmlRealPath;

    public XmlOperationHelper getXmlOperationHelper() {
        return xmlOperationHelper;
    }

    public void setXmlOperationHelper(XmlOperationHelper xmlOperationHelper) {
        this.xmlOperationHelper = xmlOperationHelper;
    }

    public String getXmlRealPath() {
        return xmlRealPath;
    }

    public void setXmlRealPath(String xmlRealPath) {
        this.xmlRealPath = xmlRealPath;
    }

    public boolean add(Permission permission)
    {
        boolean add = false;
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);

        boolean inputTest = true;
        if(permission.getAction().equals("") && permission.getPackageName().equals(""))
        {
           inputTest = false;
        }
        if(doc != null && inputTest == true)
        {
          Element root = doc.getRootElement();

          //if action is empty so add a module
          if(permission.getAction().equals(""))
          {
              Element moduleEle = root.addElement("module");
              moduleEle.addAttribute("name", permission.getPackageName());
          }
          else
          {
              List packageList = root.elements("module");
              boolean moduleIsExisting = false;
              for (Iterator it = packageList.iterator(); it.hasNext();)
              {
                Element moduleEle = (Element) it.next();
                if(moduleEle.attributeValue("name").toString().equals(permission.getPackageName()))
                {
                  Element permissionEle =  moduleEle.addElement("permission");

                  permissionEle.addAttribute("id", permission.getId());
                  String roleStr = StringHelper.join(",",permission.getRoles());
                  permissionEle.addAttribute("roles", roleStr);

                  Element packageName = permissionEle.addElement("packageName");
                  packageName.setText(permission.getPackageName());

                  Element action = permissionEle.addElement("action");
                  action.setText(permission.getAction());

                  Element name = permissionEle.addElement("name");
                  name.setText(permission.getName());

                  moduleIsExisting = true;
                }
              }
              if(moduleIsExisting == false)
              {
                 Element moduleEle = root.addElement("module");
                 moduleEle.addAttribute("name", permission.getPackageName());

                 Element permissionEle =  moduleEle.addElement("permission");

                 permissionEle.addAttribute("id", permission.getId());
                 String roleStr = StringHelper.join(",",permission.getRoles());
                 permissionEle.addAttribute("roles", roleStr);

                 Element packageName = permissionEle.addElement("packageName");
                 packageName.setText(permission.getPackageName());

                 Element action = permissionEle.addElement("action");
                 action.setText(permission.getAction());

                 Element name = permissionEle.addElement("name");
                 name.setText(permission.getName());
              }
          }

          add = xmlOperationHelper.saveXml(doc, xmlRealPath);
        }
        return add;
    }

    public void delete(Permission permission) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean delete(String packageName, String action)
    {
        boolean delete = false;
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();

          List packageList = root.elements("module");
          for (Iterator it = packageList.iterator(); it.hasNext();)
          {
            Element moduleEle = (Element) it.next();
            if(!action.equals(""))
            {
              List permissions = moduleEle.elements("permission");
              for (Iterator p_it = permissions.iterator(); p_it.hasNext();)
              {
                Element elm = (Element) p_it.next();
                if(elm.element("packageName").getText().equals(packageName) && elm.element("action").getText().equals(action))
                {
                  moduleEle.remove(elm);
                }
              }
            }
            else
            {
                if(moduleEle.attributeValue("name").toString().equals(packageName))
                {
                   root.remove(moduleEle);
                }
            }
          }
          delete = xmlOperationHelper.saveXml(doc, xmlRealPath);
        }

        return delete;
    }

    public List<Permission> findAll()
    {
        List<Permission> permissions = new ArrayList<Permission>();
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();
          //List permissionList = root.elements("permission");

          List packageList = root.elements("module");
          for (Iterator it = packageList.iterator(); it.hasNext();)
          {
            Element package_elm = (Element) it.next();
            List permissionList = package_elm.elements("permission");
            for (Iterator pit = permissionList.iterator(); pit.hasNext();)
            {
              Element elm = (Element) pit.next();
              String[] roles = elm.attributeValue("roles").toString().split(",");
              permissions.add(new Permission(
                    elm.attributeValue("id").toString(),
                    elm.element("packageName").getText(),
                    elm.element("action").getText(),
                    elm.element("name").getText(),
                    roles
              ));
            }
          }
        }
        return permissions;
    }

    public List<String> findRolePermissionsId(String roleName){
        List<String> permissions_id = new ArrayList<String>();
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();
          //List permissionList = root.elements("permission");

          List packageList = root.elements("module");
          for (Iterator it = packageList.iterator(); it.hasNext();)
          {
            Element package_elm = (Element) it.next();
            List permissionList = package_elm.elements("permission");
            for (Iterator pit = permissionList.iterator(); pit.hasNext();)
            {
              Element elm = (Element) pit.next();
              if(elm.attributeValue("roles").contains(roleName))
              {
                 permissions_id.add(elm.attributeValue("id"));
              }
            }
          }
        }
        return permissions_id;
    }

    public List<Permission> findRolesPermissions(List<Role> roles) {
        return null; 
    }

    public List<String> findAllPackages()
    {
        List<String> packages = new ArrayList<String>();
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();
          List packageList = root.elements("module");
          for (Iterator it = packageList.iterator(); it.hasNext();)
          {
            Element elm = (Element) it.next();
            packages.add(elm.attributeValue("name").toString());
          }
        }
        return packages;
    }

    public List<PackagePermission> findAllPackagePermission()
    {
        List<PackagePermission> packagePermissions = new ArrayList<PackagePermission>();
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();

          List packageList = root.elements("module");
          for (Iterator it = packageList.iterator(); it.hasNext();)
          {
            Element package_elm = (Element) it.next();
            PackagePermission p = new PackagePermission();
            p.setPackageName(package_elm.attributeValue("name").toString());

            List permissionList = package_elm.elements("permission");

            List<Permission> permissions = new ArrayList<Permission>();
            for (Iterator pit = permissionList.iterator(); pit.hasNext();)
            {
              Element elm = (Element) pit.next();
              String[] roles = elm.attributeValue("roles").toString().split(",");
              permissions.add(new Permission(
                    elm.attributeValue("id").toString(),
                    elm.element("packageName").getText(),
                    elm.element("action").getText(),
                    elm.element("name").getText(),
                    roles
              ));
            }
            p.setPackagePermissions(permissions);  

            packagePermissions.add(p);
          }
        }
        return packagePermissions;
    }

    public boolean updateRolePermissions(String roleName, String pemissionCheckedList) {
        boolean update = false;
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();

          List packageList = root.elements("module");

          for (Iterator it = packageList.iterator(); it.hasNext();)
          {
            Element package_elm = (Element) it.next();
            List permissionList = package_elm.elements("permission");
            //add roles
            for (Iterator pit = permissionList.iterator(); pit.hasNext();)
            {
              Element elm = (Element) pit.next();

              if(pemissionCheckedList.contains(elm.attributeValue("id")))
              {
                //add roles
                if(!elm.attributeValue("roles").contains(roleName))
                {
                  if(elm.attributeValue("roles").equals(""))
                  {
                    elm.addAttribute("roles",roleName);
                  }
                  else
                  {
                    elm.addAttribute("roles",elm.attributeValue("roles") + "," + roleName);
                  }
                }
              }
              else
              {
                //delete roles
                if(elm.attributeValue("roles").contains(roleName))
                {
                  if(elm.attributeValue("roles").contains("," + roleName))
                  {
                    elm.addAttribute("roles",elm.attributeValue("roles").replace("," + roleName,""));
                  }
                  else if((elm.attributeValue("roles").contains(roleName+",")))
                  {
                    elm.addAttribute("roles",elm.attributeValue("roles").replace(roleName + ",",""));
                  }
                  else
                  {
                    elm.addAttribute("roles",elm.attributeValue("roles").replace(roleName,""));
                  }
                }
              }
            }
          }
          update = xmlOperationHelper.saveXml(doc, xmlRealPath);
        }
       return update;
    }

    public boolean deleteRoleInPermissions(String roleName)
    {
        boolean delete = false;
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();

          List packageList = root.elements("module");
          for (Iterator it = packageList.iterator(); it.hasNext();)
          {
            Element moduleEle = (Element) it.next();
            List permissions = moduleEle.elements("permission");
            for (Iterator p_it = permissions.iterator(); p_it.hasNext();)
            {
                Element elm = (Element) p_it.next();
                if(elm.attributeValue("roles").contains(roleName))
                {
                  if(elm.attributeValue("roles").contains("," + roleName))
                  {
                    elm.addAttribute("roles",elm.attributeValue("roles").replace("," + roleName,""));
                  }
                  else if((elm.attributeValue("roles").contains(roleName+",")))
                  {
                    elm.addAttribute("roles",elm.attributeValue("roles").replace(roleName + ",",""));
                  }
                  else
                  {
                    elm.addAttribute("roles",elm.attributeValue("roles").replace(roleName,""));
                  }
                }                
            }
          }
          delete = xmlOperationHelper.saveXml(doc, xmlRealPath);
        }

        return delete;
    }
}
