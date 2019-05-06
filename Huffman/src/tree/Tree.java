package tree;

import java.util.ArrayList;
import java.util.List;

public class Tree implements Comparable<Tree> {
	private Node root;
	private int weight;
	private List<HuffmanElement> codeList;

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
	
	public List<HuffmanElement> getCodeList() {
		return codeList;
	}

	public Tree merge(Tree tree) {
		Tree res = new Tree();
		res.root = new Node("temp");
		res.root.setLeft(root);
		res.root.setRight(tree.root);
		res.weight = weight + tree.weight;
		return res;
	}
	
	public void calcCodeList() {
		codeList = new ArrayList<>();
		calcCodeRecur(root, "");
	}
	
	private void calcCodeRecur(Node node, String resStr) {
		if (node.getLeft() != null)
			calcCodeRecur(node.getLeft(), resStr+"0");
		if (node.getRight() != null)
			calcCodeRecur(node.getRight(), resStr+"1");
		if (!node.getKey().equals("temp"))
			codeList.add(new HuffmanElement(node.getKey(), resStr));
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
