package domR65UKG1115;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException; 
import org.xml.sax.SAXParseException;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import org.w3c.dom.*;


public class DomModifyR65UKG{
    public static void main(String[] args) throws Exception {
        File xmlFile = new File("R65UKG_1115/DomModifyR65UKG/src/domR65UKG1115/R65UKG_kurzusfelvetel.xml")
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(xmlFile);
        document.normalize();

        Element root =document.getDocumentElement();

        Element kurzus = (Element)document.getElementsByTagName("kurzus").item(0);
        createOraado(document);


        saveDocument(document, "kurzusfelvetelModify1R65UKG.xml");
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

    public static void createOraado(Document document){
        
    }
}
