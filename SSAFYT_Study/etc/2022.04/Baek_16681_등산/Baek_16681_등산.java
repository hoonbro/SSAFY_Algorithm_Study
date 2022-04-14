package etc._2022_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_16681_등산 {
	static int N, M, D, E;
	static int[] height;
	static long ans = 0,INF = Long.MAX_VALUE;
	static ArrayList<ArrayList<Edge>> al;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		height = new int[N];
		al = new ArrayList<ArrayList<Edge>>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
			al.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());

			al.get(a).add(new Edge(b, cost));
			al.get(b).add(new Edge(a, cost));
		}

		long[] upDis = dijkstra(true);
		long[] downDis = dijkstra(false);
		
		
		for(int i = 0; i < N; i++) {
			if(upDis[i] ==  Long.MAX_VALUE || downDis[i] ==  Long.MAX_VALUE)
				continue;
			
			
			ans = Math.max(ans, height[i]*E - (upDis[i] + downDis[i])*D);
		}
		
		System.out.println(ans == 0 ? "Impossible" : ans);
	}

	static long[] dijkstra(boolean isClimb) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		long[] dis = new long[N];
		Arrays.fill(dis,  Long.MAX_VALUE);
		
		if(isClimb) {
			pq.offer(new Edge(0, 0));
			dis[0] = 0;
		}else {
			pq.offer(new Edge(N-1, 0));
			dis[N-1] = 0;
		}

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if(dis[now.v] < now.cost)
				continue;

			for (Edge next : al.get(now.v)) {
				if (height[now.v] >= height[next.v])
					continue;

				if (dis[next.v] > now.cost + next.cost) {
					dis[next.v] = now.cost + next.cost;
					pq.offer(new Edge(next.v, dis[next.v]));
				}
			}
		}
		
		return dis;
	}

	static class Edge implements Comparable<Edge> {
		int v;
		long cost;

		public Edge(int v, long cost) {
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return (int)(this.cost - o.cost);
		}
	}
}
