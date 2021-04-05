# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [1520] 내리막길
## 문제 링크
> https://www.acmicpc.net/problem/1520
## 알고리즘 분류
> DP + DFS

## 코드
```java
package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1520_내리막길 {
	static int N, M;
	static int[][] map;
	static int[][] dp;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		


		System.out.println(dfs(0,0));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static int dfs(int x, int y) {
		if(x == N -1&& y == M-1) {
			if(dp[x][y] == -1) {
				System.out.println("gd");
				dp[x][y] = 1;
			}
			else {
				System.out.println("dp");
				dp[x][y] += 1;
			}
			return dp[x][y];
		}
		
		if(dp[x][y] != -1)
			return dp[x][y];
		
		int nx, ny;
		dp[x][y] = 0;
		for(int i = 0; i < 4; i++) {
			nx = x + X[i];
			ny = y + Y[i];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;
			
			if(map[nx][ny] < map[x][y])
				dp[x][y] += dfs(nx, ny);
		}
		return dp[x][y];
	}
}

```

## 문제 풀이
* 2차원 DP배열과 DFS를 사용해 문제를 해결했다.
* 2차원 DP배열을 -1로 초기화해 방문체크를 대신했다.
* DP배열의 (0,0) 위치에서 출발해 내리막길로 가며 도착지에 도착한 경우에 경로에 모두 1씩 더해준다.
* 확인하려는 경로가 -1이 아니라면 해당 길을 사용하면 도착지에 도착한다는 뜻이므로 볼필요 없고 여기까지 온 경로들에 1씩을 더해준다.
* 모든 경로를 확인한다면 출발지인 (0,0)의 값이 도착지까지의 경로의 수로 갱신된다.