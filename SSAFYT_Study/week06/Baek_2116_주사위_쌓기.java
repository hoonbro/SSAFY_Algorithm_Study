package week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2116_주사위_쌓기 {
	static int[][] dice;
	static int N;
	static int max = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		dice = new int[N][6];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			// af, db, ce
			dice[i][0] = Integer.parseInt(st.nextToken());//a
			dice[i][1] = Integer.parseInt(st.nextToken());//b
			dice[i][2] = Integer.parseInt(st.nextToken());//c
			dice[i][4] = Integer.parseInt(st.nextToken());//f
			dice[i][5] = Integer.parseInt(st.nextToken());//d
			dice[i][3] = Integer.parseInt(st.nextToken());//e
		}

		for (int i = 0; i < 6; i++) {
			dfs(0, dice[0][i], 0);
		}
		System.out.println(max);
	}

	static void dfs(int floor, int top, int sum) {
		if (floor == N) {
			max = Math.max(sum, max);
			return;
		}

		for (int i = 0; i < 6; i++) {
			if (dice[floor][i] != top)
				continue;

			int temp = 0;
			for (int j = 0; j < 6; j++) {
				if (j == i || (j + 3)%6 == i)
					continue;
				temp = Math.max(temp, dice[floor][j]);
			}
			dfs(floor + 1, dice[floor][(i + 3) % 6], sum + temp);
		}
	}
}
