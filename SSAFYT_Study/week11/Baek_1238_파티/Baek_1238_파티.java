package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1238_파티 {
	static int N, M, X, ans, INF = 987654321;
	static ArrayList<ArrayList<Node>> al;
	static ArrayList<ArrayList<Node>> al2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		al = new ArrayList<ArrayList<Node>>();
		al2 = new ArrayList<ArrayList<Node>>();
		
		for(int i = 0; i <= N; i++) {
			al.add(new ArrayList<Node>());
			al2.add(new ArrayList<Node>());
		}
		
		int a,b,t;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			al.get(a).add(new Node(b, t));
			al2.get(b).add(new Node(a, t));
		}
		
		int[] dis = dijkstra(al);
		int[] dis2 = dijkstra(al2);
		
		for(int i = 1; i <= N; i++) {
			ans = Math.max(dis[i] + dis2[i], ans);
		}
		System.out.println(ans);
	}
	
	static int[] dijkstra(ArrayList<ArrayList<Node>> list) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dis = new int[N+1]; 
		Arrays.fill(dis, INF);
		dis[X] = 0;
		pq.add(new Node(X, 0));
		Node now;
		while(!pq.isEmpty()) {
			now = pq.poll();
			
			for(Node next : list.get(now.to)) {
				if(dis[next.to] > now.weight + next.weight) {
					dis[next.to] = now.weight + next.weight;
					pq.add(new Node(next.to, dis[next.to]));
				}
			}
		}
		return dis;
	}
	
	static class Node implements Comparable<Node>{
		int to, weight;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
	}
}
