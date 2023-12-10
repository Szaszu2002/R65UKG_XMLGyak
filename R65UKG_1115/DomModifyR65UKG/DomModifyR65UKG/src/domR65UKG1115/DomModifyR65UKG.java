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
        File xmlFile = new File("R65UKG_1115/DomModifyR65UKG/src/domR65UKG1115/R65UKG_kurzusfelvetel.xml");
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(xmlFile);
        document.normalize();

        Element root =document.getDocumentElement();

        //a
        Element kurzus = (Element)document.getElementsByTagName("kurzus").item(0);
        createOraado(document);

        //b
        modifyLanguage(document, "angol");

        //c
        Element hallgato = (Element)document.getElementsByTagName("hallgato").item(0);
        root.insertBefore(createhallgato(document, "hallgato", "Görög Krisztina", "2002"),hallgato);


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

    public static Node createOraado(Document document){
        
    }

    public static void modifyLanguage(Document document, String language){
        Element element = document.createElement("kurzus");

        for(int i=0;i<list.getLength();i++){
            Node node = list.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                if(node.getAttributes.getNamedItem("nyelv").getTextContent().equals(language)){
                    node.getAttributes.getNamedItem("nyelv").getTextContent("német");
                }
            }
        }
    }

    public static Node createhallgato(Document document, String table, String name, String year){
        Element element = document.createElement(table);

        element.appendChild(createElement(document, "hnev", name));
        element.appendChild(createElement(document, "szulev", year));

        return element;
    }

    private static Node createElement(Document document, String name, String value){
        Element element = document.createElement(name);
        element.appendChild(document.createTextNode(value));

        return element;
    }
}
