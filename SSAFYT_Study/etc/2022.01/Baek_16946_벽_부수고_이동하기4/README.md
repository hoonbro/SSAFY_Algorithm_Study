# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/14.svg" width="30"> [16946] 벽 부수고 이동하기4
> https://www.acmicpc.net/problem/16946
## 알고리즘 분류
> BFS

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static int[][] map;
	static int[][][] visited;
	static Queue<Pos> q = new LinkedList<>();
	static Queue<Pos> q2 = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M][2];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		int num = 1;
		loop:
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					sb.append(0);
					continue;
				}

				boolean[] check = new boolean[num+1];
				for (int d = 0; d < 4; d++) {
					int nx = i + X[d];
					int ny = j + Y[d];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] != 0 || check[visited[nx][ny][1]])
						continue;
					
					if(visited[nx][ny][0] != 0) {
						map[i][j] += visited[nx][ny][0];
					}else {
						map[i][j] += bfs(nx, ny, num++);
						check[visited[nx][ny][1]] = true;
					}
				}
				
				sb.append(map[i][j]);
//				break loop;
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
	}

	static int bfs(int x, int y, int num) {
		q.offer(new Pos(x, y));
		q2.offer(new Pos(x, y));
		visited[x][y][0] = 1;
		int cnt = 1;

		while (!q.isEmpty()) {
			Pos p = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = p.x + X[d];
				int ny = p.y + Y[d];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] != 0 || visited[nx][ny][0] != 0)
					continue;
				
				cnt++;
				visited[nx][ny][0] =1;
				q.offer(new Pos(nx, ny));
				q2.offer(new Pos(nx, ny));
			}
		}
		
		while(!q2.isEmpty()) {
			Pos p = q2.poll();
			visited[p.x][p.y][0] = cnt;
			visited[p.x][p.y][1] = num;
		}
		
		return cnt;
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
1. 갈 수 있는 길(0)을 bfs를 돌면서 얼만큼 갈 수 있는지 확인한다.
2. 벽(1)의 상하좌우를 확인하며 길(0)이라면 그 수를 더해준다.
