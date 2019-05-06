package tree;

public class Node implements Comparable<Node> {

	private String key;
	private Integer cnt;
	private Node left;
	private Node right;
	
	public Node(String key) {
		super();
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

	@Override
	public int compareTo(Node o) {
		if (cnt > o.cnt)
			return 1;
		else if (cnt == o.cnt)
			return 0;
		else
			return -1;
	}
}
