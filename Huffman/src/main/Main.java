package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import tree.HuffmanElement;
import tree.Node;
import tree.Tree;

public class Main {

	public static void main(String[] args) {
		File file = new File("E:/text2.txt");
		String text = readAll(file);
		System.out.println(text);
		
		String res = encodeHuffman(text);
		
		System.out.println(res);
		System.out.println(res.length());
		String res2 = encodeNotHuffman(text);
		System.out.println(res2);
		System.out.println(res2.length());
	}

	public static String readAll(File file) {
		String result = "";
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			while (true) {
				String temp = br.readLine();
				if (temp == null) {
					result = result.substring(0, result.length()-1);
					break;
				}
				else
					result += temp + " ";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static String encodeHuffman(String str) {
		HashMap<String, Node> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			String key = Character.toString(str.charAt(i));
			if (map.get(key) == null)
				map.put(key, new Node(key));
			else
				map.get(key).addCnt();
		}
		
		List<Node> list = new ArrayList<>();
		list.addAll(map.values());
		PriorityQueue<Tree> pq = new PriorityQueue<>();
		for (int i = 0; i < list.size(); i++) {
			Tree tempTree = new Tree();
			tempTree.setRoot(list.get(i));
			tempTree.setWeight(tempTree.getWeight()+list.get(i).getCnt());
			pq.add(tempTree);
		}
		
		Tree resTree = null;
		while (true) {
			Tree temp1 = pq.poll();
			Tree temp2 = pq.poll();
			if (temp2 == null)
				break;
			resTree = temp1.merge(temp2);
			pq.add(resTree);
		}
		resTree.calcCodeList();
		List<HuffmanElement> resList = resTree.getCodeList();
		HashMap<String, String> resMap = new HashMap<>();
		for (HuffmanElement e : resList)
			resMap.put(e.getKey(), e.getCode());
		System.out.println("\n"+resList.toString());
		String result = "";
		for (int i = 0; i < str.length(); i++)
			result += resMap.get(Character.toString(str.charAt(i)));
		return result;
	}
	
	public static String encodeNotHuffman(String str) {
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			String key = Character.toString(str.charAt(i));
			if (map.get(key) == null)
				map.put(key, 1);
			else
				map.put(key, map.get(key) + 1);
		}
		
		List<String> sorted = new ArrayList<>();
		sorted.addAll(map.keySet());
		Collections.sort(sorted, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (map.get(o1) > map.get(o2))
					return 1;
				else if (map.get(o1) == map.get(o2))
					return 0;
				else
					return -1;
			}
		});
		Collections.reverse(sorted);
		
		List<HuffmanElement> tempList = new ArrayList<>();
		for (int i = 0; i < sorted.size(); i++) {
			String temp = "";
			for (int k = 0; k < i; k++)
				temp += "0";
			if (i != sorted.size())
				temp += "1";
			tempList.add(new HuffmanElement(sorted.get(i), temp));
		}
		System.out.println(tempList.toString());
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			String temp = "";
			int index = sorted.indexOf(Character.toString(str.charAt(i)));
			for (int j = 0; j < index; j++)
				temp += "0";
			if (index != sorted.size())
				temp += "1";
			result += temp;
		}
		return result;
	}
}
