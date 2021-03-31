# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [17069] 파이프 옮기기2
## 문제 링크
> https://www.acmicpc.net/problem/17069
## 알고리즘 분류
> DP

## 코드
```java
package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17069_파이프_옮기기2 {
	static int N;
	static int[][] map;
	static long[][][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		dp = new long[N+1][N+1][3]; //가로, 세로, 대각선
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[1][2][0] = 1;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(map[i][j] != 0)
					continue;
				// 가로
				if(j <= N && map[i][j-1] == 0) {
					dp[i][j][0] += dp[i][j-1][0] + dp[i][j-1][2];
				}
				// 세로				
				if (i <= N && map[i-1][j] == 0) {
					dp[i][j][1] += dp[i-1][j][1] + dp[i-1][j][2];
				}
				//대각선
				if(i <= N && j <= N  && map[i-1][j-1] == 0 && map[i-1][j] == 0 && map[i][j-1] == 0) {
					dp[i][j][2] += dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
			}
		}
		
		long ans = 0;
		for(int i = 0; i < 3; i++)
			ans += dp[N][N][i];
		System.out.println(ans);
	}
}
```

## 문제 풀이
* 이전방향을 알아야하기때문에 3차원 dp배열을 사용한다.
* 가로 방향의 경우 가로, 대각선에서 올 수 있다.
* 세로 방향의 경우 세로, 대각선에서 올 수 있다.
* 대각선 방향의 경우 가로, 세로, 대각선에서 모두 올 수 있다.
* 각 방향에서의 값을 더해주며 초기화해주고 마지막 dp[N][N]자리에서 모든 경우를 다 보는 것으로 해결했다.
