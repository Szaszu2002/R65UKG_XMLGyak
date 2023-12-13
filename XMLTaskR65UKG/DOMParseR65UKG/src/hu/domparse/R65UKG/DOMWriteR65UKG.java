package hu.domparse.R65UKG;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

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

public class DOMWriteR65UKG {
    static FileWriter file;
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException{
        
        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
        Document document = dBuilder.newDocument();

        Element root = (Element)document.appendChild(document.createElement("R65UKG_Teremfoglalas"));
        boolean error = false;

        //Tárgyak tábla adatai
        String[] Taid ={"01","02","03"};
        String[] Tname ={"Idegen nyelv 1.","WEB technológiák","Mesterséges intelligencia alapok"};
        String[] Targykod ={"MIGENANNY1", "GEIAL331-B", "GEIAK130-B"};
        String[] Tipus ={"elmélet", "elmélet és gyakorlat", "elmélet és gyakorlat"};
        String[] Idotartam ={"2","2","2"};
        String[][] Kezdet ={{"Hétfő 12"},{"Hétfő 14","Hétfő 16"},{"Kedd 10"}};

        
        
        for(int i=0;i<Taid.length;i++){
            System.out.println(i + ". tárgy");
            Node targy = createTargyak(document, Taid[i], Tname[i], Targykod[i], Tipus[i], Idotartam[i], Kezdet[i]);
            if(targy == null){
                error=true;
                continue;
            }
            root.appendChild(targy);
        }

        //Teremfoglalás tábla adatai
        String[] REF_Taid = {"08","07","07"};
        String[] REF_Tid ={"11","08","07"};
        String[] date ={"Csütörtök 16", "Szerda 18", "Szerda 14"};
        String[] subject ={"Játék prototípusok","Vállalati információs rendszerek", "Vállalati információs rendszerek"};

        
        for(int i=0;i<REF_Taid.length;i++){
            System.out.println(i + ". teremfoglalás");
            Node teremfoglalas = createTeremfoglalas(document, REF_Taid[i], REF_Tid[i], date[i], subject[i]);
            if(teremfoglalas == null){
                error=true;
                continue;
            }
            root.appendChild(teremfoglalas);
        }

        String[] Tid ={"01","02","03"};
        String[] Ferohely ={"28","100","-"};
        String[] Vetito={"Nincs", "Nincs", "Van"};
        String[] Allapot={"-","-","-"};
        String[] Teremszam={"A5/202","30-as előadó","Inf/202"};

    
        for(int i=0;i<Tid.length;i++){
            System.out.println(i + ". terem");
            Node terem = createTerem(document, Tid[i], Ferohely[i], Vetito[i],Allapot[i],Teremszam[i]);
            if(terem == null){
                error=true;
                continue;
            }
            root.appendChild(terem);
        }
        String separation=" ";
        //Lementjük a dokumentumot
        try{
            //file = new FileWriter("XMLR65UKG1.xml",StandardCharsets.UTF_8);
            file = new FileWriter("XMLR65UKG2.xml");
            listData(document.getChildNodes(), separation);
            listData2(document.getChildNodes(), separation);
            file.close();
        }
        catch(IOException e){
            return;
        }
        
        //saveDocument(document, "XMLR65UKG1.xml");

    }
    public static Node createTerem(Document document, String Tid, String Ferohely, String Vetito, String Allapot, String Teremszam){
        Element teremElement = document.createElement("Terem");
        teremElement.setAttribute("Tid", Tid);
        Node FerohelyNode = createText(document,"Férőhely", Ferohely);
        teremElement.appendChild(FerohelyNode);
        Node VetitoNode = createText(document,"Vetitő_állapota", Vetito);
        teremElement.appendChild(VetitoNode);
        Node AllapotNode = createText(document,"Állapot", Allapot);
        teremElement.appendChild(AllapotNode);
        Node TeremszamNode = createText(document,"Teremszám", Teremszam);
        teremElement.appendChild(TeremszamNode);
        return teremElement;
    }
    public static Node createTeremfoglalas(Document document, String REF_Taid, String REF_Tid, String date, String subject){
        Element teremfoglalasElement = document.createElement("Teremfoglalás");
        teremfoglalasElement.setAttribute("REF_Taid", REF_Taid);
        teremfoglalasElement.setAttribute("REF_Tid", REF_Tid);
        Node dateNode = createText(document,"Mikor_van_lefoglalva", date);
        teremfoglalasElement.appendChild(dateNode);
        Node subjectNode = createText(document,"Milyen_tárgyra", subject);
        teremfoglalasElement.appendChild(subjectNode);
        return teremfoglalasElement;
    }

    public static Node createTargyak(Document document, String Taid, String Tname, String Targykod, String Tipus, String Idotartam, String[] Kezdet){
        Element targyElement = document.createElement("Tárgyak");
        Node nameNode = createText(document,"Név", Tname);
        targyElement.setAttribute("Taid", Taid);
        targyElement.appendChild(nameNode);
        Node TargykodNode = createText(document,"Tárgykód", Targykod);
        targyElement.appendChild(TargykodNode);
        Node TipusNode = createText(document,"Típus", Tipus);
        targyElement.appendChild(TipusNode);
        Node IdotartamNode = createText(document,"Időtartam", Idotartam);
        targyElement.appendChild(IdotartamNode);

        Node KezdetNode;
        for(int i=0; i<Kezdet.length; i++){
            KezdetNode =createText(document,"Kezdet", Kezdet[i]);
            targyElement.appendChild(KezdetNode);
        }

        return targyElement;
    }

    public static Node createText(Document document, String nodeName, String nodetext){
        if(nodetext == null || nodetext.isEmpty()){
            System.err.println("A csomópont tartalma üres!");
            return null;
        }
        Node node = document.createElement(nodeName);
        node.setTextContent(nodetext);
        return node;

    }

    public static void saveDocument(Document document, String filename) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("indent-number",new Integer(2));
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
    
    public static void listData(NodeList nodeList, String separation) throws IOException{
        separation += "\t";
        boolean text = false;
        

        if(nodeList!=null){
            for(int i=0; i<nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                text=false;
                if(node.getNodeType()==Node.ELEMENT_NODE && ((!node.getTextContent().trim().isEmpty() && node.getNodeValue()!="#text") || node.hasAttributes())){
                    file.write("\n");
                    file.write(separation + "<" + node.getNodeName());
                    NamedNodeMap attribute = node.getAttributes();
                    for(int j=0; j<attribute.getLength(); j++){
                        file.write(" " +attribute.item(j));
                    }
                    file.write("> ");
                    NodeList newNodeList = node.getChildNodes();
                    listData(newNodeList, separation);
                    if(newNodeList.item(0) instanceof Text){
                        text=true;
                    }
                }
                else if(node instanceof Text){
                    text = true;
                    String value = node.getNodeValue().trim();
                    if(value.isEmpty()){
                        continue;
                    }
                    file.write(node.getTextContent());
                    
                }
                if(node.getNodeType()==Node.ELEMENT_NODE && ((!node.getTextContent().trim().isEmpty() && node.getNodeValue()!="#text") || node.hasAttributes())){
                    if(!text){
                        file.write("\n");
                        file.write(separation + "</" + node.getNodeName()+">\n");
                    }
                    else{
                        file.write("</" + node.getNodeName() + ">\n");
                    }
                }
                
            }
        }
    }
    public static void listData2(NodeList nodeList, String separation){
        separation += "\t";
        boolean text = false;

        if(nodeList!=null){
            for(int i=0; i<nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                text=false;
                if(node.getNodeType()==Node.ELEMENT_NODE && ((!node.getTextContent().trim().isEmpty() && node.getNodeValue()!="#text") || node.hasAttributes())){
                    System.out.println();
                    System.out.print(separation + "< " + node.getNodeName());
                    NamedNodeMap attribute = node.getAttributes();
                    for(int j=0; j<attribute.getLength(); j++){
                        System.out.print(" " +attribute.item(j));
                    }
                    System.out.print(" > ");
                    NodeList newNodeList = node.getChildNodes();
                    listData2(newNodeList, separation);
                    if(newNodeList.item(0) instanceof Text){
                        text=true;
                    }
                }
                else if(node instanceof Text){
                    text = true;
                    String value = node.getNodeValue().trim();
                    if(value.isEmpty()){
                        continue;
                    }
                    System.out.print(node.getTextContent());
                    
                }
                if(node.getNodeType()==Node.ELEMENT_NODE && ((!node.getTextContent().trim().isEmpty() && node.getNodeValue()!="#text") || node.hasAttributes())){
                    if(!text){
                        System.out.println();
                        System.out.println(separation + "</ " + node.getNodeName()+">");
                    }
                    else{
                        System.out.println("</" + node.getNodeName() + ">");
                    }
                }
                
            }
        }
    }
}
