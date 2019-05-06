package tree;

import java.util.HashMap;

public class HuffmanResultElement {

	private String input;
	private String output;
	private HashMap<String, String> codeMap;
	
	public HuffmanResultElement(String input, String output, HashMap<String, String> codeMap) {
		super();
		this.input = input;
		this.output = output;
		this.codeMap = codeMap;
	}
	
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public HashMap<String, String> getCodeMap() {
		return codeMap;
	}
	public void setCodeMap(HashMap<String, String> codeMap) {
		this.codeMap = codeMap;
	}
}
