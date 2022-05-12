package etc._2022_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_11085_군사_이동 {
	static int N, M, C, V;
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());

			pq.offer(new Edge(start, end, width));
		}

		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		int cnt = 0;
		int ans = 0;
		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (union(now.start, now.end)) {
				cnt++;

				if (cnt == N)
					break;

				if (find(C) == find(V)) {
					ans = now.width;
					break;
				}
			}
		}

		System.out.println(ans);
	}

	static int find(int a) {
		if (a == parent[a])
			return a;

		return parent[a] = find(parent[a]);
	}

	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b)
			return false;

		parent[b] = a;

		return true;
	}

	static class Edge implements Comparable<Edge> {
		int start, end, width;

		public Edge(int start, int end, int width) {
			this.start = start;
			this.end = end;
			this.width = width;
		}

		@Override
		public int compareTo(Edge o) {
			return o.width - this.width;
		}
	}
}
