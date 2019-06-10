package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BFS {

	private boolean[] marked; // Has the node been visited
	private int[] previous; // Gives the previous node on the path
	private double[] distance; // Distance from original node
	private String startingPoint = ""; // on which node we operate the BFS
	private double excentricity = 0;
	private String farthestStation = ""; // used to calculate excentricity
	
	List<String> allStations = new ArrayList<String>();
	
	public BFS() {		
	}
	
	public BFS(UnweightedGraph graph, String start) {
		
		startingPoint = start;
		
		for(String station : graph.getMap().keySet()) {
			allStations.add(station);
		}
		
		List<String> visitedNodes = new ArrayList<String>();
        List<String> currentLevel = new ArrayList<String>();
        ArrayList<String> nextLevel = new ArrayList<String>();
        visitedNodes.add(start);
        currentLevel.add(start);
        int nbNodes = allStations.size();
        marked = new boolean[nbNodes];
        previous = new int[nbNodes];
        distance = new double[nbNodes];
        
        int indexOfStation = allStations.indexOf(start);
        
        marked[indexOfStation] = true;
        previous[indexOfStation] = 0;
        distance[indexOfStation] = 0;

        List<String> neighbors = new ArrayList<String>();
        boolean newNodes = true;
        while(newNodes) {
        	// In BFS we explore level per level
            for(String c : currentLevel) {
                neighbors = graph.getAdj(c); // We get the neighboring stations
                for(String n : neighbors) {
                    if(!nextLevel.contains(n) && !visitedNodes.contains(n)) {
                        nextLevel.add(n);
                        marked[allStations.indexOf(n)] = true; // the station is confirmed
                        previous[allStations.indexOf(n)] = allStations.indexOf(c);
                        distance[allStations.indexOf(n)] = distance[allStations.indexOf(c)] + 1;
                    }
                }
            }
            if(nextLevel.size() == 0) {
                newNodes = false;
            }
            //Collections.sort(nextLevel);
            visitedNodes.addAll(nextLevel);
            currentLevel.removeAll(currentLevel);
            currentLevel.addAll(nextLevel);
            nextLevel.removeAll(nextLevel);
        }
        
        calculateFarthest();
	}
	
	// Checks if there is a path to the given node
    public boolean hasPathTo(String v) {
        return marked[allStations.indexOf(v)];
    }
    // Gives the distance to the given node
    public double distTo(String v) {
        return distance[allStations.indexOf(v)];
    }
    // Prints the shortest path to the given node
    public void printSP(String destination) {
        String currentNode = destination;
        List<String> pathToNode = new ArrayList<String>();

        while (distance[allStations.indexOf(currentNode)] > 0) {
            currentNode = allStations.get(previous[allStations.indexOf(currentNode)]);
            pathToNode.add(0,currentNode);

        }
        System.out.print(" The shortest path from '" + startingPoint + "' to '" + destination + "' is ");
        for(String i : pathToNode) {
            System.out.print(i + " -> ");
        }
        System.out.println(destination + " (distance : " + distance[allStations.indexOf(destination)] + ")");
    }
	
	public double getExcentricity() {
    	return excentricity;
    }
	
	// calculates the excentricity of the station of the BFS and the station it goes to (farthest one)
	public void calculateFarthest() {
    	double excentricity = 0;
    	String farthestStation = startingPoint;
    	for(int d=0; d<distance.length; d++) {
    		if(distance[d] > excentricity) {
    			excentricity = distance[d];
    			farthestStation = allStations.get(d);
    		}
    	}
    	this.excentricity = excentricity;
    	this.farthestStation = farthestStation;
    }
	
	public String getFarthest() {
		return farthestStation;
	}

	public boolean[] getMarked() {
		return marked;
	}

	public int[] getPrevious() {
		return previous;
	}

	public double[] getDistance() {
		return distance;
	}

	public String getStartingPoint() {
		return startingPoint;
	}

	public List<String> getAllStations() {
		return allStations;
	}
	
	
}
