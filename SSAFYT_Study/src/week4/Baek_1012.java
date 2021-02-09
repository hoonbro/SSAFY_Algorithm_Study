package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//유기농 배추
public class Baek_1012 {
	static int N, M;
	static int[][] map;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };

	static void dfs(int x, int y) {
		map[x][y] = 2;
		for(int i = 0; i < 4; i++) {
			int dx = x + X[i];
			int dy = y + Y[i];
			
			if(dx < 0 || dy < 0 || dx >=N || dy >= M)
				continue;
			if(map[dx][dy] != 1)
				continue;
			dfs(dx, dy);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());

		while (t-- != 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // row
			M = Integer.parseInt(st.nextToken()); // col
			int K = Integer.parseInt(st.nextToken()); // 배추의 갯수
			map = new int[N][M];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = 1;
			}
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						dfs(i, j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}

}
