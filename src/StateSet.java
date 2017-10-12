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
	
	
	private boolean checkRowCollision(Outputs transition) {
		
		HashSet<StateSet> setOfResults = new HashSet<>();
		
		for(State node : listOfStates)  
			setOfResults.add(node.goesTo(transition).getBelongingSet()); 
		
		if(setOfResults.size() > 1) { //Om det finns någon kollision i en rad
			
			/* Skaffas precis behövde antal StateSets */
			List<StateSet> temp = new ArrayList<>();
			
			for(int x = 0; x < setOfResults.size(); x++) {
				temp.add(new StateSet());
			}

			
			/* För varje hittade state, så kommer hittas alla andra transitioner */
			int index = 0;
			for(StateSet set : setOfResults) {
				StateSet tempStateSet = temp.get(index);
				
				//For every transition that landed in state s(set)
				
				for(State node : listOfStates ) { //For each node in set
					
					State destination = node.goesTo(transition);
					if(destination.getBelongingSet().equals(set)) {
						tempStateSet.addState(node);
						/* update belongs to too early*/
					}
				}
				
				index++;
			}
			
			
			return true;
		} else { //Om det inte finns någon kollision i en rad
			return false;
		}
		
		
	}
	
	
	List<StateSet> splitSet(int max){ 
		
		int counter = 0;
		
		for(Outputs transitionValue : Outputs.values()) { 
			
			if(counter >= max) 
				break;
			
			//Om det här metoden dela upp en uppsättning så måste vi returna
			checkRowCollision(transitionValue);
			
			counter++;
		}
		
		return null;
	}
}