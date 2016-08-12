public class KeyValuePair {
	private final int key;
	private final String value;

	public KeyValuePair(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public KeyValuePair() {
		this.key = 0;
		this.value = "";
	}

	public int getKey()   {    return key;    }

	public String toString() {    return value;  }
}