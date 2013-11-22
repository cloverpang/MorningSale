package com.cloversystem.util.impl;

import com.cloversystem.util.XmlOperationHelper;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 09/10/2013
 * Time: 7:35:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class NormalXmlHelper implements XmlOperationHelper {
    protected static final Logger logger = Logger.getLogger(NormalXmlHelper.class);
    public boolean saveXml(Document doc, String realPath) {
        boolean save = false;
        try
        {
            OutputFormat outFmt = new OutputFormat("\t",true);
            outFmt.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileOutputStream(getXMLPath(realPath)),outFmt);
            writer.write(doc);
            writer.close();

            save = true;
        }
        catch(Exception e)
        {
            logger.error("save " + realPath + " file get a error!");
            save = false;
        }
        return save;
    }

    public Document getXMLDocument(String realPath) {
        String xmlFilePath = getXMLPath(realPath);

        Document document = null;
        FileInputStream fis = null;

        try
        {
            SAXReader saxReader = new SAXReader();
            saxReader.setStripWhitespaceText(true);
            saxReader.setMergeAdjacentText(true);
            saxReader.setFeature("http://apache.org/xml/features/validation/schema", false);
            saxReader.setEncoding("UTF-8");
//            logger.info(xmlFilePath);
            fis = new FileInputStream(xmlFilePath);
            document = saxReader.read(fis);
            if(document == null)
            {
               logger.warn("File [" + xmlFilePath + "] read as null");
            }
            return document;
        }
        catch (Exception e)
        {
            logger.error("File [" + xmlFilePath + "] trans to Document exception", e);
            return null;
        }
        finally
        {
            if(fis != null)
            {
                try
                {
                    fis.close();
                } catch (IOException e)
                {
                    logger.error("File [" + xmlFilePath + "]trans to Document close InputStream exception", e);
                }
            }
        }
    }

    public String getXMLPath(String realPath) {
      String path = this.getClass().getClassLoader().getResource(realPath).getPath();
      return path;
    }
}
