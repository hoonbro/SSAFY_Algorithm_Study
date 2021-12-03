package etc._2021_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_10282_해킹 {
	static int T, n, d, c, INF = Integer.MAX_VALUE, ans, cnt;
	static ArrayList<ArrayList<Node>> al;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			al = new ArrayList<ArrayList<Node>>();
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			cnt = 0;
			ans = 0;

			for (int i = 0; i <= n; i++) {
				al.add(new ArrayList<Node>());
			}

			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				al.get(from).add(new Node(to, cost));
			}
			dijkstra();
			
		}
	}

	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(c, 0));

		int[] dis = new int[n + 1];
		
		Arrays.fill(dis, INF);
		dis[c] = 0;

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if(dis[now.index] < now.cost)
				continue;
			
			for (Node next : al.get(now.index)) {
				if (dis[next.index] > now.cost + next.cost) {
					dis[next.index] = now.cost + next.cost;
					pq.offer(new Node(next.index, dis[next.index]));
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			if(dis[i] != INF) {
				cnt++;
				ans = Math.max(ans, dis[i]);
			}
		}
		System.out.println(cnt + " " + ans);
	}

	static class Node implements Comparable<Node> {
		int index, cost;

		public Node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}
