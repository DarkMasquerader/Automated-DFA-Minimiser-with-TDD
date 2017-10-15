import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class Home {
	
	/* Manages all of the sets */
	static List<StateSet> listOfStates = new ArrayList<>();
	
	/* Initial 2 Sets: S0 and S1. Will be redundant after first split */
	static StateSet accepting = new StateSet();
	static StateSet other = new StateSet();

	
	/* Use args for state input, separate via common */
	public static void main(String[] args) {
		
	handleInput(args);
		
	/* Adding S0 and S1 to listOfStates */
	listOfStates.add(accepting);
	listOfStates.add(other);
		
		
	}
	
	
	/**
	 * Handles initial state input. All numbers prior to ',' in args
	 * belong to the accepting state.
	 * @param args Contains numbers and a comma which is used to seperate the 
	 * initial accepting and non-accepting states
	 */
	private static void handleInput(String[] args) {
		
		boolean addingAccepting = true;
		
		for(String x : args) {
			
			if(addingAccepting) {
			
				if(x.equals(","))
					addingAccepting = false;
				else
					accepting.addState(new State(Integer.parseInt(x)));
				
			} else {
				
				other.addState(new State(Integer.parseInt(x)));
				
			}
			
			
		}
		
	}

	private static List<StateSet> unpack(List<List<List<StateSet>>> listCeption) {
		
		
		List<List<List<StateSet>>> listOfLists = new ArrayList<>();
		//listOfLists.add(accepting.splitSet(maxTransitions));
		//listOfLists.add(others.splitSet(maxTransitions));
		
		assertTrue(listOfLists.size() == 2);
		
		int counter = 0;
		for(List<List<StateSet>> lvl1 : listOfLists)
			for(List<StateSet> lvl2 : lvl1) 
				for(StateSet lvl3 : lvl2) {
					counter++;
					lvl3.updateStates();
					lvl3.printInfo();
			}
			
		assertTrue(counter == 4);
		
		return null;
	}
	
}
