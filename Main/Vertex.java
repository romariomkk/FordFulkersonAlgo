package Main;

import java.util.ArrayList;

public class Vertex implements Comparable{
    
    private final int number;
    private ArrayList<String> routeToMe = new ArrayList<>();
    private int weight = Integer.MAX_VALUE;
    private boolean isTagged = false;
    private ArrayList<Vertex> adjacentVertices = new ArrayList<>();
    private ArrayList<Edge> adjacentEdges = new ArrayList<>();
    
    static ArrayList<Vertex> vertList = new ArrayList<>();
    
    public Vertex(int number){
        this.number = number;
    }
    
    public void setTagged(boolean tagged) {
        this.isTagged = tagged;
    }

    public boolean isTagged() {
        return isTagged;
    }   

    public void addAdjacentVertex(Vertex vertex){
        adjacentVertices.add(vertex);
    }
    
    public ArrayList<Vertex> getAdjacentVertices(){
        return adjacentVertices;
    }
    
    public ArrayList<String> getRouteToMe() {
        return routeToMe;
    }

    public int getWeight() {
        return weight;
    }

    public int getNumber(){
        return number;
    }
    
    public static Vertex getByNumber(int number){      
        for (Vertex vert : vertList){
            if (vert.getNumber() == number)
                return vert;
        }
        throw new NullPointerException();
        //return vertList.stream().filter((vert)->vert.getNumber() == number).findFirst().get();
    }
    
    public Vertex addRouteToMe(ArrayList<String> routeToMe) {
        routeToMe.stream().forEach((elem) -> {
            addRouteToMe(elem);
        });
        return this;
    }
    public Vertex addRouteToMe(String routeToMe) {
        this.routeToMe.add(routeToMe);
        return this;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void addAdjacentEdge(Edge edge){
        adjacentEdges.add(edge);
    }
    
    public ArrayList<Edge> getAdjacentEdges(){
        return adjacentEdges;
    }
    
    @Override
    public String toString() {
        return "Vertex{" + number + '}';
    }

    @Override
    public int compareTo(Object o) {
        return this.getWeight() - ((Vertex)o).getWeight();
    }
    
}
