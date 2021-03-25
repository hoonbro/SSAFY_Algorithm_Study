package week01;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2615 {
	static int X[] = { 1, 1, 0, -1 };
	static int Y[] = { 0, 1, 1, 1 };
	static int check[][] = new int[20][20];
	static int go[][] = new int[20][20];
	static int sum = 0;

	static class xy {
		int x;
		int y;
		int cnt;

		public xy(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static int bfs(int x, int y, int dir) {
		Queue<xy> q = new LinkedList<>();
		q.add(new xy(x, y, 1));
		int max = 0;

		while (!q.isEmpty()) {
			xy value = q.poll();
			max = Math.max(max, value.cnt);

			int dx = value.x + X[dir];
			int dy = value.y + Y[dir];

			if (dx < 1 || dy < 1 || dx >= 20 || dy >= 20 || go[dx][dy] != go[value.x][value.y])
				continue;

			q.add(new xy(dx, dy, value.cnt + 1));
		}

		if (max == 5) {
			int a = x - X[dir];
			int b = y - Y[dir];

			if (a < 1 || a < 1 || b >= 20 || b >= 20 || go[a][b] != go[x][y])
				return max;
			max++;

		}

		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 1; i < 20; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < 20; j++) {
				go[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 20; j++) {
				if (go[i][j] != 0) {
					for (int k = 0; k < 4; k++) {
						if (bfs(i, j, k) == 5) {
							System.out.println(go[i][j]);
							System.out.println(i + " " + j);
							return;
						}
					}
				}
			}
		}
		System.out.println(0);
	}

}
