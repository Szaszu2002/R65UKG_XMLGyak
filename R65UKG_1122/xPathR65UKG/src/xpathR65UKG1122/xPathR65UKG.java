package xpathR65UKG1122;

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

public class xPathModify {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        File xmlFile = new File("R65UKG_1122/xPathR65UKG/src/xpathR65UKG/R65UKG_kurzusfelvetel.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();

        Document doc = dbBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        XPath xPath = XPathFactory.newInstance().newXPath();

        NodeList nullCreditNo = (NodeList) xPath.compile("R65UKG_kurzusfelvetel/kurzusok/kurzus[descendant::kredit=0]").evaluate(doc, XPathConstants.NODESET);
        NodeList addLecturer = (NodeList) xPath.compile("R65UKG_kurzusfelvetel/kurzusok/kurzus/oktato[descendant::oktato='Fazekas Levente']").evaluate(doc, XPathConstants.NODESET);
        NodeList deleteCourse = (NodeList) xPath.compile("R65UKG_kurzusfelvetel/kurzusok/kurzus[not(@jovahagyas) or @jovahagyas='nem']").evaluate(doc, XPathConstants.NODESET);

        //Nulla kredites tárgyak jóváhagyásának módosítása nem-re
        if(nullCreditNo != null){
            for (int i = 0; i < nullCreditNo.getLength(); i++){
			    ((Element)nullCreditNo.item(i)).setAttribute("jovahagyas", "nem");
		    }
        }
        //Fazekas Levente oktato mellé Kunné Tamás Judit tanárnő beírása
        if(addLecturer != null){
            for (int i = 0; i < addLecturer.getLength(); i++){
                Node lecturer = addLecturer.item(i);
                lecturer.setTextContent(lecturer.getTextContent() + ", Kunné Tamás Judit");
		    }
        }
        //A nem jóváhagyott kurzusok törlése
        if(deleteCourse != null){
            for (int i = 0; i < deleteCourse.getLength(); i++){
                Node delete = deleteCourse.item(i);
                delete.getParentNode().removeChild(delete);
            }
        }

        saveDocument(doc, kurzusfelvetelR65UKG1.xml);

    }

     public static void saveDocument(Document document, String filename) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{https://xml.apache.org/.xslt}indent-amount", "2");
		
		DOMSource source = new DOMSource(document);
		
		File myFile = new File(filename);
		PrintStream p = null;
        try{
            p = new PrintStream(System.out, true,"UTF-8");
        }
        catch(Exception e){
            System.out.println("Don't make it!");
            return;
        }
        
		StreamResult console = new StreamResult(p);
        
		StreamResult file = new StreamResult(myFile);
		
		transf.transform(source, console);
		transf.transform(source, file);
	}
}