package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_20057_마법사_상어와_토네이도 {
	static int N, total = 0, outSand = 0;
	static int[][] map;
	// 좌하우상
	static int X[][] = { { -1, 1, -2, 2, 0, -1, 1, -1, 1 }, { -1, -1, 0, 0, 2, 0, 0, 1, 1 },
			{ -1, 1, -2, 2, 0, -1, 1, -1, 1 }, { 1, 1, 0, 0, -2, 0, 0, -1, -1 } };
	static int Y[][] = { { 1, 1, 0, 0, -2, 0, 0, -1, -1 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1 },
			{ -1, -1, 0, 0, 2, 0, 0, 1, 1 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1 } };
	static int[] blow = { 1, 1, 2, 2, 5, 7, 7, 10, 10 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0)
					total += map[i][j];
			}
		}

		move();
		System.out.println(outSand);
	}

	static void sand(int x, int y, int dir) {
		int nx = 0, ny = 0;
		int sand = map[x][y];
		for (int i = 0; i < X[0].length; i++) {
			nx = x + X[dir][i];
			ny = y + Y[dir][i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				outSand += sand * blow[i] / 100;
				map[x][y] -= sand * blow[i] / 100;
				continue;
			}

			map[nx][ny] += sand * blow[i] / 100;
			map[x][y] -= sand * blow[i] / 100;
		}
	}

	static void move() {
		int x = N / 2;
		int y = N / 2;
		int d = -1;
		int cnt = 1;
		int dir = 0;

		while (true) {
			// 가로 이동
			for (int k = 0; k < cnt; k++) {
				y += d;
				sand(x, y, dir);

				// 알파로 이동
				int ny = y + d;
				if (ny >= 0 && ny < N) {
					map[x][ny] += map[x][y];
					map[x][y] = 0;
				} else {
					outSand += map[x][y];
					map[x][y] = 0;
				}
			}

			dir = (dir + 1) % 4;

			if ((x == 0 && y == 0) || total == outSand) {
				break;
			}

			d *= -1;
			// 세로 이동
			for (int k = 0; k < cnt; k++) {
				x += d;
				sand(x, y, dir);

				// 알파로 이동
				int nx = x + d;
				if (nx >= 0 && nx < N) {
					map[nx][y] += map[x][y];
					map[x][y] = 0;
				} else {
					outSand += map[x][y];
					map[x][y] = 0;
				}
			}

			dir = (dir + 1) % 4;

			if (cnt < N - 1)
				cnt++;
		}
	}
}
