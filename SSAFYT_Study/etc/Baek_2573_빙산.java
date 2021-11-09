package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2573_빙산 {
	static int N, M, ice = 0;
	static int[][] map;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0)
					ice++;
			}
		}

		int ans = 0;
		loop: while (true) {
			ans++;
			boolean[][] visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 0 && !visited[i][j]) {
						for (int d = 0; d < 4; d++) {
							int nx = i + X[d];
							int ny = j + Y[d];

							if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 0)
								continue;
							
							visited[nx][ny] = true;
							map[nx][ny]--;
							if(map[nx][ny] == 0) {
								ice--;
							}
						}
					}
				}
			}
//			System.out.println(ans);
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] <= 0)
						continue;

					int cnt = bfs(i, j);
					if (cnt == ice)
						break;

					else if (cnt < ice) {
						break loop;
					}
				}
			}
			if (ice == 0) {
				ans = 0;
				break;
			}
		}
		System.out.println(ans);
	}

	static int bfs(int x, int y) {

		boolean[][] visited = new boolean[N][M];
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(x, y));
		visited[x][y] = true;
		int cnt = 1;

		while (!q.isEmpty()) {
			Pos p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || map[nx][ny] <= 0)
					continue;

				visited[nx][ny] = true;
				cnt++;
				q.offer(new Pos(nx, ny));
			}
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
