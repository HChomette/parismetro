package graph;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

	
	private boolean[] marked;
	private int[] previous;
	private double[] distance;
	private String startingPoint = "";
	private double excentricity;
	private String farthestStation = "";

	private List<String> allStations = new ArrayList<String>();
	
	//Methods
	
	public Dijkstra() {
		
	}

	public Dijkstra(WeightedGraph graph, String start) {

		startingPoint = start;
		
		for(String vertex : graph.getMap().keySet()) {
			allStations.add(vertex);
		}
		
		String currentNode = start;
		List<String> toCheck = new ArrayList<String>(); // Nodes that aren't out yet
	
		int nbNodes = allStations.size();
		marked = new boolean[nbNodes];
		previous = new int[nbNodes];
		distance = new double[nbNodes];
		for(int i=0; i<distance.length; i++) {
			distance[i] = -1;
		}
	
		int indexOfStation = allStations.indexOf(start);
		
		marked[indexOfStation] = true;
		previous[indexOfStation] = 0;
		distance[indexOfStation] = 0;
		toCheck.add(currentNode);
	
		List<Integer> neighbors = new ArrayList<Integer>();
		boolean newNodes = true;
		double maxWeight = graph.maxWeight();
		int markedCounter = 1;
	
		// while there are still nodes to check
		while(newNodes) {
			// We get edges from the current node
			List<WeightedEdge> currentEdges = graph.getAdj(currentNode);
			// We remember relevant neighbors

			int currentNodeIndex = allStations.indexOf(currentNode);
			
			double currentWeight = distance[currentNodeIndex];
			// For our edges
			for(WeightedEdge checkedEdge : currentEdges) {
				String target = checkedEdge.getTarget();
				int targetIndex = allStations.indexOf(target);
				if(!marked[targetIndex] && !toCheck.contains(target)) {
					toCheck.add(target);
				}
				// If the node is neither marked nor too "far" for this path :
				if(!marked[targetIndex] && ((checkedEdge.getWeight()+currentWeight)<distance[targetIndex] || distance[targetIndex]==-1)) {
					distance[targetIndex] = checkedEdge.getWeight()+currentWeight;
					previous[targetIndex] = allStations.indexOf(currentNode);
				}
			}
			marked[currentNodeIndex] = true;
			markedCounter+=1;
			toCheck.remove(new String(currentNode));
		
			// Now we go to the shortest path in the graph that has a node that needs checking
			double shortestToCheck = maxWeight*allStations.size();
			for(int j=0; j<distance.length;j++) {
				if(!marked[j] && distance[j]!=-1 && distance[j]<shortestToCheck) {
					shortestToCheck = distance[j];
					currentNode = allStations.get(j);
				}
			}
		
			if(toCheck.size()==0) {
				newNodes = false;
			}
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
        System.out.print("The shortest path from" + startingPoint + " to " + destination + " is ");
        for(String i : pathToNode) {
            System.out.print(i + " -> ");
        }
        System.out.println(destination + " (distance : " + distance[allStations.indexOf(destination)] + ")");
    }
    
    public double getExcentricity() {
    	return excentricity;
    }
	
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
