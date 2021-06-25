package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_14938_서강그라운드 {
	static int N, M, R, ans = 0;
	static int[] map, dis;
	static ArrayList<ArrayList<Node>> al;
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 지역의 개수
		M = Integer.parseInt(st.nextToken()); // 수색 범위
		R = Integer.parseInt(st.nextToken()); // 길의 개수
		map = new int[N + 1];
		dis = new int[N + 1];
		al = new ArrayList<ArrayList<Node>>();

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i <= N; i++) {
			al.add(new ArrayList<>());
		}

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			al.get(from).add(new Node(to, weight));
			al.get(to).add(new Node(from, weight));
		}

		for (int i = 1; i <= N; i++) {
			dijkstra(i);
		}
		System.out.println(ans);
	}

	static void dijkstra(int start) {
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[start] = 0;
		pq.offer(new Node(start, 0));
		Node now;
		int item = 0;

		while (!pq.isEmpty()) {
			now = pq.poll();

			for (Node next : al.get(now.to)) {
				if (dis[next.to] > now.weight + next.weight && now.weight + next.weight <= M) {
					dis[next.to] = now.weight + next.weight;
					pq.offer(new Node(next.to, dis[next.to]));
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			if(dis[i] == Integer.MAX_VALUE)
				continue;
			
			item += map[i];
		}
		ans = Math.max(item, ans);
	}

	static class Node implements Comparable<Node> {
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
