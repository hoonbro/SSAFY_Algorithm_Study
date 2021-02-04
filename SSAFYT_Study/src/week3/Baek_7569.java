package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek_7569 {
	static int N, M, H, green = 0;
	static int[][][] map;
	static LinkedList<Pos> q;

	static class Pos {
		int x;
		int y;
		int z;
		int day;

		public Pos(int x, int y, int z, int day) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.day = day;
		}
	}

	static void bfs() {
		int[] X = { 1, -1, 0, 0, 0, 0 };
		int[] Y = { 0, 0, 1, -1, 0, 0 };
		int[] Z = { 0, 0, 0, -0, 1, -1 };
		while (!q.isEmpty()) {
			if (green == 0) {
				System.out.println(q.pollLast().day);
				break;
			}
			Pos pos = q.poll();
			for (int i = 0; i < 6; i++) {
				int dx = pos.x + X[i];
				int dy = pos.y + Y[i];
				int dz = pos.z + Z[i];
				if (dx < 0 || dy < 0 || dz < 0 || dx >= N || dy >= M || dz >= H || map[dz][dx][dy] == -1)
					continue;

				if (map[dz][dx][dy] == 0) {//덜익은 토마토일 경우
					map[dz][dx][dy] = pos.day + 1; // 몇일에 익은 토마토인지 입력
					green--; // 덜인은 토마토 --
					q.add(new Pos(dx, dy, dz, pos.day + 1));
				}
			}
		}
		if (green > 0) //덜익은 토마토가 있다면 -1 출력
			System.out.println(-1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		q = new LinkedList<>();
		M = Integer.parseInt(st.nextToken()); // 가로
		N = Integer.parseInt(st.nextToken()); // 세로
		H = Integer.parseInt(st.nextToken()); // 높이
		map = new int[H][N][M];
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[h][i][j] = Integer.parseInt(st.nextToken());
					if (map[h][i][j] == 1) 
						q.add(new Pos(i, j, h, 0));
					else if (map[h][i][j] == 0)
						green++;// 덜익은 토마토 갯수
				}
			}
		}
		bfs();
	}
}
