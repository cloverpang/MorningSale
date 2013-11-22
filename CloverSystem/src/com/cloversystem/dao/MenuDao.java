package com.cloversystem.dao;

import java.util.*;
import com.cloversystem.domain.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 09/10/2013
 * Time: 10:38:07 AM
 * To change this template use File | Settings | File Templates.
 */
public interface MenuDao {
    boolean addMenuTab(MenuTab menuTab);

    boolean addMenuLink(MenuLink menuLink, String tabName, String tabTitle);

	boolean delete(String tabName, String linkUrl);

    List<MenuTab> findAll();

    List<MenuTab> findRoleMenuTabs(String roleName);

    List<String> findAllTabNames();

    List<MenuTab> findAllMenuTabs();

    boolean updateRoleMenus(String roleName, String menuLinkCheckedList);

    boolean deleteRoleInMenus(String roleName);

    boolean updateMenuTabTitle(String tabName, String tabTitle);

    boolean updateMenuLink(String tabName, String linkUrl, String linkTitle, String extendUrls);
}
