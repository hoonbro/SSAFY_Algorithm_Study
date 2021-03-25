package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_3109_빵집 {
	static int N, M;
	static char[][] map;
	static int[] X = { -1, 0, 1 };
	static int[] Y = { 1, 1, 1 };
	static int ans = 0;
	static boolean flag = false;;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		for(int i = 0; i < N; i++) {
			pipe(i, 0);
		}
		System.out.println(ans);
	}

	static void pipe(int x, int y) {
		if (y == M - 1) {
			map[x][y] = 'O';
			flag = true;

			ans++;
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			int nx = x + X[i];
			int ny = y + Y[i];

			if (nx < 0 || nx >= N || map[nx][ny] == 'x')
				continue;

			if (map[nx][ny] == '.') {
				map[nx][ny] = 'O';
				pipe(nx, ny);
				if (flag) {
					if (y == 0)
						flag = false;
					else
						break;
				}
			}

		}
	}
}
