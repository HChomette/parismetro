package graph;

public class WeightedEdge {
    private String target;
    private double weight;
    private double passedThrough = 0;

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
    
    public double getPassedThrough() {
        return passedThrough;
    }

    public void setPassedThrough(double passedThrough) {
        this.passedThrough = passedThrough;
    }
    
    public void addPassedThrough() {
    	this.passedThrough+=1;
    }

    @Override
    public String toString(){
        return (target + " : " + weight);
    }
}
