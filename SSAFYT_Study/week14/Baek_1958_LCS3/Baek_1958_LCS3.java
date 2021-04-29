package week14;

import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.IOException;

public class Baek_1958_LCS3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] s1 = br.readLine().toCharArray();
		char[] s2 = br.readLine().toCharArray();
		char[] s3 = br.readLine().toCharArray();
		
		int[][][] dp = new int[s1.length+1][s2.length+1][s3.length+1];
		
		for(int i = 1; i <= s1.length; i++) {
			for(int j = 1; j <= s2.length; j++) {
				for(int k = 1; k <= s3.length; k++) {
					if(s1[i-1] == s2[j-1] && s2[j-1] == s3[k-1]) {
						dp[i][j][k] = dp[i-1][j-1][k-1]+1;
					}
					else {
						dp[i][j][k] = Math.max(dp[i-1][j][k], Math.max(dp[i][j-1][k],  dp[i][j][k-1]));
					}
				}
			}
		}
		System.out.println(dp[s1.length][s2.length][s3.length]);
	}
}
