# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [1719] 택배
> https://www.acmicpc.net/problem/1719
## 알고리즘 분류
> 다익스트라, 플로이드&와샬

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1719_택배 {
	static int N, M, INF = 987654321;
	static int[] dis;
	static int[][] ans;
	static boolean[] visited;
	static ArrayList<ArrayList<Node>> al;
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dis = new int[N + 1];
		ans = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		al = new ArrayList<ArrayList<Node>>();
		pq = new PriorityQueue<>();

		for (int i = 0; i <= N; i++)
			al.add(new ArrayList<>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			al.get(a).add(new Node(b, cost));
			al.get(b).add(new Node(a, cost));
			ans[a][b] = b;
			ans[b][a] = a;
		}

		for (int i = 1; i <= N; i++)
			dijkstra(i);

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					sb.append("-");
				else
					sb.append(ans[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	static void init() {
		Arrays.fill(dis, INF);
		Arrays.fill(visited, false);
		pq.clear();
	}

	static void dijkstra(int start) {
		init();
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (visited[now.v])
				continue;

			visited[now.v] = true;

			for (Node next : al.get(now.v)) {
				if (dis[next.v] > next.cost + now.cost) {
					dis[next.v] = next.cost + now.cost;

					if (now.v != start && next.v != start) {
						int a = now.v;
						while (ans[start][a] != a) {
							a = ans[start][a];
						}
						ans[start][next.v] = a;
					}

					pq.offer(new Node(next.v, dis[next.v]));
				}
			}
		}
	}

	static class Node implements Comparable<Node> {
		int v, cost;

		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}
```

## 문제 풀이
1. 다익스트라 알고리즘을 사용해 문제를 해결했다.
1. 다익스트라 알고리즘을 돌리며 가장먼저 들려야할 정점을 초기화해준다.
   1. 시작점과 도착점이 시작 정점 `start`가 아닐때
   1. (start,now.v) == now.v 일 때까지 찾는다 -> 같을 경우 `start`에서 바로 가는경로
   1. 같을 때까지 돌린 후 갱신해준다.
