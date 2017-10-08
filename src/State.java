import java.util.HashMap;

public class State {

	private int nodeNumber;
	private HashMap<Outputs, State> transition = new HashMap<>();

	
	State(int nodeNumber) {
		setNodeNumber(nodeNumber);
	}
	
	int getNodeNumber() {
		return nodeNumber;
	}

	void setNodeNumber(int nodeNumber) {
		this.nodeNumber = nodeNumber;
	}
	
	void setTransition(Outputs key, State value) {
		transition.put(key, value);
	}
	
	/**
	 * 
	 * @param key
	 * @return The number of the node the chosen transition points to
	 */
	int goesTo(Outputs key) {
		return(transition.get(key).getNodeNumber());
	}
	
}
