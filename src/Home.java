import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Can Alter for more than 3 transition values later */

public class Home {
	
	/* Manages all of the sets */
	static List<StateSet> listOfSets = new ArrayList<>();
	
	/* Initial 2 Sets: S0 and S1. Will be redundant after first split */
	static StateSet accepting = new StateSet();
	static StateSet other = new StateSet();

	/* Holds Number of Nodes - For minor error handling */
	static int maxNodes;
	
	
	
	
	/* Use args for state input, separate via common */
	public static void main(String[] args) {

	/* Adding S0 and S1 to listOfStates */
	listOfSets.add(accepting);
	listOfSets.add(other);
	
	handleInput(args);
	getTransitions();
		
	accepting.printInfo();
	
	}
	
	
	private static void getTransitions() {

		Scanner scanner = new Scanner(System.in);
		
		/* Adding All States to a single list for efficiency */
		List<State> listOfStates= new ArrayList<>();
		
		List<State> temp1 = accepting.getListOfStates();
		List<State> temp2 = other.getListOfStates();
		
		for(State state : temp1)
			 listOfStates.add(state);
		
		for(State state : temp2)
			 listOfStates.add(state);
		
		maxNodes = listOfStates.size();
		
		/* Transition input for each state*/
		for(State state : listOfStates) {
			
			String tempHolder;
			int[] transitionNodeNumber = new int[3];
			
			/* Getting User Input */
			System.out.println("a b c Transition for Node #" + state.getNodeNumber());
			tempHolder = scanner.nextLine();
			
			/* Checking User Input */
			while(!isValidInput(tempHolder, transitionNodeNumber)) {
				System.out.println("a b c Transition for Node #" + state.getNodeNumber());
				System.out.println("For no transition, enter \"666\". ");
				tempHolder = scanner.nextLine();
			}
			
			/* Adding Information to States */
			state.setTransition(Outputs.A, getNode(transitionNodeNumber[0], listOfStates));
			state.setTransition(Outputs.B, getNode(transitionNodeNumber[1], listOfStates));
			state.setTransition(Outputs.C, getNode(transitionNodeNumber[2], listOfStates));
			
			
			
		}
		
		scanner.close();
		
	}

	private static State getNode(int value, List<State> list) {
		
		for(State state : list) {
			
			if(state.getNodeNumber() == value)
				return state;
			
		}
		
		return null;
		
	}

	private static boolean isValidInput(String input, int[] intAnswers) {
		
		String[] answers = input.split(" ");
		
		if(answers.length != 3)
			return false;
		
		
		
		try {
			
			for(int x = 0; x < 3; x++)
				intAnswers[x] = Integer.parseInt(answers[x]);
			
		} catch (NumberFormatException e) {
			
			System.out.println("Non-numerical Value Enteres for transitions");
			System.out.println(e);
			
		}
		
		
		for(int x : intAnswers) {
			
			if(x == 666)
				return true;
			
			if(x > maxNodes -2 || x < 0) { //Minus 2 for 0 start and Dead State
				
				System.out.println("Invalid Node Number Entered");
				return false;
			}
		}
			return true;
		
		
		
	}


	/**
	 * Handles initial state input. All numbers prior to ',' in args
	 * belong to the accepting state.
	 * @param args Contains numbers and a comma which is used to separate the 
	 * initial accepting and non-accepting states
	 */
	private static void handleInput(String[] args) {
		
		
		/* Handling Args Input */
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
		
		other.addState(new State(666));
		
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
