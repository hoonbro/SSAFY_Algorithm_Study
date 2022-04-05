package etc._2022_04;

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
