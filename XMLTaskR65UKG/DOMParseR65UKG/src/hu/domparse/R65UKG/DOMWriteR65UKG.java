package hu.domparse.R65UKG;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

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

public class DOMWriteR65UKG {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException{
        File xmFile = new File("XMLTaskR65UKG/DOMParseR65UKG/src/hu/domparse/R65UKG/XMLR65UKG.xml");

        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();

        Document document = dBuilder.parse(xmFile);
        document.normalize();

        //Lemenjük a dokumentumot
        saveDocument(document, "XMLR65UKG1.xml");

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
