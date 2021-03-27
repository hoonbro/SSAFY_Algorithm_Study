package week10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_4485_녹색옷_입은애가_젤다지 {
	static int T, N;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static int[][][] map;

	static class Pos implements Comparable<Pos> {
		int x, y, cost;

		public Pos(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Pos o) {
			return this.cost - o.cost;
		}
	}

	static void dijkstra() {
		PriorityQueue<Pos> q = new PriorityQueue<>();
		q.offer(new Pos(0, 0, map[0][0][0]));
		map[0][0][1] = map[0][0][0];
		Pos pos;
		int nx, ny;
		while (!q.isEmpty()) {
			pos = q.poll();

			if (pos.x == N - 1 && pos.y == N - 1) {
				return;
			}

			for (int i = 0; i < 4; i++) {
				nx = pos.x + X[i];
				ny = pos.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if(map[nx][ny][1] > map[pos.x][pos.y][1] + map[nx][ny][0]) {
					map[nx][ny][1] = map[pos.x][pos.y][1] + map[nx][ny][0];
					q.offer(new Pos(nx, ny, map[pos.x][pos.y][1]));
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int t = 0;
		while (true) {
			t++;
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			map = new int[N][N][2]; // 0: 맵, 1:최소 비용

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j][0] = Integer.parseInt(st.nextToken());
					map[i][j][1] = Integer.MAX_VALUE;
				}
			}
			dijkstra();
			sb.append("Problem ").append(t).append(": ").append(map[N - 1][N - 1][1]).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
