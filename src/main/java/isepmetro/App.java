package isepmetro;

import data.DataExtractor;
import graph.UnweightedGraph;
import graph.WeightedGraph;

import java.io.File;

/**
 * TODO : Note : dans rapport dire qu'on utilise liste & pas matrix parce que beaucoup de stations pour peu de liaisons
 */
public class App {
    private final static String DATA_PATH = "data.json"; //Data file name

    public static void main(String[] args) {
        ClassLoader classLoader = App.class.getClassLoader();
        File file = new File(classLoader.getResource(DATA_PATH).getFile());

        UnweightedGraph uGraph = DataExtractor.createUnweightedGraph(file); //Unweighted graph final object
        WeightedGraph wGraph = DataExtractor.createWeightedGraph(file); //Weighted graph final object
        System.out.println(wGraph.getAdj("Opéra")); //Adjacents examples
    }
}
