package test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//뿌요뿌요
public class Baek_11559 {
	static int N = 12, M = 6;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static char[][] map;
	static boolean[][] check;
	static Queue<Pos> q;
	static Queue<Pos> explode;

	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static boolean bfs() {
		while (!q.isEmpty()) {
			Pos pos = q.poll();
			check[pos.x][pos.y] = true;
			int nx, ny;
			for (int i = 0; i < 4; i++) {
				nx = pos.x + X[i];
				ny = pos.y + Y[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (map[nx][ny] != map[pos.x][pos.y] || check[nx][ny])
					continue;
				check[nx][ny] = true;
				q.offer(new Pos(nx, ny));
				explode.offer(new Pos(nx, ny));
			}
		}
		if (explode.size() >= 4) {
			Pos pos;
			while (!explode.isEmpty()) {
				pos = explode.poll();
				map[pos.x][pos.y] = '.';
			}
			return true;
		}
		explode.clear();
		return false;
	}

	static void remap() {
		for (int j = 0; j < M; j++) {
			for (int i = N - 2; i >= 0; i--) {
				if (map[i][j] != '.') {

					int t = 0;
					while (true) {
						if (i + t + 1 >= N || map[i + t + 1][j] != '.')
							break;
						char temp = map[i + t][j];
						map[i + t][j] = map[i + t + 1][j];
						map[i + t + 1][j] = temp;
						t++;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		q = new LinkedList<>();
		explode = new LinkedList<>();

		int cnt = 0;
		while (true) {
			boolean end = true;
			boolean isPuyo = false;
			check = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != '.' && !check[i][j]) {
						isPuyo = true;
						q.offer(new Pos(i, j));
						explode.offer(new Pos(i, j));
						if (bfs()) {
							remap();
							end = false;
							break;
						}
					}
				}
			}

			if(end || !isPuyo)
				break;
			cnt++;
		}
		System.out.println(cnt);
	}
}
