package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17136_색종이_붙이기2 {
	static int[][] map;
	static int[] paper;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[10][10];
		paper = new int[6];

		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0, 0);
		if (ans == Integer.MAX_VALUE)
			ans = -1;
		System.out.println(ans);
	}

	static boolean possible(int x, int y, int size) {
		if (x + size > 10 || y + size > 10)
			return false;

		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (map[i][j] != 1)
					return false;
			}
		}
		return true;
	}

	static void paperfill(int x, int y, int size, int n) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				map[i][j] = n;
			}
		}
	}

	static void dfs(int x, int y, int cnt) {
		if (x == 10 && y == 0) {
			ans = Math.min(cnt, ans);
			return;
		}

		if (ans <= cnt)
			return;

		if (map[x][y] == 1) {
			for (int size = 5; size >= 1; size--) {
				if (paper[size] < 5 && possible(x, y, size)) {
					paper[size]++;
					paperfill(x, y, size, 0);
					if (y == 9) {
						dfs(x + 1, 0, cnt + 1);
					} else
						dfs(x, y + 1, cnt + 1);
					paperfill(x, y, size, 1);
					paper[size]--;
				}
			}

		} else {
			y++;
			if (y == 10) {
				x++;
				y = 0;
			}
			dfs(x, y, cnt);
		}
	}
}
