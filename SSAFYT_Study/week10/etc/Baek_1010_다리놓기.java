package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1010_다리놓기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[31][31];
		for(int i = 0; i <= 30; i++) {
			dp[0][i] = 1;
			dp[i][0] = 1;
		}
		
		dp[0][0] = 0;
		for(int i = 1; i <= 30; i++) {
			for(int j = i; j <= 30; j++) {
				dp[i][j] = dp[i][j-1] + dp[i-1][j-1];
			}
		}
		
		int a, b;
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			sb.append(dp[a][b]).append("\n");
		}
		System.out.println(sb.toString());
	}

}
