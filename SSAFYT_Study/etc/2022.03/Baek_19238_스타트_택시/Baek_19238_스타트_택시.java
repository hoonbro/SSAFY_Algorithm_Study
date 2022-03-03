package etc._2022_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_19238_스타트_택시 {
	static Pos taxi;
	static Pos[] passenger, destination;
	static int N, M, Fuel, INF = Integer.MAX_VALUE, idx = 0;
	static int[] X = { -1, 0, 0, 1 };
	static int[] Y = { 0, -1, 1, 0 };
	static int[][] map;
	static boolean[][] visited;
	static PriorityQueue<Pos> q = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Fuel = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		visited = new boolean[N][N];
		passenger = new Pos[M + 1];
		destination = new Pos[M + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1)
					map[i][j] = -1;
			}
		}

		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken()) - 1;
		int y = Integer.parseInt(st.nextToken()) - 1;

		taxi = new Pos(x, y, 0);

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());

			int x1 = Integer.parseInt(st.nextToken()) - 1;
			int y1 = Integer.parseInt(st.nextToken()) - 1;
			int x2 = Integer.parseInt(st.nextToken()) - 1;
			int y2 = Integer.parseInt(st.nextToken()) - 1;

			map[x1][y1] = i;
			passenger[i] = new Pos(x1, y1, 0);
			destination[i] = new Pos(x2, y2, 0);
		}

		while (M > 0) {
			if (!find_passenger())
				break;
			if (!drop_passenger())
				break;
			M--;
		}

		System.out.println(M > 0 ? -1 : Fuel);
	}

	static boolean find_passenger() {
		init();

		q.offer(taxi);
		visited[taxi.x][taxi.y] = true;
		int min = INF;

		while (!q.isEmpty()) {
			Pos p = q.poll();

			if (map[p.x][p.y] > 0) {
				min = p.cost;
				idx = map[p.x][p.y];
				map[p.x][p.y] = 0;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] == -1)
					continue;

				visited[nx][ny] = true;

				q.offer(new Pos(nx, ny, p.cost + 1));
			}
		}

		if (min <= Fuel) {
			Fuel -= min;
			return true;
		}
		return false;
	}

	static boolean drop_passenger() {
		init();

		int len = Integer.MAX_VALUE;
		visited[passenger[idx].x][passenger[idx].y] = true;
		q.offer(passenger[idx]);

		while (!q.isEmpty()) {
			Pos p = q.poll();

			if (p.x == destination[idx].x && p.y == destination[idx].y) {
				taxi = new Pos(p.x, p.y, 0);
				len = p.cost;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] == -1)
					continue;

				visited[nx][ny] = true;
				q.offer(new Pos(nx, ny, p.cost + 1));
			}
		}

		if (len <= Fuel) {
			Fuel += len;
			return true;
		}
		return false;
	}

	static void init() {
		q.clear();
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
	}

	static class Pos implements Comparable<Pos>{
		int x, y, cost, fuel;

		public Pos(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Pos o) {
			if(this.cost == o.cost) {
				if(this.x == o.x)
					return this.y - o.y;
				return this.x - o.x;
			}
			return this.cost - o.cost;
		}
	}
}
