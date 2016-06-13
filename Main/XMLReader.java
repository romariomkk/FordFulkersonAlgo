package Main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLReader {
    
    int vertexNum;
    int edgeNum;
    
    public XMLReader(){
        try {
            File xmlFile = new File("src/XmlPack/Dijcstra_graph_data.xml");
            //File xmlFile = new File("src/XmlPack/source200.xml");
            
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(xmlFile);
            
            doc.normalize();
            
            vertexNum = Integer.parseInt(doc.getElementsByTagName("vert").item(0).getAttributes().getNamedItem("count").getNodeValue());
            edgeNum = Integer.parseInt(doc.getElementsByTagName("edg").item(0).getAttributes().getNamedItem("count").getNodeValue());
            
            NodeList vertData = doc.getElementsByTagName("vertex");
            for (int index=0; index<vertData.getLength(); index++){
                Node itemNode = vertData.item(index);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE){
                    Element elem = (Element)itemNode;
                    Vertex toAdd = new Vertex(Integer.parseInt(elem.getAttribute("id")));
                    Vertex.vertList.add(toAdd);
                }
            }
            
            NodeList edgeData = doc.getElementsByTagName("edge");
            for (int i=0; i<edgeData.getLength(); i++){
                Node itemNode = edgeData.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE){
                    Element elem = (Element)itemNode;
                    int capacity = Integer.parseInt(elem.getAttribute("flow"));
                    Vertex from = Vertex.getByNumber(Integer.parseInt(elem.getAttribute("from")));
                    Vertex to = Vertex.getByNumber(Integer.parseInt(elem.getAttribute("to")));
                    Edge toAdd = new Edge(capacity, from, to);
                    Edge.edgeList.add(toAdd);
                    from.addAdjacentVertex(to);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getEdgeNumber() {
        return edgeNum;
    }
    
    public int getVertexNumber(){
        return vertexNum;
    }
    
}
