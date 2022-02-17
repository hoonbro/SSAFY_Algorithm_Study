# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/14.svg" width="30"> [9370] 미확인 도착지
> https://www.acmicpc.net/problem/9370
## 알고리즘 분류
> 다익스트라

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_9370_미확인_도착지 {
	static int Testcase, N, M, T, S, G, H, INF = 20000000;
	static int[] destination, dis;
	static boolean[] visited;
	static ArrayList<Integer> ans;
	static ArrayList<ArrayList<Node>> al;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		Testcase = Integer.parseInt(br.readLine());

		while (Testcase-- > 0) {
			al = new ArrayList<ArrayList<Node>>();
			ans = new ArrayList<Integer>();

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());

			for (int i = 0; i <= N; i++) {
				al.add(new ArrayList<>());
			}
			destination = new int[T + 1];
			dis = new int[N + 1];
			visited = new boolean[N+1];

			st = new StringTokenizer(br.readLine());

			S = Integer.parseInt(st.nextToken());
			G = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken())*2;

				if ((a == G && b == H) || (a == H && b == G)) {
					d--;
				}

				al.get(a).add(new Node(b, d));
				al.get(b).add(new Node(a, d));
			}
			

			for (int i = 0; i < T; i++) {
				destination[i] = Integer.parseInt(br.readLine());
			}

			dijkstra();

			Arrays.sort(destination);
			for (int i : destination) {
				if(i != 0 && dis[i]%2 == 1)
					ans.add(i);
			}

			for (int des : ans) {
				sb.append(des).append(" ");
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
	}

	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		Arrays.fill(dis, INF);
		dis[S] = 0;
		pq.offer(new Node(S, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if(visited[now.v])
				continue;
			
			visited[now.v] = true;
			
			for (Node next : al.get(now.v)) {
				if (dis[next.v] >= now.c + next.c) {
					dis[next.v] = now.c + next.c;
					pq.offer(new Node(next.v, dis[next.v]));
				}
			}
		}
	}

	static class Node implements Comparable<Node> {
		int v, c;

		public Node(int v, int c) {
			this.v = v;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return this.c - o.c;
		}
	}
}
```

## 문제 풀이
1. 다익스트라 문제이지만 `경유지`가 있는 다익스트라 문제이다.
2. `경유지`를 들렀는지 체크해주기 위해서 `경유지`가 아닌 모든 길에 2를 곱해 짝수로 만들고 `경유지`에는 2를 곱하고 1을 빼 홀수로 만들어준다.
3. 다익스트라 알고리즘을 적용시킨 후 도착지 후보 중 짝수인 도착지 후보를 출력한다.
