package xpathR65UKG;

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
import javax.xml.xpath.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class xPathR65UKG {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        File xmlFile = new File("R65UKG_1122/xPathR65UKG/src/xpathR65UKG/studentR65UKG.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();

        Document doc = dbBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        XPath xPath = XPathFactory.newInstance().newXPath();

        NodeList classStudents = (NodeList) xPath.compile("class/student").evaluate(doc, XPathConstants.NODESET);
        Node student02 = (NodeList) xPath.compile("//student[@id='02']").evaluate(doc, XPathConstants.NODESET).item(0);
        NodeList allStudent = (NodeList) xPath.compile("//student").evaluate(doc, XPathConstants.NODESET);
        Node student2 = (NodeList) xPath.compile("/class/student[2]").evaluate(doc, XPathConstants.NODESET).item(0);
        Node studentLast = (NodeList) xPath.compile("/class/student[last()]").evaluate(doc, XPathConstants.NODESET).item(0);
        Node studentLast2 = (NodeList) xPath.compile("/class/student[last()-1]").evaluate(doc, XPathConstants.NODESET).item(0);
        NodeList studentFirstTwo = (NodeList) xPath.compile("/class/student[position()<3]").evaluate(doc, XPathConstants.NODESET);
        NodeList studentAllRoot = (NodeList) xPath.compile("/class/").evaluate(doc, XPathConstants.NODESET);
        NodeList studentAttribute = (NodeList) xPath.compile("//student[@*]").evaluate(doc, XPathConstants.NODESET);
        NodeList allElement = (NodeList) xPath.compile("//*").evaluate(doc, XPathConstants.NODESET);
        NodeList student20 = (NodeList) xPath.compile("/class/student[kor>20]").evaluate(doc, XPathConstants.NODESET);
        NodeList studentName = (NodeList) xPath.compile("//student/keresztnev | //student/vezeteknev").evaluate(doc, XPathConstants.NODESET);

        System.out.println(" 1. ");
        printNode(allStudent);

        System.out.println("\n\n 2. ");
        if(student02 != null){
            printChild(student02);
        }

        System.out.println("\n\n 3. ");
        printNode(allStudent);

        System.out.println("\n\n 4. ");
        if(student2 != null){
            printChild(student2);
        }

        System.out.println("\n\n 5. ");
        if(studentLast != null){
            printChild(studentLast);
        }

        System.out.println("\n\n 6. ");
        if(studentLast2 != null){
            printChild(studentLast2);
        }

        System.out.println("\n\n 7. ");
        printNode(studentFirstTwo);

        System.out.println("\n\n 8. ");
        printNode(studentAllRoot);

        System.out.println("\n\n 9. ");
        printNode(studentAttribute);

        System.out.println("\n\n 10. ");
        printNode(allElement);

        System.out.println("\n\n 11. ");
        printNode(student20);

        System.out.println("\n\n 12. ");
        printNode(studentName);

        
    }   

    public static void printChild(Node node){
        boolean textOnly = false;
            System.out.print("\n");
            System.out.print("<" + node.getNodeName());
            if(node.hasAttributes()){
                NamedNodeMap attribute = node.getAttributes();
                for (int i = 0; i < attribute.getLength(); i++){
                    System.out.print(" " + attribute.item(i));
                }
            }
            System.out.print(">");
            for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
                if (child.hasChildNodes()){
                    printChild(child);
                }
                else {
                    if (!isRandomNode(child)){
                        System.out.print(child.getNodeValue());
                        textOnly = true;
                    }
                }
            }
            if (!textOnly){
                System.out.println();
                System.out.print("</" + node.getNodeName() + ">\n");
            }
            else{
                System.out.print("</" + node.getNodeName() + ">");
            }
    }
    public static printNode(NodeList node){
        if(node != null){
            for (int i = 0; i < node.getLength();i++){
                printChild(node.item(i));
            }
        }
    }
}