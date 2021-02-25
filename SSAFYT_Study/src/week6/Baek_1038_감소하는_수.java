package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek_1038_감소하는_수 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[11][10];
		if(N <10) {
			System.out.println(N);
			return;
		}
		
		for(int i = 0; i < 10; i++)
			dp[0][i] = 1;
		
		int ans = 9;
		for(int i = 1; i < 10; i++) {
			for(int j = i; j < 10; j++) { 
				dp[i][j] = dp[i][j-1] + dp[i-1][j-1];
				ans += dp[i][j];
				if(ans >= N) {
					sb.append(j);
					
					ans = ans - N + 1;
					i -= 1;
					for(int b = j-1; b >= 0; b--) {// 현재보다 작은 수들만 확인
						ans -= dp[i][b];
						
						if(ans <= 0) {
							ans += dp[i][b];
							sb.append(b);
							
							if(i == 0)
								break;
							i--;
						}
					}
					System.out.println(sb.toString());
					return;
				}
			}
		}
		System.out.println(-1);
	}
}