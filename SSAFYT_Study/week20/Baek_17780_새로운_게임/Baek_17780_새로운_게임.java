package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek_17780_새로운_게임 {
	static int N, K;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static int[][] map;
	static LinkedList<Integer>[][] order;
	static Horse[] horses;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		horses = new Horse[K];
		order = new LinkedList[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				order[i][j] = new LinkedList<>();
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());

			if (dir == 2)
				dir = 3;
			else if (dir == 3)
				dir = 0;
			else if (dir == 4)
				dir = 2;
			horses[i] = new Horse(x, y, dir);
			order[x][y].add(i);
		}
		game();
	}

	static void game() {
		int x, y, nx, ny, time = 0;
		Horse h;
		while (time < 1000) {
			time++;

			for (int k = 0; k < K; k++) {
				h = horses[k];

				x = h.x;
				y = h.y;
				
				if (order[x][y].get(0) != k)
					continue;
				
				nx = x + X[h.dir];
				ny = y + Y[h.dir];

				// 파란색
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2) {
					h.dir = (h.dir + 2) % 4;
					nx = x + X[h.dir];
					ny = y + Y[h.dir];
				}

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2)
					continue;

				// 빨간색
				else if (map[nx][ny] == 1) {
					for (int i = order[x][y].size() - 1; i >= 0; i--) {
						int num = order[x][y].get(i);
						order[nx][ny].add(num);
						horses[num].x = nx;
						horses[num].y = ny;
					}
				} 
				
				//흰색
				else {
					for (int i = 0; i < order[x][y].size(); i++) {
						int num = order[x][y].get(i);
						order[nx][ny].add(num);
						horses[num].x = nx;
						horses[num].y = ny;
					}
				}
				order[x][y].clear();

				if (order[nx][ny].size() > 3) {
					System.out.println(time);
					return;
				}
			}
		}
		System.out.println(-1);
	}

	static class Horse {
		int x, y, dir;

		public Horse(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}
