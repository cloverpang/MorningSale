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
 * Date: 09/10/2013
 * Time: 10:40:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class MenuDaoImpl implements MenuDao{
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

    public boolean addMenuTab(MenuTab menuTab) {
        boolean add = false;
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);

        boolean inputTest = true;
        if(menuTab.getTabName().equals("") && menuTab.getTabTitle().equals(""))
        {
           inputTest = false;
        }
        if(doc != null && inputTest == true)
        {
           String roleStr = StringHelper.join(",",menuTab.getTabRoles());

           Element root = doc.getRootElement();
           Element tabEle = root.addElement("tab");
           tabEle.addAttribute("name", menuTab.getTabName());
           tabEle.addAttribute("title", menuTab.getTabTitle());
           tabEle.addAttribute("roles", roleStr);

           add = xmlOperationHelper.saveXml(doc, xmlRealPath);
        }
        return add;
    }

    public boolean addMenuLink(MenuLink menuLink,String tabName, String tabTitle) {
        boolean add = false;
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);

        boolean inputTest = true;
        if(tabTitle.equals(""))
        {
           tabTitle = "Default Name";
        }
        if(menuLink.getLinkUrlExcuteStr().equals("") || tabName.equals(""))
        {
           inputTest = false;
        }
        if(doc != null && inputTest == true)
        {
           String roleStr = StringHelper.join(",",menuLink.getLinkRoles());

           Element root = doc.getRootElement();

           List tabList = root.elements("tab");
           boolean tabIsExisting = false;
           for (Iterator it = tabList.iterator(); it.hasNext();)
           {
             Element tabEle = (Element) it.next();
             if(tabEle.attributeValue("name").toString().equals(tabName))
             {
                  Element menuLinkEle =  tabEle.addElement("menuLink");

                  menuLinkEle.addAttribute("roles", roleStr);

                  Element menuLinkTitle = menuLinkEle.addElement("menuLinkTitle");
                  menuLinkTitle.setText(menuLink.getLinkTitle());

                  Element menuLinkUrl = menuLinkEle.addElement("menuLinkUrl");
                  menuLinkUrl.setText(menuLink.getLinkUrl());

                  Element extendUrls = menuLinkEle.addElement("extendUrls");
                  extendUrls.setText(menuLink.getLinkUrlExcuteStr());

                  tabIsExisting = true;
              }
           }
           if(tabIsExisting == false)
           {
              Element tabEle = root.addElement("tab");
              tabEle.addAttribute("name", tabName);
              tabEle.addAttribute("title", tabTitle);
              tabEle.addAttribute("roles", roleStr);

              Element menuLinkEle =  tabEle.addElement("menuLink");
              menuLinkEle.addAttribute("roles", roleStr);

              Element menuLinkTitle = menuLinkEle.addElement("menuLinkTitle");
              menuLinkTitle.setText(menuLink.getLinkTitle());

              Element menuLinkUrl = menuLinkEle.addElement("menuLinkUrl");
              menuLinkUrl.setText(menuLink.getLinkUrl());

              Element extendUrls = menuLinkEle.addElement("extendUrls");
              extendUrls.setText(menuLink.getLinkUrlExcuteStr());
           }

           add = xmlOperationHelper.saveXml(doc, xmlRealPath);
        }
        return add;
    }

    public boolean delete(String tabName, String linkUrl) {
        boolean delete = false;
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();

          List tablist = root.elements("tab");
          for (Iterator it = tablist.iterator(); it.hasNext();)
          {
            Element tabEle = (Element) it.next();
            if(!linkUrl.equals(""))
            {
              List menulinkList = tabEle.elements("menuLink");
              for (Iterator p_it = menulinkList.iterator(); p_it.hasNext();)
              {
                Element elm = (Element) p_it.next();
                if(elm.element("menuLinkUrl").getText().equals(linkUrl))
                {
                  tabEle.remove(elm);
                }
              }
            }
            else
            {
                if(tabEle.attributeValue("name").toString().equals(tabName))
                {
                   root.remove(tabEle);
                }
            }
          }
          delete = xmlOperationHelper.saveXml(doc, xmlRealPath);
        }

        return delete;
    }

    public List<MenuTab> findAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<MenuTab> findRoleMenuTabs(String roleName) {
        List<MenuTab> menuTabs = new ArrayList<MenuTab>();
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();

          List tabList = root.elements("tab");
          for (Iterator it = tabList.iterator(); it.hasNext();)
          {
            Element tab_elm = (Element) it.next();
            if(tab_elm.attributeValue("roles").contains(roleName))
            {
              MenuTab p = new MenuTab();
              p.setTabName(tab_elm.attributeValue("name").toString());
              p.setTabTitle(tab_elm.attributeValue("title").toString());
              p.setTabRoles(tab_elm.attributeValue("roles").split(","));
              //p.setTabUrl("");

              List<String> allExtendUrls = new ArrayList<String>();

              List menuLinkList = tab_elm.elements("menuLink");
              List<MenuLink> menuLinks = new ArrayList<MenuLink>();

              for (Iterator pit = menuLinkList.iterator(); pit.hasNext();)
              {
               Element elm = (Element) pit.next();
               if(elm.attributeValue("roles").contains(roleName))
               {
                 menuLinks.add(new MenuLink(
                  elm.element("menuLinkTitle").getText(),
                  elm.element("menuLinkUrl").getText(),
                  elm.element("menuLinkUrl").getText().replace("/","_").replace(".",""),
                  elm.attributeValue("roles").split(","),
                  elm.element("extendUrls").getText().split(",")
                 ));

                 allExtendUrls.add(elm.element("extendUrls").getText());
                 //set the first match for this tab url
                 if(p.getTabUrl() == null)
                 {
                    p.setTabUrl(elm.element("menuLinkUrl").getText());  
                 }
               }
              }
              p.setTabAllExtendUrls(allExtendUrls);
              p.setTabAllExtendUrlsStr(StringHelper.join(",",allExtendUrls));
              p.setTabMenuLinks(menuLinks);
              menuTabs.add(p);
            }
          }
        }
        return menuTabs;
    }

    public List<String> findAllTabNames() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<MenuTab> findAllMenuTabs() {
        List<MenuTab> menuTabs = new ArrayList<MenuTab>();
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();

          List tabList = root.elements("tab");
          for (Iterator it = tabList.iterator(); it.hasNext();)
          {
            Element tab_elm = (Element) it.next();
            MenuTab p = new MenuTab();
            p.setTabName(tab_elm.attributeValue("name").toString());
            p.setTabTitle(tab_elm.attributeValue("title").toString());
            p.setTabRoles(tab_elm.attributeValue("roles").split(","));
            //p.setTabUrl("");

            List<String> allExtendUrls = new ArrayList<String>();
            List menuLinkList = tab_elm.elements("menuLink");

            List<MenuLink> menuLinks = new ArrayList<MenuLink>();
            for (Iterator pit = menuLinkList.iterator(); pit.hasNext();)
            {
               Element elm = (Element) pit.next();
               menuLinks.add(new MenuLink(
                 elm.element("menuLinkTitle").getText(),
                 elm.element("menuLinkUrl").getText(),
                 elm.element("menuLinkUrl").getText().replace("/","_").replace(".",""),
                 elm.attributeValue("roles").split(","),
                 elm.element("extendUrls").getText().split(",")
               ));

               String[] extendUrls = elm.element("extendUrls").getText().split(",");
               allExtendUrls.add(elm.element("extendUrls").getText());
               //set the first match for this tab url
               if(p.getTabUrl() == null)
               {
                 p.setTabUrl(elm.element("menuLinkUrl").getText());
               }
            }
              
            p.setTabAllExtendUrls(allExtendUrls);
            p.setTabAllExtendUrlsStr(StringHelper.join(",",allExtendUrls));
            p.setTabMenuLinks(menuLinks);

            menuTabs.add(p);
          }
        }
        return menuTabs;
    }

    public boolean updateRoleMenus(String roleName, String menuLinkCheckedList) {
        boolean update = false;
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();

          List tabList = root.elements("tab");

          for (Iterator it = tabList.iterator(); it.hasNext();)
          {
            Element tab_elm = (Element) it.next();

            //add this role to tab roles, if this tab not have this role name, add it
              if(!tab_elm.attributeValue("roles").contains(roleName))
              {
                  if(tab_elm.attributeValue("roles").equals(""))
                  {
                      tab_elm.addAttribute("roles",roleName);
                  }
                  else
                  {
                      tab_elm.addAttribute("roles",tab_elm.attributeValue("roles") + "," + roleName);
                  }
              }


            List menuLinkList = tab_elm.elements("menuLink");
            //add roles
            for (Iterator pit = menuLinkList.iterator(); pit.hasNext();)
            {
              Element elm = (Element) pit.next();

              if(menuLinkCheckedList.contains(elm.element("menuLinkUrl").getText()))
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

    public boolean deleteRoleInMenus(String roleName) {
        boolean delete = false;
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();

          List tabList = root.elements("tab");
          for (Iterator it = tabList.iterator(); it.hasNext();)
          {
            Element tabEle = (Element) it.next();
            if(tabEle.attributeValue("roles").contains(roleName))
            {
                  if(tabEle.attributeValue("roles").contains("," + roleName))
                  {
                    tabEle.addAttribute("roles",tabEle.attributeValue("roles").replace("," + roleName,""));
                  }
                  else if((tabEle.attributeValue("roles").contains(roleName+",")))
                  {
                    tabEle.addAttribute("roles",tabEle.attributeValue("roles").replace(roleName + ",",""));
                  }
                  else
                  {
                    tabEle.addAttribute("roles",tabEle.attributeValue("roles").replace(roleName,""));
                  }
            }
            List menuLinks = tabEle.elements("menuLink");
            for (Iterator p_it = menuLinks.iterator(); p_it.hasNext();)
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

    public boolean updateMenuTabTitle(String tabName, String tabTitle) {
        boolean update = false;
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();
          List tabList = root.elements("tab");
          for (Iterator it = tabList.iterator(); it.hasNext();)
          {
             Element tab_elm = (Element) it.next();
             if(tab_elm.attributeValue("name").equals(tabName))
             {
                tab_elm.addAttribute("title",tabTitle);
             }
          }
          update = xmlOperationHelper.saveXml(doc, xmlRealPath);
        }
        return update;
    }

    public boolean updateMenuLink(String tabName, String linkUrl, String linkTitle, String extendUrls) {
        boolean update = false;
        Document doc = xmlOperationHelper.getXMLDocument(xmlRealPath);
        if(doc != null)
        {
          Element root = doc.getRootElement();
          List tabList = root.elements("tab");
          for (Iterator it = tabList.iterator(); it.hasNext();)
          {
             Element tab_elm = (Element) it.next();
             if(tab_elm.attributeValue("name").equals(tabName))
             {
                 List menuLinks = tab_elm.elements("menuLink");
                 for (Iterator p_it = menuLinks.iterator(); p_it.hasNext();)
                 {
                     Element elm = (Element) p_it.next();
                     if(elm.element("menuLinkUrl").getText().equals(linkUrl))
                     {
                         elm.element("menuLinkTitle").setText(linkTitle);
                         elm.element("extendUrls").setText(extendUrls);
                     }
                 }
             }
          }
          update = xmlOperationHelper.saveXml(doc, xmlRealPath);
        }
        return update;
    }
}
