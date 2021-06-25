package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1800_인터넷_설치 {
	static int N, P, K, ans = Integer.MAX_VALUE;
	static int[] dis;
	static boolean[] visited;
	static ArrayList<ArrayList<Node>> al;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	static boolean dijkstra(int mid) {
		Arrays.fill(dis, Integer.MAX_VALUE);
		Arrays.fill(visited, false);
		dis[1] = 0;
		pq.offer(new Node(1, 0));
		Node now;
		
		while(!pq.isEmpty()) {
			now = pq.poll();
			
			if(visited[now.to])
				continue;
			
			visited[now.to] = true;
			
			for(Node next : al.get(now.to)) {
				int cost = now.weight;
				
				if(next.weight > mid) {
					cost += 1;
				}
				
				if(dis[next.to] > cost) {
					dis[next.to] = cost;
					pq.offer(new Node(next.to, cost));
				}
			}
		}
		return dis[N] <= K;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dis = new int[N+1];
		visited = new boolean[N+1];
		al = new ArrayList<ArrayList<Node>>();
		
		for(int i = 0; i <= N; i++) {
			al.add(new ArrayList<>());
		}
		
		for(int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			al.get(from).add(new Node(to, weight));
			al.get(to).add(new Node(from, weight));
		}
		
		int left = 0, right = 1000000, mid;
		while(left <= right) {
			mid = (left + right) / 2;
			if(dijkstra(mid)) {
				ans = mid;
				right = mid-1;
			}
			else
				left = mid + 1;
		}
		
		if(ans == Integer.MAX_VALUE)
			ans = -1;
		System.out.println(ans);
	}
	
	static class Node implements Comparable<Node>{
		int to, weight;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
}
