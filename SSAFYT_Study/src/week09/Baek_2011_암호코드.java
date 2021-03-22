package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2011_암호코드 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		s = " " + s;
		long[] dp = new long[s.length()];
		
		dp[0] = 1;
		
		for(int i = 1; i < s.length(); i++) {
			//두자리 가능
			if(s.charAt(i-1) == '1') {
				if(s.charAt(i) == '0') {
					dp[i] = dp[i-2]%1000000;
				}
				else
					dp[i] = (dp[i-1]+dp[i-2])%1000000;
			}
			else if(s.charAt(i-1) == '2') {
				if(s.charAt(i)>='1' && s.charAt(i) <= '6') {
					dp[i] = (dp[i-1]+dp[i-2])%1000000;
				}
				else if(s.charAt(i) == '0') {
					dp[i] = dp[i-2]%1000000;
				}
				else
					dp[i] = dp[i-1]%1000000;
			}
			//한자리만 가능
			else {
				if(s.charAt(i) == '0') {
					System.out.println(0);
					return;
				}
				dp[i] = dp[i-1]%1000000;
			}
		}
		
		System.out.println((dp[s.length()-1])%1000000);
	}
}
