package graph;

import java.util.ArrayList;
import java.util.List;

public abstract class CalculateDiameters {

	
	public static void Udiameter(UnweightedGraph graph) {
		
		BFS mostExcentric = new BFS();
		
		for(String station : graph.getMap().keySet()) {
			BFS bfsStation = new BFS(graph, station);
			//System.out.println(station + " : " + bfsStation.getExcentricity() + " to " + bfsStation.getFarthest());
			if(bfsStation.getExcentricity() > mostExcentric.getExcentricity()) {
				mostExcentric = bfsStation;
			}
		}
	
		List<String> allStations = mostExcentric.getAllStations();
        
        double[] distance = mostExcentric.getDistance();
        int[] previous = mostExcentric.getPrevious();
        String farthestStation = mostExcentric.getFarthest();
        String currentNode = farthestStation;
        List<String> pathToNode = new ArrayList<String>();
        while (distance[allStations.indexOf(currentNode)] > 0) {
            currentNode = allStations.get(previous[allStations.indexOf(currentNode)]);
            pathToNode.add(0,currentNode);

        }
        System.out.println();
        System.out.println("Diameter of the unweighted graph :");
        System.out.println();
        System.out.println("The longest path is from "+ mostExcentric.getStartingPoint() + " to " + farthestStation + ".");
        for(int i=0; i<pathToNode.size(); i++) {
            System.out.print(pathToNode.get(i) + " ----> ");
        }
        System.out.println(farthestStation);
        System.out.println("The total distance is : " + distance[allStations.indexOf(farthestStation)] + "");
        System.out.println();
	}
	
	public static void Wdiameter(WeightedGraph graph) {
		Dijkstra mostExcentric = new Dijkstra();
		
		for(String station : graph.getMap().keySet()) {
			 Dijkstra DijkstraStation = new Dijkstra(graph, station);
			if(DijkstraStation.getExcentricity() > mostExcentric.getExcentricity()) {
				mostExcentric = DijkstraStation;
			}
		}
		
		List<String> allStations = mostExcentric.getAllStations();
        
        double[] distance = mostExcentric.getDistance();
        int[] previous = mostExcentric.getPrevious();
        String farthestStation = mostExcentric.getFarthest();
        String currentNode = farthestStation;
        List<String> pathToNode = new ArrayList<String>();
        List<Double> weightsToNode = new ArrayList<Double>();
        while (distance[allStations.indexOf(currentNode)] > 0) {
        	double weightNode = distance[allStations.indexOf(currentNode)];
            currentNode = allStations.get(previous[allStations.indexOf(currentNode)]);
            pathToNode.add(0,currentNode);
            weightsToNode.add(0,weightNode - distance[allStations.indexOf(currentNode)]);
        }
        System.out.println();
        System.out.println("Diameter of the weighted graph :");
        System.out.println();
        System.out.println("The longest path is from "+ mostExcentric.getStartingPoint() + " to " + farthestStation + ".");
        for(int i=0; i<pathToNode.size(); i++) {
            System.out.print(pathToNode.get(i) + " -- " + weightsToNode.get(i) + " units --> ");
        }
        System.out.println();
        System.out.println("The total distance is : " + distance[allStations.indexOf(farthestStation)] + "");
        System.out.println();
	}
	
}
