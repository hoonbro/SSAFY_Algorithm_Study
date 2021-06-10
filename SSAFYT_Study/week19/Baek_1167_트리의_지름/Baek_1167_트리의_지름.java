package week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1167_트리의_지름 {
	static int N, temp, ans;
	static boolean[] visited;
	static Node[] al;
	
	static void dfs(int idx, int len) {
		if(len > ans) {
			ans = len;
			temp = idx;
		}
		visited[idx] = true;
		
		for(Node node = al[idx]; node != null; node = node.next) {
			if(visited[node.to])
				continue;
			
			dfs(node.to, len + node.weight);
		}
		visited[idx] = false;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		al = new Node[N+1];
		visited = new boolean[N+1];
		
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
				
			al[from] = new Node(to, weight, al[from]);
			al[to] = new Node(from, weight, al[to]);
		}
		
		dfs(1, 0);
		dfs(temp, 0);
		System.out.println(ans);
	}
	
	static class Node{
		int to, weight;
		Node next;
		
		public Node(int to, int weight, Node next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
	}
}
