package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import tree.HuffmanResultElement;
import tree.Node;
import tree.Tree;

public class Main {
	public static void main(String[] args) {
		File file = new File("E:/text2.txt");
		String text = readAll(file);
		HuffmanResultElement res = encodeHuffman(text);
		System.out.println(text);
		System.out.println(res.getCodeMap());
		System.out.println(res.getOutput());
		System.out.println("length : " + res.getOutput().length());
	}

	public static String readAll(File file) {
		//file의 내용을 읽어 1개의 String으로 반환, 줄바꿈은 공백으로 처리함
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
	
	/**
	 * 입력받은 문자열을 Huffman coding을 통해 변환하여 반환
	 * @param str
	 * @return
	 */
	public static HuffmanResultElement encodeHuffman(String str) {
		//입력 문자열을 확인하여 각각의 문자가 몇개있는지 확인하여 Node로 HashMap에 저장
		HashMap<String, Node> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			String key = Character.toString(str.charAt(i));
			if (map.get(key) == null)
				map.put(key, new Node(key));
			else
				map.get(key).addCnt();
		}
		
		//HashMap에 저장된 Node들을 Node가 1개인 Tree로 만들어 우선순위큐에 저장
		//우선순위는 Tree 클래스에 구현된 Comparable을 통해 정해짐
		List<Node> list = new ArrayList<>();
		list.addAll(map.values());
		PriorityQueue<Tree> pq = new PriorityQueue<>();
		for (int i = 0; i < list.size(); i++) {
			Tree tempTree = new Tree();
			tempTree.setRoot(list.get(i));
			tempTree.setWeight(list.get(i).getCnt());
			pq.add(tempTree);
		}
		
		//우선순위 큐에서 가장 weight가 작은 Tree를 두개씩 꺼내 병합
		Tree resTree = null;
		while (true) {
			Tree temp1 = pq.poll();
			Tree temp2 = pq.poll();
			if (temp2 == null)
				break;
			resTree = temp1.merge(temp2);
			pq.add(resTree);
		}
		
		//완성된 Tree를 통해 Huffman code table 작성 후 HashMap에 저장
		resTree.calcCodeList();
		HashMap<String, String> resMap = resTree.getCodeMap();
		
		//입력된 문자열을 code table을 통해 변환 (실제로는 binary로 저장되나 출력을 위해 문자열로 저장)
		String outputStr = "";
		for (int i = 0; i < str.length(); i++)
			outputStr += resMap.get(Character.toString(str.charAt(i)));
		
		HuffmanResultElement res = new HuffmanResultElement(str, outputStr, resMap);
		return res;
	}
	
	/**
	 * Greedy method가 적용되지 않은 코드
	 * @param str
	 * @return
	 */
	/*public static String encodeNotHuffman(String str) {
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
		
		HashMap<String, String> resMap = new HashMap<>();
		for (int i = 0; i < sorted.size(); i++) {
			String temp = "";
			for (int k = 0; k < i; k++)
				temp += "0";
			if (i != sorted.size() - 1)
				temp += "1";
			resMap.put(sorted.get(i), temp);
		}
		System.out.println(resMap.toString());
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			String temp = "";
			int index = sorted.indexOf(Character.toString(str.charAt(i)));
			for (int j = 0; j < index; j++)
				temp += "0";
			if (index != sorted.size() - 1)
				temp += "1";
			result += temp;
		}
		return result;
	}*/
}
