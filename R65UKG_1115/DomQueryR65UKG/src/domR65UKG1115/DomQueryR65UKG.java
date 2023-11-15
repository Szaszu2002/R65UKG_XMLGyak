package R65UKG_1115.DomQueryR65UKG.src.domR65UKG1115;
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException; 
import org.xml.sax.SAXParseException;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import org.w3c.dom.*;


public class DomQueryR65UKG {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse( new File("kurzusfelvetelR65UKG.xml"));
        Element elem = document.getDocumentElement();

        Node gyerek;
        for(int p=0; p<elem.getChildNodes().getLength(); p++){
            gyerek = elem.getChildNodes().item(p);
        }
    }
}
