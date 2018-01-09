import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
	public ArrayList<AirportNode> getPlanAirport() {
		ArrayList<AirportNode> order = new ArrayList<>();
		order.add(source);
		AirportNode currentAirport = source;
		System.out.println("This is the order: "+order);
		
		while(order.size()<path.size()) {
			order.add(getNextAirport(currentAirport));
			currentAirport=getNextAirport(currentAirport);
			/*order.add Add
			 * path.get
			order.add(path.get(order.get(prev)).getTarget());
			prev++;
			*/
		}
		return order;
	}
	public ArrayList<Connection> getPlanConnection() {
		return null;
	}
	public static void main(String[] args) {
		
		
	}
}
