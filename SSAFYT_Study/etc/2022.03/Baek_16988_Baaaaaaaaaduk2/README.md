# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [16988] Baaaaaaaaaduk2
> https://www.acmicpc.net/problem/16988
## 알고리즘 분류
> BFS, 조합

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_16988_Baaaaaaaaaduk2 {
	static int N, M, ans = 0;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visited;
	static Queue<Pos> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		q = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0)
					continue;

				int a = j+1 == M ? i+1 : i;
				int b = j+1 == M ? 0 : j+1;
				
				for(; a < N; a++) {
					for(; b < M; b++) {
						if(map[a][b] != 0)
							continue;
						
						map[i][j] = 1;
						map[a][b] = 1;
						findMax();
						map[i][j] = 0;
						map[a][b] = 0;
					}
					b = 0;
				}
			}
		}
		
		System.out.println(ans);
	}

	static void findMax() {
		init();
		
		int total = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 2 || visited[i][j])
					continue;
				
				total += bfs(i, j);
			}
		}
		
		ans = Math.max(ans, total);
	}
	
	static int bfs(int x, int y) {
		q.offer(new Pos(x, y));
		visited[x][y] = true;
		
		int cnt = 1;
		boolean flag = true;
		while(!q.isEmpty()) {
			Pos p = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || map[nx][ny] == 1)
					continue;
				
				if(map[nx][ny] == 0) {
					flag = false;
					continue;
				}
				
				cnt++;
				visited[nx][ny] = true;
				q.offer(new Pos(nx, ny));
			}
		}
		return flag ? cnt : 0;
	}
	
	static void init() {
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
	}
	
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
```

## 문제 풀이
1. 놓을 수 있는 2가지 돌의 조합을 만들고 해당 돌을 놓았을 때 최대로 가능한 집의 수를 세어준다.
