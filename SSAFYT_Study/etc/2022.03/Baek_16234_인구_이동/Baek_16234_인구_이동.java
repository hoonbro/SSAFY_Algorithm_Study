package etc._2022_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_16234_인구_이동 {
	static int N, L, R;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static int[][] map, visited;
	static Queue<Pos> q, unionQ;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		q = new LinkedList<>();
		unionQ = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean isMove;
		int union, day = 0;
		while (true) {
			visited = new int[N][N];
			isMove = false;
			union = 1;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j] != 0)
						continue;

					if (findUnion(i, j, union++)) {
						isMove = true;
					}
				}
			}
			
			if (!isMove)
				break;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(visited[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			
			day++;
		}

		System.out.println(day);
	}

	static boolean findUnion(int x, int y, int union) {
		q.offer(new Pos(x, y));
		visited[x][y] = union;

		int sum = map[x][y];
		while (!q.isEmpty()) {
			Pos p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] != 0)
					continue;

				if (L > Math.abs(map[p.x][p.y] - map[nx][ny]) || Math.abs(map[p.x][p.y] - map[nx][ny]) > R)
					continue;

				visited[nx][ny] = union;
				q.offer(new Pos(nx, ny));
				unionQ.offer(new Pos(nx, ny));
				sum += map[nx][ny];
			}
		}

		if (unionQ.isEmpty())
			return false;
		move(x, y, sum, union);
		return true;
	}

	static void move(int x, int y, int sum, int union) {
		unionQ.offer(new Pos(x, y));

		int population = sum / unionQ.size();
		System.out.println(population);
		while (!unionQ.isEmpty()) {
			Pos p = unionQ.poll();

			for (int i = 0; i < 4; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] != union)
					continue;

				map[nx][ny] = population;
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
