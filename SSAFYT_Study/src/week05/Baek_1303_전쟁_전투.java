package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//전쟁 - 전투
public class Baek_1303_전쟁_전투 {
	static char[][] soldier;
	static int N, M, white = 0, blue = 0;
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		soldier = new char[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				soldier[i][j] = s.charAt(j);
			}
		}

		q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (soldier[i][j] == 'W' || soldier[i][j] == 'B') {
					q.offer(new Pos(i, j));
					near(soldier[i][j]);
				}
			}
		}

		System.out.println(white + " " + blue);
	}

	private static void near(char c) {
		Pos pos;
		int cnt = 1;
		soldier[q.peek().x][q.peek().y] = '0';

		while (!q.isEmpty()) {
			pos = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = pos.x + X[i];
				int ny = pos.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;

				if (soldier[nx][ny] == c) {
					soldier[nx][ny] = '0';
					cnt++;
					q.offer(new Pos(nx, ny));
				}
			}
		}

		if (c == 'W')
			white += Math.pow(cnt, 2);
		else
			blue += Math.pow(cnt, 2);
	}
}
