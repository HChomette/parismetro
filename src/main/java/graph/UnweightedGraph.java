package graph;

import java.util.*;

/**
 * Simple unweighted graph class
 */
public class UnweightedGraph implements Graph {
    private Map<String, List<String>> adj = new TreeMap<>();

    public UnweightedGraph() {}

    public void addVertex(String label) {
        if(adj.containsKey(label)) return;
        adj.put(label, new ArrayList<String>());
    }

    public void addEdge(String label1, String label2){
        adj.get(label1).add(label2);
        adj.get(label2).add(label1);
    }

    public List<String> getAdj(String label){
        return adj.get(label);
    }

    public boolean containsVertex(String label){
        return adj.containsKey(label);
    }
    
    public Map<String, List<String>> getMap(){
    	return adj;
    }
}
