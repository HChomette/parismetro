package isepmetro;

import data.DataExtractor;
import graph.UnweightedGraph;
import graph.WeightedGraph;
import graph.BFS;
import graph.Dijkstra;
import graph.CalculateDiameters;

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
        //System.out.println(uGraph.getAdj("Opéra"));
        System.out.println(wGraph.getAdj("Opéra")); //Adjacents examples
        
        String station = "Abbesses";
        //BFS graphBFS = new BFS(uGraph, station);
        //graphBFS.printSP("Créteil-L'Echat (Hôpital Henri Mondor)");
        
        CalculateDiameters.Udiameter(uGraph);
        
        //Dijkstra graphDijkstra = new Dijkstra(wGraph, station);
        //graphDijkstra.printSP("Maison Blanche");
        
        CalculateDiameters.Wdiameter(wGraph); 
    }
}
