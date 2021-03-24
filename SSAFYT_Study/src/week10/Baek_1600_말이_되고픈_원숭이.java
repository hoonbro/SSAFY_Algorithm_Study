package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_1600_말이_되고픈_원숭이 {
	static int N, M, K, min = Integer.MAX_VALUE;
	static int[][][] map;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static int[] KX = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] KY = { 1, 2, 2, 1, -1, -2, -2, -1 };

	static class Pos {
		int x, y, move, cnt;

		public Pos(int x, int y, int move, int cnt) {
			this.x = x;
			this.y = y;
			this.move = move;
			this.cnt = cnt;
		}
	}

	static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(0, 0, 0, K));
		map[0][0][0] = 1;
		Pos pos;
		int nx, ny;

		while (!q.isEmpty()) {
			pos = q.poll();
			if (pos.x == N - 1 && pos.y == M - 1) {
				System.out.println(pos.move);
				return;
			}

			// 대각선
			if (pos.cnt > 0) {
				for (int d = 0; d < 8; d++) {
					nx = pos.x + KX[d];
					ny = pos.y + KY[d];

					if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny][pos.cnt - 1] != 0)
						continue;

					map[nx][ny][pos.cnt - 1] = 1;
					q.offer(new Pos(nx, ny, pos.move + 1, pos.cnt - 1));
				}
			}

			for (int d = 0; d < 4; d++) {
				nx = pos.x + X[d];
				ny = pos.y + Y[d];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny][pos.cnt] != 0)
					continue;

				map[nx][ny][pos.cnt] = 1;
				q.offer(new Pos(nx, ny, pos.move + 1, pos.cnt));
			}
		}
		System.out.println(-1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M][K + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int a = Integer.parseInt(st.nextToken());
				for (int k = 0; k <= K; k++) {
					map[i][j][k] = a;
				}
			}
		}
		bfs();
	}
}
