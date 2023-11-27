package hu.domparse.R65UKG;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class DOMQueryR65UKG {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException{
        File xmlFile = new File("XMLTaskR65UKG/DOMParseR65UKG/src/hu/domparse/R65UKG/XMLR65UKG.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();

        Document doc = dbBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("1. Azok a tárgyak, melyekből elmélet és gyakorlat is van egyaránt: ");

        NodeList targyList= doc.getElementsByTagName("Tárgyak");
        for(int i=0; i<targyList.getLength(); i++){
            Node node = targyList.item(i);
            printTargy(node, "elmélet és gyakorlat");
        }

        System.out.println("");
        System.out.println("2. Azok az oktatok, melyeknek az Informatikai épületben van a szobájuk: ");

        NodeList oktatoList = doc.getElementsByTagName("Oktatók");
        for(int i=0; i<oktatoList.getLength(); i++){
            Node node = oktatoList.item(i);
            printOktato(node, "Informatikai épület");
        }

        System.out.println("");
        System.out.println("3. Azok a termek, ahol 100-nál nagyobb a férőhely: ");

        NodeList teremList = doc.getElementsByTagName("Terem");
        for(int i=0; i<teremList.getLength(); i++){
            Node node = teremList.item(i);
            printTerem(node, 100);
        }

        System.out.println("");
        System.out.println("4. Azok a termek, ahol van vetítő: ");

        for(int i=0; i<teremList.getLength(); i++){
            Node node = teremList.item(i);
            printTerem2(node,"Van");
        }

        System.out.println("");
        System.out.println("5. Annak a diáknak a neve, akihez az R65UKG Neptunkód tartozik: ");

        NodeList hallgatoList = doc.getElementsByTagName("Hallgatók");
        for(int i=0; i<hallgatoList.getLength(); i++){
            Node node = hallgatoList.item(i);
            printHallgato(node,"R65UKG");
        }

    }

    private static void printTargy(Node node, String condition){
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            String Taid = element.getAttribute("Taid");

            Node nname = element.getElementsByTagName("Név").item(0);
            String name = nname.getTextContent();

            Node nsubject_code = element.getElementsByTagName("Tárgykód").item(0);
            String subject_code = nsubject_code.getTextContent();

            Node ntype = element.getElementsByTagName("Típus").item(0);
            String type = ntype.getTextContent();

            if(type.equals(condition)){
                System.out.printf("Tárgyneve: " + name + ", tárgykódja: " + subject_code);
                System.out.println("");
            }
        }
    }

    private static void printOktato(Node node, String condition){
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            String Oid = element.getAttribute("Oid");

            Node nname = element.getElementsByTagName("Név").item(0);
            String name = nname.getTextContent();

            Node nbuilding = element.getElementsByTagName("Épület").item(0);
            String building = nbuilding.getTextContent();

            if(building.equals(condition)){
                System.out.printf(name);
                System.out.println("");
            }
        }
    }

    private static void printTerem(Node node, int condition){
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            String Tid = element.getAttribute("Tid");

            Node nroom_number= element.getElementsByTagName("Teremszám").item(0);
            String room_number = nroom_number.getTextContent();

            Node nseat = element.getElementsByTagName("Férőhely").item(0);
            int seat = 0;
            try{
                seat = Integer.parseInt(nseat.getTextContent());
            }
            catch(Exception e){
                seat=0;
            }
            

            if(seat > condition){
                System.out.printf("Teremszáma: " + room_number + ", férőhely: " + seat);
                System.out.println("");
            }
        }
    }

    private static void printTerem2(Node node, String condition){
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            String Tid = element.getAttribute("Tid");

            Node nroom_number= element.getElementsByTagName("Teremszám").item(0);
            String room_number = nroom_number.getTextContent();

            Node nprojector = element.getElementsByTagName("Vetítő_állapota").item(0);
            String projector = nprojector.getTextContent();

            if(projector.equals(condition)){
                System.out.printf("Teremszáma: " + room_number);
                System.out.println("");
            }
        }
    }

    private static void printHallgato(Node node, String condition){
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            String Neptun_code = element.getAttribute("Neptunkód");

            Node nname = element.getElementsByTagName("Név").item(0);
            String name = nname.getTextContent();

            if(Neptun_code.equals(condition)){
                System.out.printf(name);
                System.out.println("");
            }
        }
    }

}