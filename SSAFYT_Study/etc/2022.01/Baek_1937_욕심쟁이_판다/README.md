# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [1937] 욕심쟁이 판다
> https://www.acmicpc.net/problem/1937
## 알고리즘 분류
> DP, DFS

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1937_욕심쟁이_판다 {
	static int N, ans = 0, max = 0;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	static int[][] map, dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(dp[i], 0);
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(dp[i][j] != 0)
					continue;
				
				dp[i][j] = 1;
				ans = Math.max(ans, dfs(i, j));
			}
		}
		
		System.out.println(ans);
	}
	
	static int dfs(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int nx = x + X[i];
			int ny = y + Y[i];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] <= map[x][y])
				continue;
			
			if(dp[nx][ny] == 0) {
				int cnt = dfs(nx, ny);
				dp[x][y] = Math.max(dp[x][y], cnt + 1);
				
			}else {
				dp[x][y] = Math.max(dp[x][y], dp[nx][ny] + 1);
			}
		}
		
		if(dp[x][y] == 0)
			return 1;
		return dp[x][y];
	}
}
```

## 문제 풀이
1. 입력을 받는 map 배열과 위치별 최대 이동 횟수를 적는 dp배열을 선언한다.
2. 모든 좌표를 돌며 dp 배열이 초기화가 되어있지 않다면 `깊이 우선 탐색`을 시작한다.
   1. 다음 선택한 좌표가 초기화가 안되어 있다면(dp[nx][ny] == 0), 재귀를 계속 돌며 cnt변수에 return값을 넣어두고 dp[x][y]값과 비교해 dp[x][y]값을 갱신해준다.
   2. 다음 선택한 좌표가 초기화가 되어있다면, 한번 더 재귀를 들어갈 필요없이 바로 갱신해준다.
