import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StateSet {
	
	private boolean toSplit = false;
	private List<State> listOfStates = new ArrayList<>();	
	
	void markToSplit() {
		toSplit = true;
	}
	
	void resetMark() {
		toSplit = false;
	}
	
	boolean isToSplit() {
		return toSplit;
	}
	
	void addState(State state) {
		listOfStates.add(state);
		state.setOwner(this);
		
	}
	
	List<State> getListOfStates() {
		return listOfStates;
	}
	
	/**
	 * Checks for any collisions (row by row) and updates the toSplit variable's value
	 * accordingly.
	 */
	void checkCollisions(int max) {
		
		int counter = 0;
		
		for(Outputs transitionValue : Outputs.values()) { //For each row (input)
			
			/* Base Case - Maximum Number of Transitions Values on a node */
			if(counter >= max)
				return;
			
			HashSet<StateSet> setOfResults = new HashSet<>();
			
			/* Check which set current input takes it to (Due to Dead State, will always return not null*/
			for(State node : listOfStates)  
				setOfResults.add(node.goesTo(transitionValue).getBelongingSet()); 
			
			/* If HashSet is more than 1, outputs for row are going to more than 1 set */
			if(setOfResults.size() > 1) {
				markToSplit();
				break;
			}
			
			counter++;
			
		}

	}
}