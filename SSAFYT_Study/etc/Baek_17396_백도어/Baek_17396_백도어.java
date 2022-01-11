package etc._2022_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_17396_백도어 {
	static int N, M;
	static long INF = Long.MAX_VALUE;
	static int[] vision;
	static long[] dis;
	static boolean[] visited;
	static ArrayList<ArrayList<Node>> al = new ArrayList<ArrayList<Node>>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			al.add(new ArrayList<>());
		}

		dis = new long[N];
		vision = new int[N];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			vision[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			al.get(from).add(new Node(to, cost));
			al.get(to).add(new Node(from, cost));
		}
		
		djikstra();
		
		System.out.println(dis[N-1] == INF ? -1 : dis[N-1]);
	}

	static void djikstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(dis, INF);

		pq.offer(new Node(0, 0));
		dis[0] = 0;

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if(now.vertex == N-1)
				break;
			
			if (visited[now.vertex] || (vision[now.vertex] == 1))
				continue;

			visited[now.vertex] = true;
			
			for(Node next : al.get(now.vertex)) {
				if(dis[next.vertex] > now.cost + next.cost) {
					dis[next.vertex] = now.cost + next.cost;
					pq.offer(new Node(next.vertex, dis[next.vertex]));
				}
			}
		}
	}

	static class Node implements Comparable<Node> {
		int vertex;
		long cost; 
		public Node(int vertex, long cost) {
			this.vertex = vertex;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return (int) (this.cost - o.cost);
		}
	}
}
