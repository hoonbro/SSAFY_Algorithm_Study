package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_1939_중량제한 {
	static int N, M, start, end, ans = 0;
	static ArrayList<ArrayList<Node>> al = new ArrayList<ArrayList<Node>>();
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		
		for(int i = 0; i <= N; i++) {
			al.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			al.get(from).add(new Node(to, weight));
			al.get(to).add(new Node(from, weight));
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		bfs();
		
		System.out.println(ans);
	}
	
	static void bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		visited[start] = true;
		for(Node n : al.get(start)) {
			pq.offer(n);
		}
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(visited[now.to])
				continue;
			
			visited[now.to] = true;
			
			if(now.to == end) {
				ans = Math.max(ans, now.weight);
				break;
			}
			
			for(Node next : al.get(now.to)) {
				if(visited[next.to])
					continue;
				pq.offer(new Node(next.to, Math.min(now.weight, next.weight)));
			}
		}
	}
	
	static class Node implements Comparable<Node>{
		int to, weight;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return o.weight - this.weight;
		}
	}
}
