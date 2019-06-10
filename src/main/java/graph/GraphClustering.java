package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class GraphClustering {

	private static int betweenessRegisterLimit = 3; // We want to keep the 3 highest betweeness
	
	public static void getClusters(WeightedGraph graph) {
		
		// We operates a Dijkstra for each station to register the paths taken
		for(String station : graph.getMap().keySet()) {
			Dijkstra currentDijkstra = new Dijkstra(graph, station);
		}		
		// We save some values to treat them later
		List<Double> highestBetweeness = new ArrayList<Double>();
		List<String> highestStart = new ArrayList<String>();
		List<String> highestEnd = new ArrayList<String>();
		double min = 0;
		
		// For each station
		for(String station : graph.getMap().keySet()) {	
			// We check all the edges
			for(WeightedEdge edge : graph.getAdj(station)) {
				List<WeightedEdge> adj = graph.getAdj(edge.getTarget()); // And we get the comeback on the same edge
				double truePassingNb = 0;
				for(WeightedEdge e : adj) {
					if(e.getTarget() == station) {
						truePassingNb+= (e.getPassedThrough() + edge.getPassedThrough())/2; // We calculate the true betweeness by adding the paths and dividing by 2 (two sides for the same edge)
					}
				}
				
				
				// This part registers the edges with the highest betweeness
				if(truePassingNb > min && !highestEnd.contains(station)) {
					
					if(highestBetweeness.size() < betweenessRegisterLimit) {
						highestBetweeness.add(truePassingNb);
						highestStart.add(station);
						highestEnd.add(edge.getTarget());
					}
					else {
						int indice = highestBetweeness.indexOf(Collections.min(highestBetweeness));
						
						highestBetweeness.set(indice,truePassingNb);
						highestStart.set(indice,station);
						highestEnd.set(indice,edge.getTarget());
					}
					

					min = Collections.min(highestBetweeness);
				}
				//System.out.println(station + " to " + edge.getTarget() + " is passed through " + (int) truePassingNb + " times.");
				if(truePassingNb >= 3000.0) {
					
				}
			}
		}
		// Present results
		System.out.println(" The edges with the highest betweenesses are : ");
		for(int i=0; i<highestBetweeness.size();i++) {
			System.out.println("\t'" + highestStart.get(i) + "' to '" + highestEnd.get(i) + "' with a betweeness of " + highestBetweeness.get(i));
		}
		System.out.println();
	}
}
