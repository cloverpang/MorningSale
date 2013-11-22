package com.cloversystem.util;

import org.dom4j.Document;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 09/10/2013
 * Time: 7:35:07 AM
 * To change this template use File | Settings | File Templates.
 */
public interface XmlOperationHelper {
    boolean saveXml(Document doc, String realPath);

    Document getXMLDocument(String realPath);

    String getXMLPath(String realPath);
}
