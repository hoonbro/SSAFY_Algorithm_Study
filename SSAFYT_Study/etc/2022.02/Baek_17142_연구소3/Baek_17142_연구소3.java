package etc._2022_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_17142_연구소3 {
	static int N, M, ans = Integer.MAX_VALUE, empty = 0;
	static Virus[] arr;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Virus> al = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new Virus[M];
		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 0)
					empty++;
				else if (map[i][j] == 2) {
					al.add(new Virus(i, j, 0));
				}
			}
		}

		if (empty == 0) {
			System.out.println(0);
		} else {
			dfs(0, 0);
			System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
		}
	}

	static void bfs() {
		Queue<Virus> q = new LinkedList<>();
		visited = new boolean[N][N];
		int cnt = 0;

		for (int i = 0; i < M; i++) {
			q.offer(arr[i]);
			visited[arr[i].x][arr[i].y] = true;
		}

		while (!q.isEmpty()) {
			Virus virus = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = virus.x + X[i];
				int ny = virus.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1 || visited[nx][ny])
					continue;

				if (map[nx][ny] == 0)
					cnt++;

				if (empty == cnt) {
					ans = Math.min(virus.time + 1, ans);
					return;
				}

				visited[nx][ny] = true;
				q.offer(new Virus(nx, ny, virus.time + 1));
			}
		}
	}

	static void dfs(int idx, int cnt) {
		if (cnt == M) {
			bfs();
			return;
		}

		for (int i = idx; i < al.size(); i++) {
			arr[cnt] = al.get(i);
			dfs(i + 1, cnt + 1);
		}
	}

	static class Virus {
		int x, y, time;

		public Virus(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}
