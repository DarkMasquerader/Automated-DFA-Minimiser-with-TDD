import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StateSet {
	
	private boolean toSplit = false;
	private List<State> listOfStates = new ArrayList<>();	
	
	public void printInfo() {
	
		for(State node : listOfStates) 
			System.out.println(node.toString());
		
	}
	
	public void updateStates() {
		for(State node : listOfStates)
			node.updateSet();
		
		toSplit = false;
	}
	
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
	
	/* Used during the splitting process */
	void addFutureState(State state) {
		listOfStates.add(state);
		state.setFutureOwner(this);
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
	
	private List<StateSet> checkRowCollision(Outputs transition) {
		
		HashSet<StateSet> setOfResults = new HashSet<>();
		
		for(State node : listOfStates)  
			setOfResults.add(node.goesTo(transition).getBelongingSet()); 
		
		/* If there's a collision on a row */
		if(setOfResults.size() > 1) { 
			
			/* Creates the precise amount of new sets needed*/
			List<StateSet> temp = new ArrayList<>();
			
			for(int x = 0; x < setOfResults.size(); x++) {
				temp.add(new StateSet());
			}

			
			/* Splitting state via found states in row. Same states go together */
			int index = 0;
			for(StateSet set : setOfResults) {
				
				StateSet tempStateSet = temp.get(index);
				
				for(State node : listOfStates ) { //For each node in set
					
					State destination = node.goesTo(transition);
					
					if(destination.getBelongingSet().equals(set)) 
						tempStateSet.addFutureState(node);
						
				}
				
				index++;
			}
			
			return temp; 
			
		} else { //If there isn't a collision in the set
			return null;
		}
		
	}
	
	List<List<StateSet>> splitSet(int max){ 
		
		int counter = 0;
		
		List<List<StateSet>> listOfLists = new ArrayList<>();
		
		for(Outputs transitionValue : Outputs.values()) { 
			
			if(counter >= max) 
				break;
			
			/* If a set is divided, we will return new sets */
			List<StateSet> object = checkRowCollision(transitionValue);
			if(object != null) {
				listOfLists.add((List<StateSet>)object);
				break;
			}
				
			counter++;
		}
		
		return listOfLists;
	}
}