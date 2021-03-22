package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_10422_괄호 {
	static int N;
	static long[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		dp = new long[5001];

		dp[0] = 1;
		dp[2] = 1;

		for (int i = 4; i < dp.length; i += 2) {
			for (int j = 0; j <= i-2; j += 2) {
				dp[i] = (dp[i] + dp[j] * dp[i-2-j])%1000000007L;
			}
		}
		
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp[n]).append("\n");
		}

		System.out.println(sb.toString());
	}
}
