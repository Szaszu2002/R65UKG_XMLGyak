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
        hallgatoStart(Name, attributes);
        if(Name== "kurzusok"){
            indent(1);
            System.out.print("kurzusok");
        }
        kurzusStart(Name, attributes);
        System.out.print(" start");
    }

    public void characters(char character[], int start, int length) throws SaxException{
        if(character.length != 0){
            System.out.println();
            indent(indent+1);
            System.out.println(new String(character, start, length));
        }
    }

    public void endElement(String uri, String localName, String Name) throws SaxException{
        if(Name == "R65UKG_kurzusfelvetel"){
            System.out.print("R65UKG_kurzusfelvetel");
        }
        if(Name == "hallgato"){
            indent(1);
            System.out.print("hallgató");
        }
        if(Name == "hnev"){
            indent(2);
            System.out.print("név");
        }
        if(Name == "szulev"){
            indent(2);
            System.out.print("születési év");
        }
        if(Name == "szak"){
            indent(2);
            System.out.print("szak");
        }
        if(Name == "kurzusok"){
            indent(1);
            System.out.print("kurzusok");
        }
        if(Name == "kurzus"){
            indent(2);
            System.out.print("kurzus");
        }
        if(Name == "kurzusnev"){
            indent(3);
            System.out.print("kurzusnév");
        }
        if(Name == "kredit"){
            indent(3);
            System.out.print("kredit");
        }
        if(Name == "hely"){
            indent(3);
            System.out.print("hely");
        }
        if(Name == "idopont"){
            indent(3);
            System.out.print("időpont");
        }
        if(Name == "oktato"){
            indent(3);
            System.out.print("oktato");
        }
        System.out.print(" end");
    }

    void hallgatoStart(String Name, Attributes attributes){
        if(Name == "hallgato"){
            indent(1);
            System.out.print("hallgató");
            String evfolyam = attributes.getValue("evf");
            String attribute = " {évfolyam: " + evfolyam + "}";
            System.out.print(attribute);
        }
        if(Name == "hnev"){
            indent(2);
            indent=2;
            System.out.print("név");
        }
        if(Name == "szulev"){
            indent(2);
            indent=2;
            System.out.print("születési év");
        }
        if(Name == "szak"){
            indent(2);
            indent=2;
            System.out.print("szak");
        }
    }

    void kurzusStart(String Name, Attributes attributes){
        if(Name == "kurzus"){
            indent(2);
            System.out.print("kurzus");
            String id = attributes.getvalue("id");
            String jovahagyas = attributes.getValue("jóváhagyás");
            String attribute;
            if(jovahagyas != null){
                attribute = " {id: " + id + ", jóváhagyás: " + "}";
            }
            else{
                attribute = " {id: " + id + "}";
            }
            System.out.print(attribute);
        }
        if(Name == "kurzusnev"){
            indent(3);
            indent=3;
            System.out.print("kurzusnév");
        }
        if(Name == "kredit"){
            indent(3);
            indent=3;
            System.out.print("kredit");
        }
        if(Name == "hely"){
            indent(3);
            indent=3;
            System.out.print("hely");
        }
        if(Name == "idopont"){
            indent(3);
            indent=3;
            System.out.print("időpont");
        }
        if(Name == "oktato"){
            indent(3);
            indent=3;
            System.out.print("oktato");
        }
    }

        
}
