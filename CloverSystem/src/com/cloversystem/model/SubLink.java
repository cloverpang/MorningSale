package com.cloversystem.model;

import com.sun.net.httpserver.HttpContext;
import java.util.*;
import java.lang.*;
import java.applet.*;
import javax.servlet.http.*;
import javax.servlet.*;

import com.sun.xml.internal.ws.client.RequestContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.servlet.support.RequestContext.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 27/09/2013
 * Time: 10:36:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class SubLink {

      public static String linkTemplate = "<a href='%s' %s > %s </a>";
      public String[] expectedUrls;
      public String title;
      public String link;

      public SubLink(){}

    public SubLink(String[] expectedUrls, String title, String link) {
        this.expectedUrls = expectedUrls;
        this.title = title;
        this.link = link;
    }

    public String[] getExpectedUrls() {
        return expectedUrls;
    }

    public void setExpectedUrls(String[] expectedUrls) {
        this.expectedUrls = expectedUrls;
    }

    public void setTitle(String title)
      {
          this.title = title;
      }

      public String getTitle()
      {
          return this.title;
      }

      public void setLink(String link)
      {
          this.link = link;
      }

      public String getLink()
      {
          return this.link;
      }

      //HttpServletRequest  request = ServletActionContext.getRequest();

      public String GetAnchorTag()
      {
            //String localPath = request.getRequestURI();
            String namespace = ServletActionContext.getActionMapping().getNamespace();
            String actionName = ServletActionContext.getActionMapping().getName();
            String localPath = namespace + "/" + actionName + ".action";

            //String retutnLinkStr;
            if(localPath != null)
            {
              for (String i : getExpectedUrls())
              {
                if (i.equals(localPath) || i.contains("*"))
                {
                    //return String.format(linkTemplate,getLink(),getTitle(),"class='cmsLinkSelected'");
                    return "<a href='" + this.link + "'" + "class='cmsLinkSelected'" + ">" +  this.title + "</a>";
                    //retutnLinkStr =   "<a href='" + this.link + "'" + "class='cmsLinkSelected'" + ">" +  this.title + "</a>";
                }
              }
            }
            return "<a href='" + this.link + "'" + ">" +  this.title + "</a>";
      }
}
