package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_10844_쉬운_계단_수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[][] dp = new long[101][10];
		
		for(int i = 1; i < 10; i++) {
			dp[1][i] = 1;
			dp[2][i] = 2;
		}
		dp[2][9] = 1;
		
		for(int i = 3; i <= N; i++) {
			for(int j = 1; j < 10; j++) {
				if(j == 1)
					dp[i][j] = dp[i-2][j] + dp[i-1][j+1];
				else if(j == 9)
					dp[i][j] = dp[i-1][j-1];
				else
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1];
				
				dp[i][j] %= 1000000000;
			}
		}
		
		long sum = 0;
		for(int i = 1; i < 10; i++)
			sum += dp[N][i];
		
		System.out.println(sum% 1000000000);
	}
}
