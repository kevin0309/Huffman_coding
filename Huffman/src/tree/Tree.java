package tree;

import java.util.HashMap;

public class Tree implements Comparable<Tree> {
	private Node root;
	private int weight;	//서브트리의 노드들의 cnt 총합
	private HashMap<String, String> codeMap;	

	public Tree() {
		root = null;
		weight = 0;
	}

	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public HashMap<String, String> getCodeMap() {
		return codeMap;
	}

	/**
	 * 자기자신 Tree와 입력받은 Tree를 각각 left, right child로 갖는 새로운 Tree를 생성하여 반환
	 * 새롭게 생성된 Tree의 root Node는 key값이 dummy인 dummy Node임
	 * @param tree
	 * @return
	 */
	public Tree merge(Tree tree) {
		Tree res = new Tree();
		res.root = new Node("dummy");
		res.root.setLeft(root);
		res.root.setRight(tree.root);
		res.weight = weight + tree.weight;
		return res;
	}
	
	/**
	 * Huffman code를 계산하여 Tree.codeMap에 저장
	 * 재귀 호출 함수인 calcCodeRecur를 사용
	 */
	public void calcCodeList() {
		codeMap = new HashMap<>();
		calcCodeRecur(root, "");
	}
	
	/**
	 * calcCodeList의 재귀호출함수
	 * tree의 왼쪽자식으로 갈때는 0, 오른쪽자식으로 갈때는 1을 추가한다.
	 * @param node
	 * @param resStr
	 */
	private void calcCodeRecur(Node node, String resStr) {
		if (node.getLeft() != null)
			calcCodeRecur(node.getLeft(), resStr+"0");
		if (node.getRight() != null)
			calcCodeRecur(node.getRight(), resStr+"1");
		if (!node.getKey().equals("dummy"))
			codeMap.put(node.getKey(), resStr);
	}

	@Override
	public int compareTo(Tree o) {
		if (weight > o.weight)
			return 1;
		else if (weight == o.weight)
			return 0;
		else
			return -1;
	}
}
