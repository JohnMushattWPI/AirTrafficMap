import java.util.*;

public class BasicNode implements Node{

	String name;
	int weight;
	ArrayList<BasicNode> neighbors;
	
	public BasicNode(String name, int weight) {
		this.name=name;
		this.weight=weight;
	}
	
	public String getNodeName() {
		return this.name;
	}
	public int getNodeWeight() {
		return this.weight;
	}
	public Collection<BasicNode> getNeighbors() {
		return this.neighbors;
	}
	public void setNeighbors(ArrayList<BasicNode> neighbors) {
		this.neighbors=neighbors;
	}
	public String toString()
	{
		return this.getNodeName();
	}
	static public List<Node> getShortestPathByWeight(Node s, Node t) {
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
		
		BasicNode start = new BasicNode("start", 0);
		
		BasicNode node1 = new BasicNode("node 1", 1);
		BasicNode node2 = new BasicNode("node 2", 2);
		
		BasicNode node3 = new BasicNode("node 3", 4);
		BasicNode node4 = new BasicNode("node 4", 5);
		
		BasicNode node5 = new BasicNode("node 5", 7);
		BasicNode node6 = new BasicNode("node 6", 6);
		
		BasicNode node7 = new BasicNode("node 7", 10);
		
		ArrayList<BasicNode> startGroup = new ArrayList();
		
		startGroup.add(node1);
		startGroup.add(node2);
		start.setNeighbors(startGroup);
		
		ArrayList<BasicNode> node1Group = new ArrayList();
		
		node1Group.add(node3);
		node1Group.add(node4);
		node1.setNeighbors(node1Group);
		
		ArrayList<BasicNode> node2Group = new ArrayList();
		
		node2Group.add(node4);
		node2Group.add(node5);
		node2.setNeighbors(node2Group);
		
		ArrayList<BasicNode> node3Group = new ArrayList();
		
		node3Group.add(node6);
		node3Group.add(node7);
		node3.setNeighbors(node3Group);
		
		ArrayList<BasicNode> node4Group = new ArrayList();
		
		node4Group.add(node7);
		node4.setNeighbors(node4Group);
		
		ArrayList<BasicNode> node5Group = new ArrayList();
		
		node5Group.add(node3);
		node5.setNeighbors(node5Group);

		ArrayList<BasicNode> node6Group = new ArrayList();
		
		node6Group.add(node7);
		node6.setNeighbors(node6Group);
		
		findShortestPath(node2,node7);
	}
	
}
