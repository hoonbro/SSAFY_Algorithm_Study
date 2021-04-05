package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1197_최소_스패닝_트리 {
	static int V, E;
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parent = new int[V+1];
		
		int from, to, weight;
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			pq.add(new Edge(from, to, weight));
		}
		
		make();
		long ans = 0;
		Edge e;
		int cnt = 0;
		while(!pq.isEmpty()) {
			e = pq.poll();
			if(find(e.from) != find(e.to)) {
				union(e.from, e.to);
				ans += e.weight;
				cnt ++;
				
				if(cnt == V-1)
					break;
			}
		}
		System.out.println(ans);
	}
	
	static void make() {
		for(int i = 1; i <= V; i++) 
			parent[i] = i;
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
	
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
}
