package week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1504_특정한_최단_경로 {
	static int N, E, INF = 987654321;
	static int[] dis;
	static boolean[] visited;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static ArrayList<ArrayList<Edge>> al;
	
	static int dijkstra(int start, int end) {
		Arrays.fill(dis, INF);
		Arrays.fill(visited, false);
		dis[start] = 0;
		pq.offer(new Edge(start, 0));
		Edge now;
		
		while(!pq.isEmpty()) {
			now = pq.poll();
			
			if(visited[now.to])
				continue;
			
			visited[now.to] = true;
			
			for(Edge next : al.get(now.to)) {
				if(dis[next.to] > now.weight + next.weight) {
					dis[next.to] = now.weight + next.weight;
					pq.offer(new Edge(next.to, dis[next.to]));
				}
			}
		}
		return dis[end];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		al = new ArrayList<ArrayList<Edge>>();
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		dis = new int[N+1];
		visited = new boolean[N+1];
		
		for(int i = 0; i <= N; i++) {
			al.add(new ArrayList<>());
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			al.get(from).add(new Edge(to, weight));
			al.get(to).add(new Edge(from, weight));
		}
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		long result = dijkstra(1, a);
		result += dijkstra(a, b);
		result += dijkstra(b, N);
		
		long result2 = dijkstra(1, b);
		result2 += dijkstra(b, a);
		result2 += dijkstra(a, N);
		
		long ans = (result >= INF && result2 >= INF) ? -1 : Math.min(result, result2);

		System.out.println(ans);
	}
	
	static class Edge implements Comparable<Edge>{
		int to, weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
}
