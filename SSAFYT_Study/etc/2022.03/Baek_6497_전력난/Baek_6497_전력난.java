package etc._2022_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_6497_전력난 {
	static int N, M;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			PriorityQueue<Node> pq = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0) {
				break;
			}
			
			parent = new int[N];
			for(int i = 0; i < N; i++) {
				parent[i] = i;
			}
			
			int ans = 0;
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				pq.offer(new Node(from, to, cost));
				ans += cost;
			}
			
			int cnt = 0;
			while(cnt < N-1) {
				Node now = pq.poll();
				
				if(union(now.from, now.to)) {
					ans -= now.cost;
					cnt++;
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static int find(int a) {
		if(a == parent[a])
			return a;
		
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b)
			return false;
		
		parent[b] = a;
		return true;
	}
	
	static class Node implements Comparable<Node>{
		int from, to , cost;

		public Node(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}
