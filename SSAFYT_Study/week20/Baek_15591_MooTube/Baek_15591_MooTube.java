package week20;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baek_15591_MooTube {
	static int N,Q,ans;
	static Node[] list;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		list = new Node[N+1];
		
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			list[from] = new Node(to, weight, list[from]);
			list[to] = new Node(from, weight, list[to]);
		}
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			ans = -1;
			
			dfs(K, V, Integer.MAX_VALUE);
			sb.append(ans).append("\n");
//			sb.append(bfs(K, V)).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	static void dfs(int k, int v, int weight) {
		if(visited[v] || k > weight)
			return;
		
		visited[v] = true;
		ans++;
		
		for(Node node = list[v]; node != null; node = node.next) {
			dfs(k, node.to, Math.min(weight, node.weight));
		}
		visited[v] = false;
	}
	
	static class Node{
		int to, weight;
		Node next;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		public Node(int to, int weight, Node next) {
			super();
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
	}
}
