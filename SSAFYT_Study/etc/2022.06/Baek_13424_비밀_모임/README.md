# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [13424] 비밀 모임
> https://www.acmicpc.net/problem/13424
## 알고리즘 분류
> 다익스트라, 플로이드와샬

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_13424_비밀_모임 {
	static int T, N, M, K, min, idx;
	static int[] start;
	static ArrayList<ArrayList<Node>> al;
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			min = Integer.MAX_VALUE;
			idx = 0;
			al = new ArrayList<ArrayList<Node>>();

			for (int i = 0; i <= N; i++) {
				al.add(new ArrayList<>());
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				al.get(a).add(new Node(b, cost));
				al.get(b).add(new Node(a, cost));
			}

			K = Integer.parseInt(br.readLine());
			start = new int[K];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				start[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 1; i <= N; i++) {
				int len = dijkstra(i);

				if(min > len) {
					idx = i;
					min = len;
				}
			}
			sb.append(idx).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	static int dijkstra(int des) {
		int[] dis = new int[N + 1];
		boolean[] visited = new boolean[N + 1];
		Arrays.fill(dis, 987654321);

		pq.offer(new Node(des, 0));
		dis[des] = 0;

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (visited[now.v])
				continue;

			visited[now.v] = true;
			for (Node next : al.get(now.v)) {
				if (dis[next.v] > now.cost + next.cost) {
					dis[next.v] = now.cost + next.cost;
					pq.offer(new Node(next.v, dis[next.v]));
				}
			}
		}
		
		int result = 0;
		System.out.println(des + " ");
		for(int i = 0; i < K; i++) {
			System.out.println(start[i] + " " + dis[start[i]] + " ");
			result += dis[start[i]];
		}
		System.out.println();
		pq.clear();
		return result;
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
1. 다익스트라를 사용하면 문제를 해결할 수 있다.
1. 주어진 N이 100이기 때문에 플로이드 워셜 알고리즘을 사용해도 문제를 해결할 수 있다.
