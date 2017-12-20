import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class Connection{
	private String name;
	private Node source, target;
	private ArrayList<AirportNode> neighbors;
	private double distance;
	
	public Connection (String name, AirportNode source, AirportNode target, double distance) {
		this.name=name;
		this.source=source;
		this.target=target;
		this.distance=distance; 
		neighbors= new ArrayList<>();
		neighbors.add(source);
		neighbors.add(target);
	}
	public String toString() {
		return this.source.getNodeName() + "\t" + this.target.getNodeName() + "\t\t" + this.distance;
	}
	public double getDistance() {
		return this.distance;
	}
	public Node getSource() {
		return this.source;
	}
	public Node getTarget() {
		return this.target;
	}
	public String getName() {
		return this.name;
	}
	
	public ArrayList<AirportNode> getNeighbors() {
		return this.neighbors;
	}
	
	public String getNodeName() {
		return this.getName();
	}

}
