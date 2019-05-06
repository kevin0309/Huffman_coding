package tree;

public class Node {

	private String key;		//저장된 문자
	private Integer cnt;	//문자의 갯수
	private Node left;
	private Node right;
	
	public Node(String key) {
		this.key = key;
		left = null;
		right = null;
		cnt = 1;
	}

	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public String getKey() {
		return key;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void addCnt() {
		cnt++;
	}
}
