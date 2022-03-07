package etc._2022_03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_4991_로봇_청소기 {
	static int N, M, dustTotal, dustCnt;
	static Pos start;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static char[][] map;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0)
				break;

			map = new char[N][M];

			dustCnt = 0;
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);

					if (map[i][j] == 'o') {
						map[i][j] = '.';
						start = new Pos(i, j, 0, 0);
					} else if (map[i][j] == '*') {
						map[i][j] = (char) ('a' + dustCnt);
						dustCnt++;
					}
				}
			}

			dustTotal = (1 << dustCnt) - 1;

			sb.append(bfs()).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
	}

	static int bfs() {
		Queue<Pos> q = new LinkedList<>();
		visited = new boolean[N][M][(1 << dustCnt)];
		visited[start.x][start.y][0] = true;
		q.offer(start);

		while (!q.isEmpty()) {
			Pos p = q.poll();

			if (p.dust == dustTotal) {
				return p.len;
			}

			for (int i = 0; i < 4; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 'x' || visited[nx][ny][p.dust])
					continue;

				if (map[nx][ny] == '.') {
					visited[nx][ny][p.dust] = true;
					q.offer(new Pos(nx, ny, p.len + 1, p.dust));
				} else {
					int num = map[nx][ny] - 'a';

					if ((p.dust & (1 << num)) == 0) {
						visited[nx][ny][p.dust | (1 << num)] = true;
						q.offer(new Pos(nx, ny, p.len + 1, p.dust | (1 << num)));
					}else {
						visited[nx][ny][p.dust] = true;
						q.offer(new Pos(nx, ny, p.len+1, p.dust));
					}
				}
			}

		}
		return -1;
	}

	static class Pos {
		int x, y, len, dust;

		public Pos(int x, int y, int len, int dust) {
			this.x = x;
			this.y = y;
			this.dust = dust;
			this.len = len;
		}
	}
}
