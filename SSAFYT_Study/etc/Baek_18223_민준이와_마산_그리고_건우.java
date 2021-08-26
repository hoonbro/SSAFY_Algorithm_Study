package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_18223_민준이와_마산_그리고_건우 {
	static int V,E,P;
	static int[] dis;
	static boolean[] visited;
	static int INF = 987654321;
	static ArrayList<ArrayList<Node>> al = new ArrayList<ArrayList<Node>>();
	static PriorityQueue<Node> pq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		dis = new int[V+1];
		
		for(int i = 0; i <= V; i++) {
			al.add(new ArrayList<>());
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			al.get(from).add(new Node(to, weight));
			al.get(to).add(new Node(from, weight));
		}
		
		int min = dijkstra(1, V);
		int friend = dijkstra(1, P) + dijkstra(P, V);
		System.out.println(min + " " + friend);
		if(min >= friend)
			System.out.println("SAVE HIM");
		else
			System.out.println("GOOD BYE");
	}
	
	static int dijkstra(int start, int end) {
		Arrays.fill(dis, INF);
		dis[start] = 0;
		
		pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
		visited = new boolean[V+1];
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(visited[now.to])
				continue;
			
			visited[now.to] = true;
			
			for(Node next : al.get(now.to)) {
				if(dis[next.to] > now.weight + next.weight) {
					dis[next.to] = now.weight + next.weight;
					pq.add(new Node(next.to, dis[next.to]));
				}
			}
		}
		return dis[end];
	}
	
	static class Node implements Comparable<Node>{
		int to, weight;

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
		
		
	}
}
