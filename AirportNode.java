import java.util.*;

public class AirportNode{

	String name;
	ArrayList<Connection> connections;
	
	public AirportNode(String name) {
		this.name=name;
	}
	
	
	public Boolean equals(AirportNode other) {
		if(this.getNodeName().equals(other.getNodeName())) {
			return true;
		}
		return false;
	}
	
	
	public String getNodeName() {
		return this.name;
	}
	
	public ArrayList<Connection> getNeighbors() {
		return this.connections;
	}
	
	
	public void setNeighbors(ArrayList<Connection> connections) {
		this.connections=connections;
	}
	
	
	public String toString()
	{
		String temp = "Airport: " + this.getNodeName() +"\nAll connections from this airport:\n" + 
	"Source\t\tDestination\tDistance (Miles)\n";
		for(int i =0; i<this.connections.size();i++) {
			temp+=this.connections.get(i).toString() +"\n";
		}
		return temp;
	}
	
	
	public Boolean hasConnection(AirportNode other) {
		for(Connection flight: this.getNeighbors())
		{
			if(flight instanceof Connection) {
				if(((Connection) flight).getTarget().equals(other)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public List<AirportNode> getShortestPathByDistance(Node t) {
		List<Node> finalList = new ArrayList<>();
		
		Queue<AirportNode> todo = new LinkedList<>();
		List<AirportNode> visited = new ArrayList<>();
		Map<AirportNode, Double> distance = new HashMap<AirportNode, Double>();
		boolean found =false;
		
		todo.add(this);
		distance.put(this, Double.valueOf(0));
		
		while(!todo.isEmpty()) {
			AirportNode currentNode = todo.peek();
			visited.add(currentNode);
			if(currentNode.equals(t)) {
				found=true;
				break;
			}
			else {
				for(int i =0;i<currentNode.getNeighbors().size();i++){
					currentNode.getNeighbors().get
					if(!todo.contains() && !visited.contains(connection) && !distance.containsKey(connection)) {
						todo.add(connection);
						distance.put(connection, Double.valueOf(distance.get(currentNode).doubleValue() +this.connections.g));
					}
				}
			}
		}
		return finalList;
	}
	
	
	/*static public List<Node> findShortestPath(Node s, Node t) {
		
		List<Node> visited = new ArrayList<>();		//Might want to make a LinkedList
		Queue<Node> todo = new LinkedList<>(); 		//Need to create a data type that implements queue, (Queue is an interface?)
		Map<Node, Integer> distance = new HashMap<>();
		boolean found = false;
		
		todo.add(s);
		
		distance.put(s, Integer.valueOf(0));
		
		//Begin searching of the graph
		while (!todo.isEmpty() && todo.peek()!=null) {
			
			Node n = todo.peek();
			visited.add(n); 
			if (n.equals(t)) {
				found = true;
				break;
			} else {
				//Add sub-nodes to todo, increase depth of search
				//System.out.println(neighbors);
				for (Node n1 : n.getNeighbors()) {
					if (!todo.contains(n1) && !visited.contains(n1) && !distance.containsKey(n1)) {   //Make sure we are not overriding values
						todo.add(n1);
						distance.put(n1, Integer.valueOf(distance.get(n).intValue() + 1));		
							
					}
				}
			}
			todo.remove();
		}
		//If found begin backtracking
		if (found) {
			Stack<Node> bPath = new Stack<Node>();
			bPath.push(t);
			Node n0 = t;
			int step = distance.get(t).intValue();
			Node min = null;
			int minD = Integer.MAX_VALUE;
			while (step - 1 >= 0) {//Get the smallest distance of the neighbors of n1
				for (Node n1 : n0.getNeighbors()) {
					if (distance.get(n1).intValue() < minD) {
						min = n1;
						minD = distance.get(n1).intValue();
					}
				}
				step = distance.get(min).intValue();
				bPath.push(min);
				n0 = min;
			}
			List<Node> fPath = new LinkedList<>();	//Reverse the the path to have it start from source and end at target
			while (!bPath.isEmpty()) {
				fPath.add(bPath.pop());
			}	
			return fPath;
		} else {
			return null;

		}
	}*/
	public static void main(String[] args)
	{
		
		/**
		 * 			  start				node1
		 * 	  	  	 /     \		   /     \
		 *  	   node1  node2		node3	node4
		 *          
		 * 			  node3
		 * 			
		 * 
		 * 
		 */
		
		AirportNode MSY = new AirportNode("New Orleans");
		AirportNode ATL = new AirportNode("Atlanta");
		AirportNode DFW = new AirportNode("Dallas");
		AirportNode ORD = new AirportNode("Chicago");
		AirportNode BOS = new AirportNode("Boston");
		AirportNode SFO = new AirportNode("San Francisco");
		AirportNode DCA = new AirportNode("Washington D.C.");
		AirportNode LHR = new AirportNode("London");
		
		ArrayList<Connection> MSY_Flights = new ArrayList();
		
		Connection MSY_ATL = new Connection("MSY->ATL",MSY, ATL, 424.73);
		Connection MSY_DFW = new Connection("MSY->DFW",MSY,DFW,447.02);
		Connection MSY_BOS = new Connection("MSY->BOS",MSY,BOS,1366.68);
		Connection MSY_LHR = new Connection("MSY-LHR",MSY,LHR,4619.33);
		
		MSY_Flights.add(MSY_ATL);
		MSY_Flights.add(MSY_DFW);
		MSY_Flights.add(MSY_BOS);
		MSY_Flights.add(MSY_LHR);
		MSY.setNeighbors(MSY_Flights);
		System.out.println(MSY);
		
		ArrayList<Connection> ATL_Flights = new ArrayList();
		Connection ATL_MSY = new Connection("ATL->MSY",ATL,MSY,424.73);
		Connection ATL_DFW = new Connection("ATL->DFW",ATL,DFW,729.79);
		Connection ATL_LHR = new Connection("ATL->LHR",ATL,LHR,4200.74);
		Connection ATL_ORD = new Connection("ATL->ORD",ATL,ORD,606.63);
		Connection ATL_DCA = new Connection("ATL->DCA",ATL,DCA,546.84);
		
		ATL_Flights.add(ATL_DCA);
		ATL_Flights.add(ATL_ORD);
		ATL_Flights.add(ATL_LHR);
		ATL_Flights.add(ATL_MSY);
		ATL_Flights.add(ATL_DFW);
		ATL.setNeighbors(ATL_Flights);
		System.out.println(ATL);
	}
	
}
