import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Danté G
 *
 */
public class TestSets {

	State state0, state1;
	
	@Before
	public void setUp() {
		state0 = new State(0);
		state1 = new State(1);
	}

	
	@Test
	/* Testing HashMap */
	public void testHashMap() {
		state0.setTransition(Outputs.A, state1);
		assertTrue(state0.goesTo(Outputs.A).getNodeNumber() == 1);
	}

}
