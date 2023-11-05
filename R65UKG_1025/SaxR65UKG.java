import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DEfaultHandler;



public class SaxR65UKG extends DefaultHandler{
    static public void main(String[] args) throws Exception{
        String filename = null;

        for (int i = 0; i < args.length; i++) {
            filename = args[i];
            if (i != args.length - 1) {
                usage();
            }
        }

        if (filename == null) {
            usage();
        }
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SaxHandler handler = new SaxHandler();
        XMLReader reader = new SAXParser();
    }
}

startElement(){
    if()
}
characters(){
    if()
}
endElement(){

}

try{

}catch