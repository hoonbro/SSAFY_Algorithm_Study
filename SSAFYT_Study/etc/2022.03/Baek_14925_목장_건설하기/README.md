# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [14925] 목장 건설하기
> https://www.acmicpc.net/problem/14925
## 알고리즘 분류
> 다이나믹 프로그래밍

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_14925_목장_건설하기 {
	static int N, M;
	static int[][] map, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N+1][M+1];
		dp = new int[N+1][M+1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] != 0)
					continue;

				dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
				ans = Math.max(dp[i][j], ans);
			}
		}
		System.out.println(ans);
	}
}
```

## 문제 풀이
1. 
