package hu.saxR65UKG;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class SaxR65UKG extends DefaultHandler{
    public static void main(String[] args) throws Exception{
        
        try{
            File xml = new File("R65UKG_1025/SaxR65UKG/hu/saxR65UKG/R65UKG_kurzusfelvetel.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            SaxHandlerR65UKG handler = new SaxHandlerR65UKG();
            parser(xml, handler);
        }catch(IOException e){
            System.out.println("Nem sikerült megnyitni a kért fájlt!");
            e.printStackTrace();
        }catch(ParserConfigurationException e){
            System.out.println("Nem sikerült!");
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

        
    }
}