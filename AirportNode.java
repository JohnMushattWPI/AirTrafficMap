import java.util.*;

public class AirportNode implements Node{

	String name;
	ArrayList<Connection> connections;
	
	public AirportNode(String name) {
		this.name=name;
	}
	
	public String getNodeName() {
		return this.name;
	}
	
	public Collection<? extends Node> getNeighbors() {
		return this.connections;
	}
	public void setNeighbors(ArrayList<Connection> connections) {
		this.connections=connections;
	}
	public String toString()
	{
		return this.getNodeName();
	}
	public static  List<Node> getShortestPathByWeight(Node s, Node t) {
		List<Node> finalList = new ArrayList<>();
		
		Queue<Node> todo = new LinkedList<>();
		List<Node> visited = new ArrayList<>();
		Map<Node, Integer> weightMap = new HashMap<Node, Integer>();
		
		
		todo.add(s);
		
		return finalList;
	}
	static public List<Node> findShortestPath(Node s, Node t) {
		
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
	}
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
		
		AirportNode node3 = new AirportNode("New York");
		AirportNode node4 = new AirportNode("Boston");
		
		AirportNode node5 = new AirportNode("San Fran");
		AirportNode node6 = new AirportNode("Washington D.C.");
		
		AirportNode node7 = new AirportNode("London");
		
		ArrayList<Connection> MSY_Flights = new ArrayList();
		Connection MSY_ATL = new Connection("MSY->ATL",MSY, ATL, 424.73);
		Connection MSY_DFW = new Connection("MSY->DFW",MSY,DFW,447.02);
		
		ArrayList<AirportNode> node1Group = new ArrayList();
		
		node1Group.add(node3);
		node1Group.add(node4);
		node1.setNeighbors(node1Group);
		
		ArrayList<AirportNode> node2Group = new ArrayList();
		
		node2Group.add(node4);
		node2Group.add(node5);
		node2.setNeighbors(node2Group);
		
		ArrayList<AirportNode> node3Group = new ArrayList();
		
		node3Group.add(node6);
		node3Group.add(node7);
		node3.setNeighbors(node3Group);
		
		ArrayList<AirportNode> node4Group = new ArrayList();
		
		node4Group.add(node7);
		node4.setNeighbors(node4Group);
		
		ArrayList<AirportNode> node5Group = new ArrayList();
		
		node5Group.add(node3);
		node5.setNeighbors(node5Group);

		ArrayList<AirportNode> node6Group = new ArrayList();
		
		node6Group.add(node7);
		node6.setNeighbors(node6Group);
		
		findShortestPath(node2,node7);
	}
	
}
