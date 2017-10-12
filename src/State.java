import java.util.HashMap;

public class State {

	private int nodeNumber; //Dead State Special Value: 666 [Needs a method for it to point to itself]
	private HashMap<Outputs, State> transition = new HashMap<>();
	private StateSet belongsTo;
	private StateSet newSet = null; //Used during Set Splitting (Once node has been moved)
	
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
	
	HashMap<Outputs, State> getTransitions() {
		return transition;
	}
	
	/**
	 * 
	 * @param key
	 * @return The number of the node the chosen transition points to
	 */
	State goesTo(Outputs key) {
		return(transition.get(key));
	}
	
	StateSet getBelongingSet() {
		return belongsTo;
	}

	void setOwner(StateSet stateSet) {
		belongsTo = stateSet;
	}
	
	void setFutureOwner(StateSet stateset) { 
		newSet = stateset;
	}
	
	//Is run after split has been done for each state
	public void updateSet() {
		belongsTo = newSet;
		newSet = null;
	}
	
	public String toString() {
		return "Node Number: " + getNodeNumber() + "\nBelongsTo: " + getBelongingSet() + "\n";
	}
}