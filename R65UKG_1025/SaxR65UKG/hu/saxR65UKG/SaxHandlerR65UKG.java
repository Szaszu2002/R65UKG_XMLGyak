package hu.saxR65UKG;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class SaxHandlerR65UKG extends DefaultHandler{

    int indent;

    public void startElement(String uri, String localName, String Name, Attributes attributes) throws SAXException{
        if(Name=="R65UKG_kurzusfelvetel"){
            System.out.print("R65UKG_kurzusfelvetel");
            String tanev = attributes.getValue("tanev");
            String egyetem = attributes.getValue("egyetem");
            String attribute = " {tanév: " + tanev + ", egyetem neve: " + egyetem + "} ";
            System.out.print(attribute);
        }
        hallgatoStart(Name, attributes){
            
        }
    }

        
}
