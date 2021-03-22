package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17136_색종이_붙이기 {
	static int[][] map;
	static int[] paper;
	static int ans;

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
		dfs(0, 0);
		for(int i = 0; i < paper.length; i++) {
			if(paper[i] > 5) {
				ans = -1;
				break;
			}
			ans += paper[i];
		}
		System.out.println(ans);
	}

	static boolean possible(int x, int y, int size) {
		if (x + size >= 10 || y + size >= 10)
			return false;

		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (map[i][j] != 1)
					return false;
			}
		}
		return true;
	}

	static void dfs(int x, int y) {
		if (x == 10 && y == 0) {
			return;
		}
		if (map[x][y] !=0 ) {
			for (int size = 5; size >= 1; size--) {
				if (possible(x, y, size)) {
					paper[size]++;
					for(int i = x; i < x+size; i++) {
						for(int j = y; j < y+size; j++) {
							map[i][j] = size;
						}
					}
					
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							System.out.print(map[i][j] + " ");
						}
						System.out.println();
					}
					System.out.println();
					
					break;
				}
			}

		}
		y++;
		if (y == 10) {
			x++;
			y = 0;
		}
		dfs(x, y);

	}
}
