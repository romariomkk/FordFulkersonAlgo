package Main;

import java.util.ArrayList;


public class FordAlgo {

    int[][] data;
    int vertexNum, edgeNum;
    
    ArrayList<Vertex> vertList;
    ArrayList<Edge> edgeList;
    private ArrayList<ArrayList<Vertex>> flowList = new ArrayList<>(new ArrayList<>());
    private ArrayList<ArrayList<Edge>> edgesInFlowList = new ArrayList<>(new ArrayList<>());
    
    public FordAlgo(){
        readXML();
        initVars();
    }
    
    private void readXML(){
        XMLReader reader = new XMLReader();
        vertexNum = reader.getVertexNumber();
        edgeNum = reader.getEdgeNumber();
    }
    
    private void initVars(){
        vertList = Vertex.vertList;
        edgeList = Edge.edgeList;
    }
    
    private void findMaxFlow(int from, int to){
        ArrayList<Vertex> temp = new ArrayList<>();
        passByInDepth(temp, from, to);
        passThroughFlows();
    }
    
    private void passByInDepth(ArrayList<Vertex> arr, int from, int to){
        Vertex curr = Vertex.getByNumber(from);
        curr.getAdjacentVertices().stream().forEach((vert) -> {
            ArrayList<Vertex> list = new ArrayList<>(arr);
            list.add(curr);
            int newFrom = vert.getNumber();
            if (vert.getNumber() != to){
                passByInDepth(list, newFrom, to);
            }else{
                list.add(Vertex.getByNumber(to));
                flowList.add(list);
            }
        });
    }

    private void passThroughFlows(){
        for (ArrayList<Vertex> tempList : flowList){
            
            ArrayList<Edge> edges = getFlowEdges(tempList);
            
            Edge minEdge = getMinFlow(edges);
            
            if ((minEdge.getCapacity() - minEdge.getFlow()) == 0){
                continue;
            }
            
            edges.forEach((edge)->{
                edge.setFlow(edge.getFlow() + (minEdge.getCapacity() - minEdge.getFlow()));
            });
            
            edgesInFlowList.add(edges);
        }
    }
    
    private ArrayList<Edge> getFlowEdges(ArrayList<Vertex> tempList){
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i=0; i<tempList.size()-1; i++){
            edges.add(Edge.getEdgeWhichIs(tempList.get(i).getNumber(), 
                                          tempList.get(i+1).getNumber()));
        }
        return edges;
    }
    
    private Edge getMinFlow(ArrayList<Edge> edges){
        return edges.stream().min((o1, o2)->{
            return (o2.getCapacity() - o2.getFlow()) - (o1.getCapacity() - o1.getFlow());
        }).get();
    }
    public ArrayList<ArrayList<Vertex>> getFlowList() {
        return flowList;
    }
    
    public static void main(String[] args) {
        FordAlgo ford = new FordAlgo();
        ford.findMaxFlow(2, 10);
        ford.getFlowList().forEach((arr)->System.out.println(arr));
        ford.edgesInFlowList.forEach((arr)->System.out.println(arr));
    }
    
}