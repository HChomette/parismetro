package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WeightedGraph implements Graph{
    private Map<String, List<WeightedEdge>> adj = new TreeMap<>();

    public WeightedGraph() {}

    public void addVertex(String label) {
        if(adj.containsKey(label)) return;
        adj.put(label, new ArrayList<WeightedEdge>());
    }

    public void addEdge(String label1, String label2, double weight){
        adj.get(label1).add(new WeightedEdge(label2, weight));
        adj.get(label2).add(new WeightedEdge(label1, weight));
    }

    public List<WeightedEdge> getAdj(String label){
        return adj.get(label);
    }

    public boolean containsVertex(String label){
        return adj.containsKey(label);
    }
    
    public Map<String, List<WeightedEdge>> getMap(){
    	return adj;
    }

    public double maxWeight() {
    	double maxWeight = 0;   	
    	for(String vertex : adj.keySet()) {   		
    		for(WeightedEdge edge : adj.get(vertex)) {
    			if(edge.getWeight()>maxWeight) {
    				maxWeight = edge.getWeight();
    			}
    		}
    	}
    	return maxWeight;
    }
}
