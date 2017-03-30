package cn.gateside.gattmg.extents;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class XmlExtents {
	
	/*
	 * @param element Element
	 * @param attributeName  element attr name
	 * @return Stirng list
	 */
	public static List<String> getAttributeValue(Element element, String attributeName){
		List<String> attriValueList = new ArrayList<String>();
		for(@SuppressWarnings("unchecked")
		Iterator<Element> iter = element.elementIterator(); iter.hasNext();){
			Element e = (Element)iter.next();
			attriValueList.add(e.attributeValue(attributeName));
		}
		return attriValueList;
	}
	
	
	/**
	 * @param filePath  xml file path
	 * @param elementName  element name
	 * @return Element
	 * @throws DocumentException ex
	 */
	public static Element getElementByName(String filePath, String elementName) throws DocumentException{
		Element root = getRootElement(filePath);
		return root.element(elementName);
	}
	/**
	 * @param filePath  xml file path
	 * @param tagName xml element tag name
	 * @return Element List
	 * @throws DocumentException ex
	 */
	public static List<Element> getElementsByTag(String filePath, String tagName) throws DocumentException{
		Element root = getRootElement(filePath);
		return root.elements(tagName);
	}
	
	
	
	/**
	 * @param filePath xml file path
	 * @return XML Element
	 * @throws DocumentException
	 */
	private static Element getRootElement(String filePath) throws DocumentException{
		SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File(filePath));
        Element root = document.getRootElement();
        return root;
	}
	
	/**
	 * @param filePath xml file path
	 * @param elementName element name
	 * @param attributeName attr name
	 * @return String list
	 * @throws DocumentException ex
	 */
	public static List<String> getAttributeValueByElementName(String filePath, String elementName, String attributeName) throws DocumentException{
		List<String> valueList = new ArrayList<String>();
		List<Element> elementList = XmlExtents.getElementsByTag(filePath, elementName);
		for(Element item : elementList)
		{
			String is_moduleString=item.attributeValue("StepModule");
			if(is_moduleString==null)
			{
				valueList.add(item.attributeValue(attributeName));	
			}	
		}
		return valueList;
	}
	
	/**
	 * @param rootName rootElement name
	 * @param classStringNames class names
	 * @return Document
	 */
	public static Document createXml(String rootName, List<String> classStringNames){
		Document document = DocumentHelper.createDocument();
		
		Element root = document.addElement(rootName);		
		Element test = root.addElement("test");		
		Element classes = test.addElement("classes");		
		Element result = createClassesChild(classes, classStringNames);
		
		root.addAttribute("name","Suite");
		root.addAttribute("parallel", "none");
		test.addAttribute("name", "Test");
		
		return document;
	}
	
	/**
	 * @param classElement  class Element
	 * @param testStrings tst names
	 * @return
	 */
	private static Element createClassesChild(Element classElement, List<String> testStrings){
		Element eachElement = null;
		for(String each:testStrings){
			eachElement = classElement.addElement("class");
			eachElement.addAttribute("name", each);
		}
		return eachElement;
	}
	
	/**
	 * @return OutPutFormat
	 */
	private static OutputFormat formatXmlFile(){
		OutputFormat xmlFormat = new OutputFormat();
		
		xmlFormat.setEncoding("UTF-8");
		xmlFormat.setIndentSize(2);
		xmlFormat.setNewlines(true);
		
		return xmlFormat;
	}
	
	/**
	 * @param document document
	 * @param outputPath outputPath
	 * @param xmlName xmlName
	 * @throws IOException ex
	 */
	public static void XmlOutput(Document document, String outputPath, String xmlName) throws IOException{
		FileWriter fileWriter = new FileWriter(outputPath + xmlName);
		OutputFormat xmlFormat = formatXmlFile();
		XMLWriter xmlWriter = new XMLWriter(fileWriter, xmlFormat);
		xmlWriter.write(document);
		xmlWriter.close();
	}
}
