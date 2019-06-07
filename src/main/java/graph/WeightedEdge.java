package graph;

public class WeightedEdge {
    private String target;
    private double weight;

    public WeightedEdge(String target, double weight) {
        this.target = target;
        this.weight = weight;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString(){
        return (target + " : " + weight);
    }
}
