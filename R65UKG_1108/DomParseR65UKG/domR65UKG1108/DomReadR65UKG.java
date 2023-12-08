package domR65UKG1108;

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


class DomParseR65UKG{
    public static void main(String[] args){
        File xmlFile = new File("R65UKG_1108/DomParseR65UKG/domR65UKG1108/R65UKG_kurzusfelvetel.xml");
        Document doc = ReadFile(xmlFile);

        if(doc!=null){
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        }
        else {
            System.out.println("Document is null");
            System.exit(-1);
        }

        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        String separation = "";
        listData(nodeList, separation);
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

        if(nodeList!=null){
            for(int i=0; i<nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE && !node.getTextContent().trim().isEmpty()){
                    System.out.print(separation + "< " + node.getNodeName() + " >: ");
                    NamedNodeMap attribute = node.getAttributes();
                    for(int j=0; j<attribute.getLength(); j++){
                        System.out.print(" - " + attribute.item(j));
                    }
                    System.out.println();
                    NodeList newNodeList = node.getChildNodes();
                    listData(newNodeList, separation);
                }
                else if(node instanceof Text){
                    String value = node.getNodeValue().trim();
                    if(value.isEmpty()){
                        continue;
                    }
                    System.out.println(separation + node.getTextContent());
                }
            }
        }
    }
}
