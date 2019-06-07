package data;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import graph.Graph;
import graph.UnweightedGraph;
import graph.WeightedGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class DataExtractor {

    private static final int MAX_METRO = 14; //Number of metro lines

    public static UnweightedGraph createUnweightedGraph(File file){
        UnweightedGraph res = new UnweightedGraph();
        createGraphFromJSON(file, false, res);
        return res;
    }

    public static WeightedGraph createWeightedGraph(File file){
        WeightedGraph res = new WeightedGraph();
        createGraphFromJSON(file, true, res);
        return res;
    }

    //Not very elegant, but allows us to not repeat code
    //Relies on casting graph to either UnweightedGraph or WeightedGraph
    //We rely a lot on casting for the JSON products, because there are a lot of different sub-objects
    private static void createGraphFromJSON(File file, boolean weighted, Graph graph) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e){
            e.printStackTrace();
            return;
        }
        Gson gson = new Gson();
        LinkedTreeMap<String, Object> json = gson.fromJson(br, LinkedTreeMap.class); //Global json
        LinkedTreeMap<String, LinkedTreeMap> lignesJson = (LinkedTreeMap)json.get("lignes");
        LinkedTreeMap<String, LinkedTreeMap> stationsJson = (LinkedTreeMap)json.get("stations");

        ArrayList<ArrayList>  lignes = new ArrayList<>(); //ArrayList of ArrayLists containing every line with every station
        for(int i = 1; i <= MAX_METRO; i++){
            ArrayList<ArrayList> arrets = (ArrayList)lignesJson.get(Integer.toString(i)).get("arrets");
            lignes.add(arrets.get(0));
        }

        LinkedTreeMap precedent = new LinkedTreeMap(); //We keep the precedent station to create the edge
        for(int i = 0; i < lignes.size(); i++) {
            for(int j = 0; j < lignes.get(i).size(); j++){
                int currentNb = Integer.parseInt((String)lignes.get(i).get(j));
                LinkedTreeMap currentStation = stationsJson.get(Integer.toString(currentNb));
                String currentName = (String)currentStation.get("nom");

                if(!graph.containsVertex(currentName)){ //If station doesn't exist already, we add it
                    graph.addVertex(currentName);
                }

                if(j > 0){ //We create the edge with the precedent station, so not for the first one
                    if(weighted){
                        double distance = calculateDistance(Double.parseDouble((String)currentStation.get("lng")),
                                Double.parseDouble((String)currentStation.get("lat")),
                                Double.parseDouble((String)precedent.get("lng")),
                                Double.parseDouble((String)precedent.get("lat")));
                        ((WeightedGraph)graph).addEdge(currentName, (String)precedent.get("nom"), distance);
                    } else {
                        ((UnweightedGraph)graph).addEdge(currentName, (String)precedent.get("nom"));
                    }
                }
                precedent = currentStation;

            }
        }
    }

    /**
     * Calculate distance between two points
     */
    private static double calculateDistance(double xA, double yA, double xB, double yB){
        double ac = Math.abs(yB - yA);
        double cb = Math.abs(xB - xA);

        return Math.hypot(ac, cb);
    }

}