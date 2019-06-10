package isepmetro;

import data.DataExtractor;
import graph.UnweightedGraph;
import graph.WeightedGraph;
import graph.BFS;
import graph.Dijkstra;
import graph.CalculateDiameters;
import graph.GraphClustering;

import java.io.File;

/**
 * TODO : Note : dans rapport dire qu'on utilise liste & pas matrix parce que beaucoup de stations pour peu de liaisons
 */
public class App {
    private final static String DATA_PATH = "src\\main\\resources\\data.json"; //Data file name

    public static void main(String[] args) {
        ClassLoader classLoader = App.class.getClassLoader();
        File file = new File(DATA_PATH);

        UnweightedGraph uGraph = DataExtractor.createUnweightedGraph(file); //Unweighted graph final object
        WeightedGraph wGraph = DataExtractor.createWeightedGraph(file); //Weighted graph final object
        
        String station1 = "Opéra";
        System.out.println();
        System.out.println("________________________________ Graph creation ___________________________________");
        System.out.println();
        System.out.println(" Unweighted graph of Paris stations, adjacent stations to " + station1 + " :");
        System.out.print("\t");
        System.out.println(uGraph.getAdj(station1));
        System.out.println();
        
        System.out.println(" Weighted graph of Paris stations, adjacent stations to " + station1 + " :");
        System.out.print("\t");
        System.out.println(wGraph.getAdj(station1)); //Adjacents examples
        System.out.println();
        
        System.out.println("________________________________ Shortest Path Example ____________________________");
        System.out.println();
        String station2 = "Abbesses";
        BFS graphBFS = new BFS(uGraph, station2);
        graphBFS.printSP("Créteil-L'Echat (Hôpital Henri Mondor)");
        System.out.println();
        System.out.println("________________________________ BFS Diameter _____________________________________");
 
        CalculateDiameters.Udiameter(uGraph);
        
        System.out.println("________________________________ Dijkstra Diameter _________________________________");
        //Dijkstra graphDijkstra = new Dijkstra(wGraph, station);
        //graphDijkstra.printSP("Maison Blanche");
        CalculateDiameters.Wdiameter(wGraph); 
        System.out.println("________________________________ Betweeness ________________________________________");
        System.out.println();
        WeightedGraph clustersGraph = DataExtractor.createWeightedGraph(file);
        GraphClustering.getClusters(clustersGraph);
    }
}
