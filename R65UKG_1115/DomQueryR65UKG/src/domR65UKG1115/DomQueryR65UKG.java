package domR65UKG1115;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


public class DomQuery {
    public static void main(String[] args) throws Exception {
        File xmlFile = new File("XMLTaskR65UKG/DOMParseR65UKG/src/hu/domparse/R65UKG/XMLR65UKG.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();

        Document doc = dbBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Felvett kurzusok: ");
    
        NodeList kurzusokList= doc.getElementsByTagName("kurzusok");
        for(int i=0; i<kurzusokList.getLength(); i++){
            Node node = kurzusokList.item(i);
            printKurzusok(node);
        }

        System.out.println();
        System.out.println("A dokumentum első példánya: ");

        Node hallgato = doc.getFirstChild();
        while (hallgato.getNodeType() == Node.TEXT_NODE){
            hallgato = hallgato.getNextSibling();
        }
        printFirst(hallgato);
        writeFirst("R65UKG_1115/DomQueryR65UKG/src/domR65UKG1115", hallgato);

        System.out.println();
        System.out.println("Oktatók:");

        NodeList kurzusList = doc.getElementsByTagName("kurzus");
        for(int i=0; i<kurzusList.getLength(); i++){
            Node node = kurzusList.item(i);
            printOktatok(node);
        }

        System.out.println();
        System.out.println("5 kredites kurzusok nevei:");

        for(int i=0; i<kurzusList.getLength(); i++){
            Node node = kurzusList.item(i);
            printKredit(node,"5");
        }

    }

    private static void printKurzusok(Node node){
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;

            Node kname = element.getElementsByTagName("kurzusneve").item(0);
            String name = kname.getTextContent();

            System.out.println(name);
        }
    }
    public static void printFirst(Node hallgato){
        boolean textOnly = false;

        System.out.print("\n");
        System.out.print("<" + node.getNodeName());
        if(node.hasAttributes()){
            NamedNodeMap attribute = node.getAttributes();
            for(int i=0; i<attribute.getLength();i++){
                System.out.print(" " + attribute.item(i));
            }
        }
        System.out.print(">");

        for(Node child = node.getFirstChild(); child != null; child =child.getNextSibling()){
            if(child.hasChildNodes()){
                printFirst(child);
            }
            else{
                if(!randNode(child)){
                    System.out.println(child.getNodeValue());
                    textOnly=true;
                }
            }
        }

        if(!textOnly){
            System.out.println();
            System.out.print("</" + node.getNodeName() + ">\n");
        }
        else{
            System.out.print("</" + node.getNodeName() + ">");
        }
    }

    private static boolean randNode(Node node){
        if (node.getNodeType() != Node.TEXT_NODE){
            return true;
        }
        String value = node.getNodeValue();
        if (value.trim().isEmpty() || value == "#text" || value == null){
            return true;
        }
        return false;
    }

    private static FileWriter xml;

    public static writeFirst(String path, Node node){
        if (!path.endsWith(".xml")){
            System.err.println("This is not an XML file!");
            return;
        }

        try {
            xml = new FileWriter(path, StandardCharsets.UTF_8);            
        } catch (IOException ioe) {
            System.out.println("IO ERROR!");
            ioe.printStackTrace();
        }

        if (xml == null){
            System.err.println("Failed to open file " + path);
            return;
        }

        try{
            xml.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

            Element rootElement = document.getDocumentElement();
            writeChildren((Node)rootElement, 0);
            xml.close();
        }
        catch (IOException e){
            System.err.println("IO error!");
            e.printStackTrace();
            return;
        }
        
        System.out.println("Finish!");
    }

    public static printOktatok(Node node){
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;

            Node noktato= element.getElementsByTagName("oktato").item(0);
            String oktato = noktato.getTextContent();

            System.out.printf(oktato);
            System.out.println("");
        }
    }

    private static void printKredit(Node node, String condition){
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;

            Node nkredit = element.getElementsByTagName("Név").item(0);
            String kredit = nkredit.getTextContent();

            Node nname = element.getElementsByTagName("Név").item(0);
            String name = nname.getTextContent();

            if(kredit.equals(condition)){
                System.out.printf(name);
                System.out.println("");
            }
        }
    }
}
