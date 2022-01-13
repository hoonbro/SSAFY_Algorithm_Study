# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [1516] 게임 개발
> https://www.acmicpc.net/problem/1516
## 알고리즘 분류
> 위상정렬

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_1516_게임_개발 {
	static int N;
	static int[] indegree, time, ans;
	static Queue<Integer> q = new LinkedList<Integer>();
	static ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		indegree = new int[N + 1];
		time = new int[N + 1];
		ans = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			al.add(new ArrayList<>());
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			time[i] = Integer.parseInt(st.nextToken());
			ans[i] = time[i];
			while (st.hasMoreElements()) {
				int token = Integer.parseInt(st.nextToken());
				if (token == -1)
					break;

				al.get(token).add(i);
				indegree[i]++;
			}
		}
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}
		while (!q.isEmpty()) {
			int now = q.poll();

			for (int next : al.get(now)) {
				indegree[next]--;
				
				ans[next] = Math.max(ans[next], ans[now] + time[next]);

				if (indegree[next] == 0)
					q.offer(next);
			}
		}

		for (int i = 1; i <= N; i++) {
			sb.append(ans[i]).append("\n");
		}

		System.out.println(sb.toString());
	}
}
```

## 문제 풀이
1. 위상정렬 알고리즘을 사용하며 가장 오래걸리는 시간을 체크해준다.
