
public class StateSet {
	
	private boolean toSplit = false;
	
	void markToSplit() {
		toSplit = true;
	}
	
	boolean isToSplit() {
		return toSplit;
	}
}
