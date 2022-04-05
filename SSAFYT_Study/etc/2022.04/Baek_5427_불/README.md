# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [5427]  불
> https://www.acmicpc.net/problem/5427
## 알고리즘 분류
> BFS

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_5427_불 {
	static int T, N, M, ans;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static char[][] map;
	static Queue<Pos> q;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			ans = 0;
			map = new char[N][M];
			q = new LinkedList<>();

			int x = 0, y = 0;
			for (int i = 0; i < N; i++) {
				String s = br.readLine();

				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);

					if (map[i][j] == '*') {
						q.offer(new Pos(i, j, 0));
					} else if (map[i][j] == '@') {
						x = i;
						y = j;
					}
				}
			}

			q.offer(new Pos(x, y, 0));
			bfs();
			sb.append(ans == 0 ? "IMPOSSIBLE" : ans).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void bfs() {
		while (!q.isEmpty()) {
			Pos p = q.poll();
			char now = map[p.x][p.y];
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					if(now == '@') {
						ans = p.time + 1;
						return;
					}
					continue;
				}

				if (map[nx][ny] != '.')
					continue;

				map[nx][ny] = now;
				q.offer(new Pos(nx, ny, p.time + 1));
			}
		}
	}

	static class Pos {
		int x, y, time;

		public Pos(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}
```

## 문제 풀이
1. BFS알고리즘을 적용해 문제를 해결했다.
1. 불이 먼저 움직여야하기 때문에 입력을 받을 시 불이라면 바로 큐에 넣어주고 사람이라면 좌표를 기억해 둔뒤 입력이 모두 들어온 후 큐에 넣어준다.
1. 사람 혹은 불이 움직인자리를 바꿔줬기 때문에 따로 방문체크 배열은 사용하지 않았다.
