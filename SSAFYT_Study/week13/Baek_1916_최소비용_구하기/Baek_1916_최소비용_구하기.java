package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1916_최소비용_구하기 {
	static int N,M, INF = 987654321;
	static int[] dis;
	static ArrayList<ArrayList<Node>> al;
	
	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dis[start] = 0;
		
		Node now;
		while(!pq.isEmpty()) {
			now = pq.poll();
			
			for(Node next : al.get(now.to)) {
				if(dis[next.to] > now.weight + next.weight) {
					dis[next.to] = now.weight + next.weight;
					pq.offer(new Node(next.to, dis[next.to]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dis = new int[N+1];
		Arrays.fill(dis, INF);
		al = new ArrayList<ArrayList<Node>>();
		for(int i = 0; i <= N; i++) {
			al.add(new ArrayList<>());
		}
		
		
		int from, to, weight, start, end;
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			al.get(from).add(new Node(to, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		dijkstra(start);
		System.out.println(dis[end]);
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
