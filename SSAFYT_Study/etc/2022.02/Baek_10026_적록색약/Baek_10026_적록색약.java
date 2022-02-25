package etc._2022_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_10026_적록색약 {
	static int N;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static char[][] map;
	static boolean[][][] visited;
	static Queue<Pos> q = new LinkedList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N][2];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		int rg = 0, r = 0, g = 0, b = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j][0]) {
					RGB(i, j, map[i][j]);
					if (map[i][j] == 'R') 
						r++;
					else if (map[i][j] == 'G')
						g++;
					else if (map[i][j] == 'B')
						b++;
				}

				if(!visited[i][j][1] && map[i][j] != 'B') {
					RG(i, j);
					rg++;
				}
			}
		}
		System.out.println((r+g+b) + " " + (rg + b));

	}

	static void RG(int x, int y) {
		Queue<Pos> rgQ = new LinkedList<>();
		rgQ.offer(new Pos(x, y));
		visited[x][y][1] = true;

		while (!rgQ.isEmpty()) {
			Pos p = rgQ.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny][1] || map[nx][ny] == 'B')
					continue;

				visited[nx][ny][1] = true;
				rgQ.offer(new Pos(nx, ny));
			}
		}
	}

	static void RGB(int x, int y, char color) {
		q.offer(new Pos(x, y));
		visited[x][y][0] = true;

		while (!q.isEmpty()) {
			Pos p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny][0] || map[nx][ny] != color)
					continue;

				visited[nx][ny][0] = true;
				q.offer(new Pos(nx, ny));
			}
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
