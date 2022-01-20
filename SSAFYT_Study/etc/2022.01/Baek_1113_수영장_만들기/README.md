# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/15.svg" width="30"> [2230] 수영장 만들기
> https://www.acmicpc.net/problem/1113
## 알고리즘 분류
> BFS

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_1113_수영장_만들기 {
	static int N, M, ans = 0, maxHeight = 0;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	static int[][] map;
	static boolean[][] visited;
	static Queue<Pos> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
				maxHeight = Math.max(maxHeight, map[i][j]);
			}
		}
		
		for(int h = 2; h <= maxHeight; h++) {
			visited = new boolean[N][M];
			for(int i = 1; i < N-1; i++) {
				for(int j = 1; j < M-1; j++) {
					if(map[i][j] >= h || visited[i][j])
						continue;
					
					bfs(i, j, h);
				}
			}
		}
		
		System.out.println(ans);
		
	}
	
	static void bfs(int x, int y, int h) {
		 q.offer(new Pos(x, y));
		 visited[x][y] = true;
		 int cnt = 1;
		 
		 boolean flag = false;
		 while(!q.isEmpty()) {
			 Pos now = q.poll();
			 
			 for(int d = 0; d < 4; d++) {
				 int nx = now.x + X[d];
				 int ny = now.y + Y[d];
				 
				 if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
					 flag = true;
					 continue;
				 }
				 
				 if(visited[nx][ny] || map[nx][ny] >= h)
					 continue;
				 
				 visited[nx][ny] = true;
				 q.offer(new Pos(nx, ny));
				 cnt++;
			 }
		 }
		 
		 if(flag) return;
		 
		 ans += cnt;
	}
	
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
```

## 문제 풀이

1. BFS 알고리즘을 사용해 간단히 문제를 해결했다.
