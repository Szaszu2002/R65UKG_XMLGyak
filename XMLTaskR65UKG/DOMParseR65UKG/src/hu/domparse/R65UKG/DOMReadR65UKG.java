package hu.domparse.R65UKG;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMReadR65UKG {

	public static void main(String[] args) {
        File xmlFile = new File("XMLTaskR65UKG/DOMParseR65UKG/src/hu/domparse/R65UKG/XMLR65UKG.xml");
        Document doc = ReadFile(xmlFile);

        if(doc!=null){
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        }
        else {
            System.out.println("Document is null");
            System.exit(-1);
        }

        System.out.println("<" + doc.getDocumentElement().getNodeName() + ">");
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        String separation = "";
        listData(nodeList, separation);
        System.out.println("</" + doc.getDocumentElement().getNodeName() + ">");
    }

    public static Document ReadFile(File xmlFile){
        Document doc = null;

        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            doc = dbBuilder.parse(xmlFile);
        } catch (ParserConfigurationException | SAXException | IOException exception) {
            exception.printStackTrace();
        }
        return doc;
    }

    public static void listData(NodeList nodeList, String separation){
        separation += "\t";
        boolean text = false;

        if(nodeList!=null){
            for(int i=0; i<nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                text=false;
                if(node.getNodeType()==Node.ELEMENT_NODE && ((!node.getTextContent().trim().isEmpty() && node.getNodeValue()!="#text") || node.hasAttributes())){
                    System.out.println();
                    System.out.print(separation + "< " + node.getNodeName());
                    NamedNodeMap attribute = node.getAttributes();
                    for(int j=0; j<attribute.getLength(); j++){
                        System.out.print(" " +attribute.item(j));
                    }
                    System.out.print(" > ");
                    NodeList newNodeList = node.getChildNodes();
                    listData(newNodeList, separation);
                    if(newNodeList.item(0) instanceof Text){
                        text=true;
                    }
                }
                else if(node instanceof Text){
                    text = true;
                    String value = node.getNodeValue().trim();
                    if(value.isEmpty()){
                        continue;
                    }
                    System.out.print(node.getTextContent());
                    
                }
                if(node.getNodeType()==Node.ELEMENT_NODE && ((!node.getTextContent().trim().isEmpty() && node.getNodeValue()!="#text") || node.hasAttributes())){
                    if(!text){
                        System.out.println();
                        System.out.println(separation + "</ " + node.getNodeName()+">");
                    }
                    else{
                        System.out.println("</" + node.getNodeName() + ">");
                    }
                }
                
            }
        }
    }


}