package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2240_자두나무 {
	static int T, W;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		dp = new int[T+1][W+1]; // [시간][이동횟수]

		for (int i = 1; i <= T; i++) {
			int t = Integer.parseInt(br.readLine());

			for (int j = 0; j <= W; j++) {
				// 움직임이 없는 경우
				// 나무 1에 떨어지면 1 추가 / 나무 2에 떨어지면 아무일없음
				if (j == 0) {
					if (t == 1)
						dp[i][j] = dp[i - 1][j] + 1;
					else
						dp[i][j] = dp[i - 1][j];

				}

				// 짝수번 움직이면 나무 1
				else if (j % 2 == 0) {
					if (t == 1)
						dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j] + 1);
					else
						dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i - 1][j]);
				}

				// 홀수번 움직이면 나무 2
				else {
					if (t == 1)
						dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i - 1][j]);
					else
						dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j] + 1);
				}
			}
		}
		
		int ans = 0;
		for(int i = 0; i <= W; i++) {
			ans = Math.max(ans, dp[T][i]);
		}
		System.out.println(ans);
	}

}
