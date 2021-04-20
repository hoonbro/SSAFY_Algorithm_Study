package week13.추가;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2239_스도쿠 {
	static int N = 9;
	static int[][] map;
	static boolean[][][] visited;
	static boolean[][] square;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[N][N];
		visited = new boolean[N + 1][N + 1][2]; // 0가로, 1세로
		square = new boolean[N + 1][N + 1];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j) - '0';
				if (map[i][j] != 0) {
					visited[i][map[i][j]][0] = true;
					visited[j][map[i][j]][1] = true;
					square[(i / 3) * 3 + (j / 3)][map[i][j]] = true;
				}
			}
		}
		dfs(0);
	}

	static void dfs(int cnt) {
		if (cnt == 81) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			System.out.println();
			System.exit(0);
		}

		int x = cnt / N;
		int y = cnt % N;

		if (map[x][y] != 0) {
			dfs(cnt + 1);
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (visited[x][i][0] || visited[y][i][1] || square[(x / 3) * 3 + (y / 3)][i])
				continue;

			map[x][y] = i;
			visited[x][i][0] = true;
			visited[y][i][1] = true;
			square[(x / 3) * 3 + (y / 3)][i] = true;
			dfs(cnt + 1);
			map[x][y] = 0;
			visited[x][i][0] = false;
			visited[y][i][1] = false;
			square[(x / 3) * 3 + (y / 3)][i] = false;
		}
	}
}
