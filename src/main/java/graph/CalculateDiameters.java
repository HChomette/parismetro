package graph;

import java.util.ArrayList;
import java.util.List;

public abstract class CalculateDiameters {

	// Calculates the diameter for an unweighted graph
	public static void Udiameter(UnweightedGraph graph) {
		
		BFS mostExcentric = new BFS();
		
		// We'll do a BFS on each station
		for(String station : graph.getMap().keySet()) {
			BFS bfsStation = new BFS(graph, station);
			
			// We save the BFS giving us the highest value of excentricity (which is the diameter)
			if(bfsStation.getExcentricity() > mostExcentric.getExcentricity()) {
				mostExcentric = bfsStation;
			}
		}
			
		// We now want to print the path of the diameter
		List<String> allStations = mostExcentric.getAllStations();
        
        double[] distance = mostExcentric.getDistance();
        int[] previous = mostExcentric.getPrevious();
        String farthestStation = mostExcentric.getFarthest();
        String currentNode = farthestStation;
        List<String> pathToNode = new ArrayList<String>();
        
        // We register all stations we pass through
        while (distance[allStations.indexOf(currentNode)] > 0) {
            currentNode = allStations.get(previous[allStations.indexOf(currentNode)]);
            pathToNode.add(0,currentNode);
        }
        
        // Here's the output
        System.out.println();
        System.out.println("Diameter of the unweighted graph :");
        System.out.println();
        System.out.println(" The longest path is from '"+ mostExcentric.getStartingPoint() + "' to '" + farthestStation + "'.");
        System.out.print("\t");
        for(int i=0; i<pathToNode.size(); i++) {
            System.out.print(pathToNode.get(i) + " --> ");
        }
        System.out.println(farthestStation);
        System.out.println(" The total distance is : " + distance[allStations.indexOf(farthestStation)] + "");
        System.out.println();
	}
	
	// Calculates the diameter for an weighted graph
	public static void Wdiameter(WeightedGraph graph) {
		
		Dijkstra mostExcentric = new Dijkstra();
		
		// We'll do a Dijkstra on each station
		for(String station : graph.getMap().keySet()) {
			 Dijkstra DijkstraStation = new Dijkstra(graph, station);
			 
			// We save the Dijkstra giving us the highest value of excentricity (which is the diameter)
			if(DijkstraStation.getExcentricity() > mostExcentric.getExcentricity()) {
				mostExcentric = DijkstraStation;
			}
		}
		
		// We now want to print the path of the diameter
		List<String> allStations = mostExcentric.getAllStations();
        
        double[] distance = mostExcentric.getDistance();
        int[] previous = mostExcentric.getPrevious();
        String farthestStation = mostExcentric.getFarthest();
        String currentNode = farthestStation;
        List<String> pathToNode = new ArrayList<String>();
        List<Double> weightsToNode = new ArrayList<Double>();
        
        // We register all stations we pass through
        while (distance[allStations.indexOf(currentNode)] > 0) {
        	double weightNode = distance[allStations.indexOf(currentNode)];
            currentNode = allStations.get(previous[allStations.indexOf(currentNode)]);
            pathToNode.add(0,currentNode);
            weightsToNode.add(0,weightNode - distance[allStations.indexOf(currentNode)]);
        }
        
        System.out.println();
        System.out.println("Diameter of the weighted graph :");
        System.out.println();
        System.out.println(" The longest path is from '"+ mostExcentric.getStartingPoint() + "' to '" + farthestStation + "'.");
        System.out.print("\t");
        for(int i=0; i<pathToNode.size(); i++) {
            System.out.print(pathToNode.get(i) + " -- " + String.format("%.2f", weightsToNode.get(i)) + " meters --> ");
        }
        System.out.println();
        System.out.println(" The total distance is : " + String.format("%.2f", distance[allStations.indexOf(farthestStation)]) + " meters");
        System.out.println();
	}
	
}
