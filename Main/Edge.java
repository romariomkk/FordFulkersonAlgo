package Main;

import java.awt.Color;
import java.util.ArrayList;


public class Edge implements Comparable{
    private int capacity;
    private int flow;
    private Vertex vertex1;
    private Vertex  vertex2;
    private Color color;
    
    static ArrayList<Edge> edgeList = new ArrayList<>();
    
    public Edge(int capacity, Vertex from, Vertex to){
        this.capacity = capacity;
        this.vertex1 = from;
        this.vertex2 = to;
        this.color = Color.BLACK;        
    }

    @Override
    public int compareTo(Object o) {
        return this.capacity - ((Edge)o).capacity;
    }
    
    public void setFlow(int flow) {
        this.flow = flow;
    }
    public int getFlow(){
        return flow;
    }
    
    public int getCapacity(){
        return capacity;
    }
        
    public Vertex getFromWhere() {
        return vertex1;
    }
    
    public Vertex getToWhere(){
        return vertex2;
    }
    
    public Color getColor() {
        return color;
    }
    
    public Color setColor(Color color) {
        this.color = color;
        return this.color;
    }
    
    public static Edge getEdgeWhichIs(int from, int to){
        Edge toReturn = null;
        for (Edge edge : edgeList){
            if ((edge.getFromWhere().getNumber() == from && edge.getToWhere().getNumber() == to)){
                toReturn = edge;
                break;
            }
        }
        return toReturn;
    }
    
    @Override
    public String toString() {
        return "Edge{" + flow + " / " + capacity + ", " + 
                vertex1.getNumber() + "->" + vertex2.getNumber() + "}";
    }
}
