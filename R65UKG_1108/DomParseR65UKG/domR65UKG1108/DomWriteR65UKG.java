package domR65UKG1108;

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



class DomRead{
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException{
        File xmFile = new File("R65UKG_1108/DomParseR65UKG/domR65UKG1108/R65UKG_kurzusfelvetel.xml");

        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();

        Document document = dBuilder.parse(xmFile);
        document.normalize();

        saveDocument(document, "kurzusfelvetel1R65UKG.xml");
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