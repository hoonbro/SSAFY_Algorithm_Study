package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//연구소(bfs, dfs 혼합)
public class Baek_14502 {
	static int N, M, max = -1, safeZone = 0;
	static int[][] map, temp;
	static boolean[][] visited;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static Queue<Pos> q;

	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	//dfs 벽3개 만들기, 순열
	static void makeWall(int x, int y, int cnt) {
		//벽이 3개가 만들어졌을때 max와 bfs값 비교해서 큰값을 max에 할당
		if (cnt == 3) {
			max = Math.max(max, bfs() - 3);
			map[x][y] = 0;
			visited[x][y] = false;
			return;
		}

		for (int i = x; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 && !visited[i][j]) {
					map[i][j] = 1;
					visited[i][j] = true;
					makeWall(i, j, cnt + 1);
				}
			}
		}

		map[x][y] = 0;
		visited[x][y] = false;
	}

	static int bfs() {
		q = new LinkedList<>();
		copyMap();
		int cnt = safeZone;
		while (!q.isEmpty()) {
			Pos pos = q.poll();
			for (int i = 0; i < 4; i++) {
				int dx = pos.x + X[i];
				int dy = pos.y + Y[i];

				if (dx < 0 || dy < 0 || dx >= N || dy >= M)
					continue;
				if (temp[dx][dy] == 0) {
					temp[dx][dy] = 2;
					cnt--;
					q.offer(new Pos(dx, dy));
				}
			}
		}
		return cnt;
	}

	//temp배열에 맵 복사
	static void copyMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
				if (map[i][j] == 2)
					q.offer(new Pos(i, j));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		temp = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					safeZone++;
			}
		}
		makeWall(0,0,0);
		System.out.println(max);
	}
}
