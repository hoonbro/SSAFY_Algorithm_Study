package etc._2022_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_17086_아기상어2 {
	static int N, M, ans = 0;
	static int[] X = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] Y = { 0, 1, -1, 1, -1, 0, 1, -1 };
	static int[][] map;
	static boolean[][] visited;
	static Queue<Pos> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) {
					q.offer(new Pos(i, j, 0));
					visited[i][j] = true;
				}
			}
		}

		bfs();
		
		System.out.println(ans);
	}

	static void bfs() {
		while (!q.isEmpty()) {
			Pos p = q.poll();

			ans = Math.max(p.len, ans);
			
			for (int i = 0; i < 8; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 1 || visited[nx][ny])
					continue;

				visited[nx][ny] = true;
				q.offer(new Pos(nx, ny, p.len+1));
			}
		}
	}

	static class Pos {
		int x, y, len;

		public Pos(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}
}
