package hu.domparse.R65UKG;

import java.io.File;
import java.io.IOException;

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

public class DOMModifyR65UKG {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        File xmFile = new File("/XMLTaskR65UKG/DOMParseR65UKG/src/hu/domparse/R65UKG/R65UKG.xml");

        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();

        Document document = dBuilder.parse(xmFile);
        document.getDocumentElement().normalize();

        //beállítjuk a terem állapotát foglaltra
        NodeList nodeList = document.getElementsByTagName("Terem");
        for(int i=0; i<nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                if(node.getAttributes().getNamedItem("Tid").getTextContent().equals("1")){
                    NodeList childNodeList = node.getChildNodes();
                    for(int j=0; j<childNodeList.getLength(); j++){
                        Node childNode = childNodeList.item(j);
                        if(childNode.getNodeName().equals("Állapot")){
                            childNode.setTextContent("Foglalt");
                        }
                    }
                }
            }
        }

        //új oktatót adunk hozzá
        Element oktato = (Element)document.getElementsByTagName("Oktatók").item(0);
        oktato.appendChild(createOktato(document,"09","Tompa Tamás","tompa@iit.uni-miskolc.hu"));

        //új hallgatót adunk hozzá
        Element hallgato = (Element)document.getElementsByTagName("Hallgatók").item(0);
        hallgato.appendChild(createHallgato(document,"G2GWPO","Tucsa Eszter Boglárka","tucsa.eszter@gmail.com", "mérnökinformatikus"));

        //új termet adunk hozzá
        Element terem = (Element)document.getElementsByTagName("Terem").item(0);
        terem.appendChild(createTerem(document,"12","400","37-es előadó"));

        //Az 1-es id-vel rendelkező oktatót módosítjuk 13-as id-re
        modifyId(document, "Oktatók", "Oid", "01", "13");

        //Lemenjük a modosított dokumentumot
        saveDocument(document, "modify.txt");

    }

    public static void saveDocument(Document document, String filename) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{https://xml.apache.org/.xslt}indent-amount", "2");
		
		DOMSource source = new DOMSource(document);
		
		File myFile = new File(filename);
		
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		
		transf.transform(source, console);
		transf.transform(source, file);
	}

    private static Node createOktato(Document document, String Oid, String name, String email){
        Element element = document.createElement("Oktatók");

        element.setAttribute("Oid", Oid);
        element.appendChild(createElement(document,"Név", name));
        element.appendChild(createElement(document,"Email_cím",email));

        return element;
    }

    private static Node createHallgato(Document document, String neptuncode, String name, String email, String specialise){
        Element element = document.createElement("Hallgatók");

        element.setAttribute("Neptunkód", neptuncode);
        element.appendChild(createElement(document,"Név", name));
        element.appendChild(createElement(document,"Email_cím",email));
        element.appendChild(createElement(document,"Szak",specialise));

        return element;
    }

    private static Node createTerem(Document document, String Tid, String seat, String room_number){
        Element element = document.createElement("Terem");

        element.setAttribute("Tid", Tid);
        element.appendChild(createElement(document,"Férőhely", seat));
        element.appendChild(createElement(document,"Teremszám", room_number));

        return element;
    }

    private static Node createElement(Document document, String name, String value){
        Element element = document.createElement(name);
        element.appendChild(document.createTextNode(value));

        return element;
    }

    public static void modifyId(Document document, String table, String id, String old_id, String new_id){
        NodeList list = document.getElementsByTagName(table);
        for(int i=0; i<list.getLength(); i++){
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                if(node.getAttributes().getNamedItem(id).getTextContent().equals(old_id)){
                    node.getAttributes().getNamedItem(id).setTextContent(new_id);
                }
            }
        }
    }
}