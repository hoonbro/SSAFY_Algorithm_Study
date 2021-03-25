package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Baek_1793_타일링 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BigInteger[] dp = new BigInteger[251];
		dp[0] = BigInteger.ONE;
		dp[1] = BigInteger.ONE;
		dp[2] = new BigInteger("3");
		for(int i = 3; i < dp.length; i++) {
			dp[i] = (dp[i-1]).add(dp[i-2].multiply(new BigInteger("2")));
		}
		
		int N;
		String s;
		while(true) {
			s = br.readLine();
			if(s == null)
				break;
			N = Integer.parseInt(s);
			System.out.println(dp[N]);
		}
	}
}
