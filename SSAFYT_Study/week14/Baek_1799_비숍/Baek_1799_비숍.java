package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1799_비숍 {
	static int N, ans1 = 0, ans2 = 0;
	static int[] X = { -1, -1 };
	static int[] Y = { 1, -1 };
	static int[][] map;
	static boolean color;
	static boolean[][] visited;

	static boolean check(int x, int y) {
		int nx, ny;
		for (int d = 0; d < 2; d++) {
			nx = x;
			ny = y;

			while (true) {
				nx += X[d];
				ny += Y[d];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					break;

				if (visited[nx][ny])
					return false;
			}
		}
		return true;
	}

	static void dfs(int idx, int cnt) {
		if(idx == N*N) {
			if (color)
				ans1 = Math.max(ans1, cnt);
			else
				ans2 = Math.max(ans2, cnt);
			return;
		}
		
		int x = idx / N;
		int y = idx % N;

		if ((color && ((x + y) % 2 == 0)) || (!color && ((x + y) % 2 == 1))) 
			dfs(idx + 1, cnt);

		else if (map[x][y] == 0 || !check(x, y))
			dfs(idx + 1, cnt);
		
		else {
			visited[x][y] = true;
			dfs(idx + 1, cnt + 1);
			visited[x][y] = false;
			dfs(idx+1, cnt);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		color = false;
		dfs(0, 0);
		color = true;
		dfs(1, 0);
		System.out.println(ans1 + ans2);
	}
}
