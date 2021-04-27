# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [1405] 미친 로봇
> https://www.acmicpc.net/problem/1405
## 알고리즘 분류
> DFS, 백트래킹

## 코드
```java
package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1405_미친로봇 {
	static int N;
	static double ans;
	static int[] per = new int[4];
	static int[] X = {0,0,1,-1};
	static int[] Y = {1,-1,0,0};
	static boolean[][] visited = new boolean[30][30];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		for(int i = 0; i < 4; i++) {
			per[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(15,15,0,1.0);
		
		System.out.println(ans);
	}
	
	static void dfs(int x, int y, int cnt, double percentage) {
		if(cnt ==N) {
			ans += percentage;
			return;
		}
		
		visited[x][y] = true;
		for(int i = 0; i < 4; i++) {
			int nx = x + X[i];
			int ny = y + Y[i];
			
			if(visited[nx][ny])
				continue;
			
			dfs(nx, ny, cnt+1, percentage*per[i]* 0.01);
			visited[nx][ny] = false;
		}
	}
}
```

## 문제 풀이
* dfs와 백트래킹을 사용해 문제를 해결했다.
* 최대 15번이기 때문에 30까지의 visited배열을 사용해준다.
* dfs를 중앙위치에서 부터 시작해 재귀를 돌리면 간단하게 문제를 해결할 수 있다.
