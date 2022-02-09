package etc._2022_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Baek_16437_양_구출_작전 {
	static int N;
	static long ans = 0;
	static boolean[] visited;
	static Node[] nodes;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		nodes = new Node[N+1];
		visited = new boolean[N+1];
		
		for(int i = 1; i<= N; i++) {
			nodes[i] = new Node(i);
		}
		
		for(int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			char type = st.nextToken().charAt(0);
			int cnt = Integer.parseInt(st.nextToken());
			int parent = Integer.parseInt(st.nextToken());
			
			nodes[i].cnt = type == 'S' ? cnt : -cnt;
			nodes[parent].child.add(nodes[i]);
		}
		
		
		System.out.println(dfs(1));
	}
	
	static long dfs(int idx) {
		if(nodes[idx].child.isEmpty()) {
			return nodes[idx].cnt > 0 ? nodes[idx].cnt : 0; 
		}
		
		long sum = nodes[idx].cnt;
		for(Node child : nodes[idx].child) {
			sum += dfs(child.idx);
		}
		
		return sum > 0 ? sum : 0;
	}
	
	static class Node{
		int cnt, idx;
		List<Node> child = new LinkedList<>();
		
		public Node(int idx) {
			this.idx = idx;
		}
		
		public Node(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}
	}
}
