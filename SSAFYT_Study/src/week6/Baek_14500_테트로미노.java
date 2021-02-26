package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//dfs
public class Baek_14500_테트로미노 {
	static int N, M, max = -1;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dfs(i, j, 0, 0);
				plus(i, j);
			}
		}
		System.out.println(max);
	}

	static void dfs(int x, int y, int cnt, int sum) {
		if (cnt == 4) {
			max = Math.max(max, sum);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nx = x + X[i];
			int ny = y + Y[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny])
				continue;

			visited[nx][ny] = true;
			dfs(nx, ny, cnt + 1, sum + map[nx][ny]);
			visited[nx][ny] = false;
		}
	}

	static void plus(int x, int y) {
		int cnt = 1;
		int sum = map[x][y];
		int min = 987654321;
		for (int i = 0; i < 4; i++) {
			int nx = x + X[i];
			int ny = y + Y[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;

			cnt++;
			min = Math.min(min, map[nx][ny]);
			sum += map[nx][ny];
		}
		
		if(cnt == 5)
			sum-=min;
		
		max = Math.max(max, sum);
	}
}

