import java.util.*;
public interface Node {

	public Collection<? extends Node> getNeighbors();
	
	public String getNodeName();
	
	public String toString();
	
	
}
