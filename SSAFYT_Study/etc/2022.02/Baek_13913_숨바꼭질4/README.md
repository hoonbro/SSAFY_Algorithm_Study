# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [13913] 숨바꼭질 4
> https://www.acmicpc.net/problem/13913
## 알고리즘 분류
> BFS

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_13913_숨바꼭질4 {
	static int N, K, ans = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (N >= K) {
			sb.append(N - K).append("\n");
			for (int i = N; i >= K; i--) {
				sb.append(i).append(" ");
			}
		} else
			bfs();
		bw.write(sb.toString());
		bw.flush();
	}

	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		int[] visited = new int[100001];
		
		Arrays.fill(visited, -1);

		q.offer(K);
		visited[K] = Integer.MAX_VALUE;

		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			
			while(size-- > 0) {
				int now = q.poll();
				
				if (now == N) {
					sb.append(time).append("\n");
					
					int next = N;
					while(next != Integer.MAX_VALUE) {
						sb.append(next).append(" ");
						next = visited[next];
					}
					return;
				}

				if (now - 1 >= 0 && visited[now - 1] == -1) {
					visited[now - 1] = now;
					q.offer(now-1);
				}

				if (now + 1 <= 100000 && visited[now + 1] == -1) {
					visited[now + 1] = now;
					q.offer(now+1);
				}
				if (now %2 == 0 && visited[now / 2] == -1) {
					visited[now / 2] = now;
					q.offer(now/2);
				}
			}
			time++;
		}
	}
}
```

## 문제 풀이
1. 동생의 위치부터 BFS를 돌며 역순으로 수빈이를 찾은 후 위치를 기억해 해결
