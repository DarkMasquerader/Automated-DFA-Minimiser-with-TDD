import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestStateSet {

	State state0, state1, state2, state3, dead;
	List<State> stateCollection = new ArrayList<>();
	
	@Before
	public void setUp() {
		
		state0 = new State(0);
		state1 = new State(1);
		state2 = new State(2);
		state3 = new State(3);
		dead = new State(666);
		
		stateCollection.add(state0);
		stateCollection.add(state1);
		stateCollection.add(state2);
		stateCollection.add(state3);
		stateCollection.add(dead);
	}

	/* Could Transfer to Project */
	public void mapToDead(int numberOutputs) {
		
		for(State s : stateCollection) {
			int counter = 0;
			HashMap<Outputs, State> temp = s.getTransitions();
			
			for(Outputs transValue : Outputs.values()) {
				
				/* Base Case - Number of output values */
				if(counter >= numberOutputs)
					break;
				
				/* If there isn't a key, there isn't a transition and will thus set to point to dead state */
				if(!temp.containsKey(transValue))
					s.setTransition(transValue, dead);
				
				counter++;
			}
			
			
		}
		
	}
	
	@Test
	public void collisionTest1() {
		int maxTransitions = 2;
		
		StateSet accepting = new StateSet();
		StateSet others = new StateSet();
			
		state0.setTransition(Outputs.A, state1);
		state1.setTransition(Outputs.A, state2);
		state1.setTransition(Outputs.B, state3);
		mapToDead(maxTransitions);
		
		/* Add Accepting States */
		accepting.addState(state1);
		accepting.addState(state3);
		
		/* Add Non-Accepting States */
		others.addState(state0);
		others.addState(state2);
		others.addState(dead);
		
		accepting.checkCollisions(maxTransitions);
		others.checkCollisions(maxTransitions);

		assertTrue(accepting.isToSplit() == true);
		assertTrue(others.isToSplit() == true);
		
	}
	
	@Test
	public void collisionTest2() {
		int maxTransitions = 2;
		
		stateCollection.remove(state0);
		
		StateSet accepting = new StateSet();
		StateSet others = new StateSet();
			
		state1.setTransition(Outputs.A, state2); 

		mapToDead(maxTransitions);
		
		
		
		/* Add Accepting States */
		accepting.addState(state1);
		accepting.addState(state3);
		
		/* Add Non-Accepting States */
		others.addState(state2);
		others.addState(dead);
		
		accepting.checkCollisions(maxTransitions);
		others.checkCollisions(maxTransitions);

		assertTrue(accepting.isToSplit() == false);
		assertTrue(others.isToSplit() == false);
		
	}

}
