package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//bfs
public class Baek_2636_치즈 {
	static int N, M, cheeze = 0, leftCheeze = 0;
	static int[][] map;
	static Queue<Pos> q;
	static ArrayList<Pos> al;
	static boolean[][] check;
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
				if (map[i][j] == 1)
					cheeze++;
			}
		}

		int cnt = 0;
		q = new LinkedList<>();
		while (cheeze != 0) {
			check = new boolean[N][M];
			leftCheeze = cheeze;
			cnt++;

			loop:
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 0) {
						q.offer(new Pos(i, j));
						bfs();
						break loop;
					}
				}
			}
		}

		System.out.println(cnt);
		System.out.println(leftCheeze);
	}

	static void bfs() {
		al = new ArrayList<>();
		while (!q.isEmpty()) {
			Pos pos = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = pos.x + X[i];
				int ny = pos.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || check[nx][ny])
					continue;

				check[nx][ny] = true;
				if (map[nx][ny] == 0)
					q.offer(new Pos(nx, ny));
				else if (map[nx][ny] == 1)
					al.add(new Pos(nx, ny));
			}
		}

		for (int i = 0; i < al.size(); i++) {
			cheeze--;
			map[al.get(i).x][al.get(i).y] = 0;
		}
	}

	static class Pos {
		int x, y;

		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}
