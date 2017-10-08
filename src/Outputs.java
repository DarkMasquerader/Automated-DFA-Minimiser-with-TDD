
public enum Outputs {
	A("a"),B("b"),C("c"),D("d");
	
	private String value;
	
	private Outputs(String str) {
		setValue(str);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
