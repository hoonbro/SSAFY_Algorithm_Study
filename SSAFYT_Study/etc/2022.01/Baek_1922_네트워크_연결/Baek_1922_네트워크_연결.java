package etc._2022_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1922_네트워크_연결 {
	static int N, M;
	static int[] parent;
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parent = new int[N + 1];
		
		for(int i = 1; i <= N; i++)
			parent[i] = i;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			pq.offer(new Node(a, b, cost));
		}
		
		int cnt = 0;
		int ans = 0;
		while(!pq.isEmpty()) {
			Node e = pq.poll();
			
			if(find(e.from) != find(e.to)) {
				union(e.from, e.to);
				ans += e.cost;
				cnt++;
				
				if(cnt > N-1)
					break;
			}
		}
		
		System.out.println(ans);
	}

	static int find(int a) {
		if (a == parent[a])
			return a;

		return parent[a] = find(parent[a]);
	}

	static void union(int a, int b) {
		a = parent[a];
		b = parent[b];

		if (a == b)
			return;

		parent[b] = a;
	}

	static class Node implements Comparable<Node> {
		int from, to, cost;

		public Node(int from, int to, int cost) {
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
