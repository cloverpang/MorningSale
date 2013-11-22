package com.cloversystem.domain;

import java.io.Serializable;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 09/10/2013
 * Time: 10:40:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class MenuLink implements Serializable {
    private String linkTitle;
    private String linkUrl;
    private String linkUrlExcuteStr;
    private String[] linkRoles;
    private String[] extendUrls;

    public MenuLink() {
    }

    public MenuLink(String linkTitle, String linkUrl, String linkUrlExcuteStr, String[] linkRoles, String[] extendUrls) {
        this.linkTitle = linkTitle;
        this.linkUrl = linkUrl;
        this.linkUrlExcuteStr = linkUrlExcuteStr;
        this.linkRoles = linkRoles;
        this.extendUrls = extendUrls;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String[] getLinkRoles() {
        return linkRoles;
    }

    public void setLinkRoles(String[] linkRoles) {
        this.linkRoles = linkRoles;
    }

    public String[] getExtendUrls() {
        return extendUrls;
    }

    public void setExtendUrls(String[] extendUrls) {
        this.extendUrls = extendUrls;
    }

    public String getLinkUrlExcuteStr() {
        return linkUrlExcuteStr;
    }

    public void setLinkUrlExcuteStr(String linkUrlExcuteStr) {
        this.linkUrlExcuteStr = linkUrlExcuteStr;
    }
}
