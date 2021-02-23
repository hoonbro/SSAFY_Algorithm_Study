package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_17070_파이프_옮기기1 {
	static int[][] map;
	static int[] X = { 0, 1, 1 };
	static int[] Y = { 1, 1, 0 };
	static int N, ans = 0;
	static Queue<Pos> q;

	static class Pos {
		int x, y, dir, cnt;

		Pos(int x, int y, int dir, int cnt) {
			this.x = x;
			this.y = y;
			this.dir = dir; // 0:가로, 1:대각, 2:세로
			this.cnt = cnt;
		}
	}

	static void bfs() {
		while (!q.isEmpty()) {
			Pos pos = q.poll();

			if (pos.x == N - 1 && pos.y == N - 1) {
				ans++;
				continue;
			}

			// 가로, 대각선, 세로
			for (int i = 0; i < 3; i++) {
				// 현재 가로면 세로로 못감, 세로면 가로로 못감
				if (pos.dir == 0 && i == 2 || pos.dir == 2 && i == 0)
					continue;

				int nx = pos.x + X[i];
				int ny = pos.y + Y[i];

				if (nx >= N || ny >= N || map[nx][ny] == 1)
					continue;

				if (i == 1) {
					if (map[pos.x + 1][pos.y] == 1 || map[pos.x][pos.y + 1] == 1)
						continue;
				}

				q.add(new Pos(nx, ny, i, pos.cnt + 1));

			}
		}
	}

	static void dfs(int x, int y, int dir) {
		if (x == N - 1 && y == N - 1) {
			ans++;
			return;
		}

		for (int i = 0; i < 3; i++) {
			// 현재 가로면 세로로 못감, 세로면 가로로 못감
			if (dir == 0 && i == 2 || dir == 2 && i == 0)
				continue;

			int nx = x + X[i];
			int ny = y + Y[i];

			if (nx >= N || ny >= N || map[nx][ny] == 1)
				continue;

			if (i == 1) {
				if (map[nx - 1][ny] == 1 || map[nx][ny - 1] == 1)
					continue;
			}

			dfs(nx, ny, i);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		q.offer(new Pos(0, 1, 0, 0));
//		bfs();
		dfs(0, 1, 0);
		System.out.println(ans);
	}
}
