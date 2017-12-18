import java.util.ArrayList;
import java.util.Collection;

public class Connection implements Node{
	private String name;
	private Node source, target;
	private ArrayList<Node> neighbors;
	private double distance;
	
	public Connection (String name, Node source, Node target, double distance) {
		this.name=name;
		this.source=source;
		this.target=target;
		this.distance=distance;
		
		neighbors.add(source);
		neighbors.add(target);
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
	@Override
	public Collection<? extends Node> getNeighbors() {
		return this.neighbors;
	}
	@Override
	public String getNodeName() {
		return this.getName();
	}

}
