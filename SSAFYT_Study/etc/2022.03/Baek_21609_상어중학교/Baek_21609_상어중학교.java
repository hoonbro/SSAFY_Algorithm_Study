package etc._2022_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_21609_상어중학교 {
	static Pos maxPos;
	static int N, M, max = 0, rainbowMax, ans = 0;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static int[][] visited, map;
	static Queue<Pos> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			visited = new int[N][N];
			max = 0;
			rainbowMax = 0;
			int group = 1;
			boolean isEnd = true;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] <= 0 || visited[i][j] != 0)
						continue;

					boolean flag = false;
					for (int d = 0; d < 4; d++) {
						int nx = i + X[d];
						int ny = j + Y[d];

						if (!check(map[i][j], group, nx, ny))
							continue;

						flag = true;
					}
					if (!flag)
						continue;

					isEnd = false;
					findGroup(i, j, map[i][j], group++);
				}
			}
			if (isEnd)
				break;

			deleteGroup();
			gravity();
			map = rotate();
			gravity();
		}

		System.out.println(ans);
	}

	static void findGroup(int x, int y, int color, int group) {
		visited[x][y] = group;
		q.offer(new Pos(x, y));

		int cnt = 1;
		int rainbowCnt = 0;
		while (!q.isEmpty()) {
			Pos p = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = p.x + X[d];
				int ny = p.y + Y[d];

				if (!check(color, group, nx, ny))
					continue;

				visited[nx][ny] = group;
				q.offer(new Pos(nx, ny));
				cnt++;
				if (map[nx][ny] == 0)
					rainbowCnt++;
			}
		}

		if (cnt > max) {
			max = cnt;
			maxPos = new Pos(x, y);
			rainbowMax = rainbowCnt;
		} else if (cnt == max && rainbowCnt > rainbowMax) {
			max = cnt;
			maxPos = new Pos(x, y);
			rainbowMax = rainbowCnt;
		} else if (cnt == max && rainbowCnt == rainbowMax) {
			if (maxPos.x < x || (maxPos.x == x && maxPos.y < y)) {
				max = cnt;
				maxPos = new Pos(x, y);
				rainbowMax = rainbowCnt;
			}
		}
	}

	static void deleteGroup() {
		q.offer(maxPos);
		ans += max * max;
		int color = map[maxPos.x][maxPos.y];

		while (!q.isEmpty()) {
			Pos p = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = p.x + X[d];
				int ny = p.y + Y[d];

				if (!check(color, -1, nx, ny))
					continue;

				q.offer(new Pos(nx, ny));
				map[nx][ny] = -2;
				visited[nx][ny] = -1;
			}
		}
	}

	static void gravity() {
		for (int j = 0; j < N; j++) {
			for (int i = N - 1; i > 0; i--) {
				if (map[i][j] != -2)
					continue;

				int idx = i;
				while (idx-- > 0) {
					if (map[idx][j] == -1)
						break;

					if (map[idx][j] == -2)
						continue;

					swap(idx, i, j);
					break;
				}
			}
		}
	}

	static int[][] rotate() {
		int[][] arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[N-j-1][i] = map[i][j];
			}
		}

		return arr;
	}

	static void swap(int x1, int x2, int y) {
		int temp = map[x1][y];
		map[x1][y] = map[x2][y];
		map[x2][y] = temp;
	}

	static boolean check(int color, int group, int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] <= -1)
			return false;

		if (map[nx][ny] != 0 && (color != map[nx][ny] || visited[nx][ny] == group))
			return false;

		if (map[nx][ny] == 0 && visited[nx][ny] == group)
			return false;

		return true;
	}

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
