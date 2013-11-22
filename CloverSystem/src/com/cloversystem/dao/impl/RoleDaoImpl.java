package com.cloversystem.dao.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.*;
import java.util.*;
import com.cloversystem.domain.*;
import com.cloversystem.dao.base.*;
import com.cloversystem.dao.*;
import com.cloversystem.util.XmlOperationHelper;
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

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 04/10/2013
 * Time: 7:58:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class RoleDaoImpl implements RoleDao{
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

    public boolean add(Role role)
    {
        boolean add = false;
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();
          Element roleEle = root.addElement("item");
            roleEle.addAttribute("id", role.getName());
          Element roleName = roleEle.addElement("name");
            roleName.setText(role.getName());
          Element desciption = roleEle.addElement("desciption");
            desciption.setText(role.getDesciption());

          add = xmlOperationHelper.saveXml(doc, xmlRealPath);
        }
        return add;
    }

    public void delete(Role role) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean delete(String name)
    {
        boolean delete = false;
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();

          List roleList = root.elements("item");
          for (Iterator it = roleList.iterator(); it.hasNext();)
          {
            Element elm = (Element) it.next();
            if(elm.element("name").getText().equals(name))
            {
               root.remove(elm);
            }
          }

          delete = xmlOperationHelper.saveXml(doc, xmlRealPath);
        }

        return delete;
    }

    public List<Role> findAll()
    {
        List<Role> roles = new ArrayList<Role>();
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();
          //List roleList = doc.selectNodes("//item");
          List roleList = root.elements("item");
          for (Iterator it = roleList.iterator(); it.hasNext();)
          {
            Element elm = (Element) it.next();
            roles.add(new Role(
                     elm.element("name").getText(),
                     elm.element("desciption").getText()
            ));
          }
        }
        return roles;
    }

    public List<String> findAllRoles() {
        List<String> roles = new ArrayList<String>();
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();
          //List roleList = doc.selectNodes("//item");
          List roleList = root.elements("item");
          for (Iterator it = roleList.iterator(); it.hasNext();)
          {
            Element elm = (Element) it.next();
            roles.add(elm.element("name").getText());
          }
        }
        return roles;
    }

    public Role getRole(String name)
    {
        Role role = new Role();
        return role;
    }
}
