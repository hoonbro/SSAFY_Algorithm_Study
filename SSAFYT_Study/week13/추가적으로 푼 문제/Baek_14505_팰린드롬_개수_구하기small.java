package week13.추가;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_14505_팰린드롬_개수_구하기small {
	static boolean flag;
	static char[] str;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine().toCharArray();
		int len = str.length;
		dp = new int[len+1][len+1];
		
		for(int i = 0; i < len; i++) {
			//a
			dp[i][i] = 1;
			if(i+1 >= len)
				break;
			//aa
			if(str[i] == str[i+1])
				dp[i][i+1] = 3;
			
			//ab
			else
				dp[i][i+1] = 2;
		}
		
		int l = 2;
		while(l < len){
	        for (int s = 0; s < len; s++) {
	            int e = s + l;
	            if (e >= len) continue;
	            dp[s][e] = dp[s + 1][e] + dp[s][e - 1] + (str[s] == str[e] ? 1 : -dp[s + 1][e - 1]);
	        }
	        l++;
	    }

		System.out.println(dp[0][len-1]);
	}
	
}
