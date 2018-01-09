import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class FlightPlan {

	
	
	private HashMap<AirportNode,Connection> path= new HashMap<>();
	private AirportNode source;
	private AirportNode target;
	private float totalDistance;

	public FlightPlan(AirportNode source, AirportNode target) {
		this.source= source;
		this.target=target;
	}
	

	public void addPath(AirportNode airport, Connection connection) {
		path.put(airport, connection);
		totalDistance+=connection.getDistance();
	}
	
	
	public String toString() {
		String str = "Current Flight Plan:\n";
		for(AirportNode airport: path.keySet()) {
			str+= airport.getNodeName() +"\t\t" + path.get(airport).getName() +"\n";
		}
		return str;
	}
	public String getTotalDistance() {
		return ""+this.totalDistance;
	}
	private AirportNode getNextAirport(AirportNode source) {
		return path.get(source).getTarget();
	}
	private Connection getNextConnection(AirportNode source) {
		return path.get(source);
	}
	
	
	public ArrayList<AirportNode> getPlanAirport() {
		ArrayList<AirportNode> order = new ArrayList<>();
		order.add(source);
		AirportNode currentAirport = source;
		System.out.println("This is the order: "+order);
		
		while(order.size()<path.size()) {
			order.add(getNextAirport(currentAirport));
			currentAirport=getNextAirport(currentAirport);
		}
		return null;
	}
	public ArrayList<Connection> getPlanConnection() {
		ArrayList<Connection> order = new ArrayList<>();
		
		order.add(path.get(source));
		AirportNode currentAirport = source;
		while(order.size()<path.size()) {
			order.add(getNextConnection(currentAirport));
			currentAirport=getNextAirport(currentAirport);
		}
		return null;
	}
	
	public Connection getConnection(AirportNode source, AirportNode target) {
		
		for(Connection connection: source.getNeighbors()) {
			if(connection.getSource().equals(source) && connection.getTarget().equals(target)) {
				
				return connection;
			}
		}
		return null;
	}
	public void getShortestPath() {
			
		Queue<AirportNode>  todo= new LinkedList<>();
		LinkedList<AirportNode> visited = new LinkedList<>();
		HashMap<AirportNode,Double> distance = new HashMap<>();
		todo.add(source);
		distance.put(source, new Double(0));
		boolean done = false;
		while(!todo.isEmpty()) {
			AirportNode currentAirport = todo.peek();
			visited.add(currentAirport);
			if(currentAirport.equals(target)) {
				done =true;
				break;
			}
			else {
				
				for(AirportNode nextAirport : currentAirport.getNeighborAirports()) {
					if (!todo.contains(nextAirport) && !visited.contains(nextAirport) && !distance.containsKey(nextAirport)) {
						
						todo.add(nextAirport);
						distance.put(nextAirport, getConnection(currentAirport,nextAirport).getDistance() + distance.get(currentAirport));
					}
					
				}
			}
			todo.remove();			
		}
		if(done) {
			
			Stack<AirportNode> backtrack = new Stack<>();
			backtrack.push(target);
			AirportNode currentAirport = target;
			double step = distance.get(target);
			AirportNode minAirport = null;
			double minDistance = Double.MAX_VALUE;
			while(step-1 >=0) {
				for(AirportNode nextAirport : currentAirport.getNeighborAirports()) {
					if(distance.get(nextAirport) < minDistance) {
						minAirport = nextAirport;
						minDistance = distance.get(minAirport);
					}
				}
				step= distance.get(minAirport);
				backtrack.push(minAirport);
				currentAirport=minAirport;
			}
			List<AirportNode> finalPath = new LinkedList<>();
			while(!backtrack.isEmpty()) {
				
				finalPath.add(backtrack.pop());
			}
			for(AirportNode test: finalPath) {
				System.out.println(test.getNodeName());
			}
			
			
			
			
			
		}
		
	}
	public static void main(String[] args) {
		
		
		
		
		
	}
}
