package tree;

public class HuffmanElement {
	private String key;
	private String code;
	
	public HuffmanElement(String key, String code) {
		super();
		this.key = key;
		this.code = code;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getCode() {
		return code;
	}
	
	@Override
	public String toString() {
		return "['"+key+"', "+code+"] ";
	}
}
