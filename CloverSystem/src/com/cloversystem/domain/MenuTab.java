package com.cloversystem.domain;

import java.io.Serializable;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 09/10/2013
 * Time: 10:40:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class MenuTab implements Serializable {
    private String tabName;
    private String tabTitle;
    private String[] tabRoles;
    private String tabUrl;
    private List<String> tabAllExtendUrls;
    private String tabAllExtendUrlsStr;
    private List<MenuLink> tabMenuLinks;

    public MenuTab() {
    }

    public MenuTab(String tabName, String tabTitle, String[] tabRoles, String tabUrl, List<String> tabAllExtendUrls, String tabAllExtendUrlsStr, List<MenuLink> tabMenuLinks) {
        this.tabName = tabName;
        this.tabTitle = tabTitle;
        this.tabRoles = tabRoles;
        this.tabUrl = tabUrl;
        this.tabAllExtendUrls = tabAllExtendUrls;
        this.tabAllExtendUrlsStr = tabAllExtendUrlsStr;
        this.tabMenuLinks = tabMenuLinks;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }

    public String[] getTabRoles() {
        return tabRoles;
    }

    public void setTabRoles(String[] tabRoles) {
        this.tabRoles = tabRoles;
    }

    public String getTabUrl() {
        return tabUrl;
    }

    public void setTabUrl(String tabUrl) {
        this.tabUrl = tabUrl;
    }

    public List<MenuLink> getTabMenuLinks() {
        return tabMenuLinks;
    }

    public void setTabMenuLinks(List<MenuLink> tabMenuLinks) {
        this.tabMenuLinks = tabMenuLinks;
    }

    public List<String> getTabAllExtendUrls() {
        return tabAllExtendUrls;
    }

    public void setTabAllExtendUrls(List<String> tabAllExtendUrls) {
        this.tabAllExtendUrls = tabAllExtendUrls;
    }

    public String getTabAllExtendUrlsStr() {
        return tabAllExtendUrlsStr;
    }

    public void setTabAllExtendUrlsStr(String tabAllExtendUrlsStr) {
        this.tabAllExtendUrlsStr = tabAllExtendUrlsStr;
    }
}
