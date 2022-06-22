package etc._2022_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1577_도로의_개수 {
	static int N, M, K;
	static int[] X = { 0, 1 };
	static int[] Y = { 1, 0 };
	static long[][] dp;
	static boolean[][][] broken;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new long[N + 2][M + 2];
		broken = new boolean[N + 2][M + 2][2];

		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken()) + 1;
			int b = Integer.parseInt(st.nextToken()) + 1;
			int c = Integer.parseInt(st.nextToken()) + 1;
			int d = Integer.parseInt(st.nextToken()) + 1;

			if (a < c) {
				broken[a][b][1] = true;
			} else if (a > c) {
				broken[c][d][1] = true;
			} else if (b < d) {
				broken[a][b][0] = true;
			} else if (b > d) {
				broken[c][d][0] = true;
			}
		}

		dp[1][1] = 1;
		for(int i = 1; i <= N+1; i++) {
			for(int j = 1; j<= M+1; j++) {
				if(!broken[i-1][j][1]) {
					dp[i][j] += dp[i-1][j];
				}
				if(!broken[i][j-1][0]) {
					dp[i][j] += dp[i][j-1];
				}
			}
		}

		System.out.println(dp[N+1][M+1]);
	}
}
