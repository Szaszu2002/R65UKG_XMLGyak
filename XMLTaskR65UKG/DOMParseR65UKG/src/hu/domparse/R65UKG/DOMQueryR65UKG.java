package hu.domparse.R65UKG;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMQueryR65UKG {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        File xmlFile = new File("XMLR65UKG.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();

        Document doc = dbBuilder.parser(xmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("1. Azok a tárgyak, melyekből elmélet és gyakorlat is van egyaránt: ");

        NodeList targyList= doc.getElementsByTagname("Tárgyak");
        for(int i=0; i<targyList.getLength(); i++){
            Node node = targyList.item(i);
            printTargy(node, "elmélet és gyakorlat");
        }

        System.out.println("");
        System.out.println("2. Azok az oktatok, melyeknek az Informatikai épületben van a szobájuk: ");

        NodeList oktatoList = doc.getElementsByTagname("Oktatók");
        for(int i=0; i<oktatoList.getLength(); i++){
            Node node = oktatoList.item(i);
            printOktato(node, "Informatikai épület");
        }

        System.out.println("");
        System.out.println("3. Azok a termek, ahol 100-nál nagyobb a férőhely: ");

        NodeList teremList = doc.getElementsByTagname("Terem");
        for(int i=0; i<teremList.getLength(); i++){
            Node node = teremList.item(i);
            printOktato(node, 100);
        }

        System.out.println("");
        System.out.println("4. Azok a termek, ahol van vetítő: ");

        for(int i=0; i<teremList.getLength(); i++){
            Node node = teremList.item(i);
            printOktato(node, "Van");
        }

        System.out.println("");
        System.out.println("5. A Gépészmérnőki és Informatikai Kar szakjai: ");

        NodeList karList = doc.getElementsByTagname("Kar");
        for(int i=0; i<karList.getLength(); i++){
            Node node = karList.item(i);
            printOktato(node, "Gépészmérnőki és Informatikai Kar");
        }


    }
}