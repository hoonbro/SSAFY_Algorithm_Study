package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_11025_요세푸스문제3 {
	static int N, K;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N+1];
		
		dp[1] = 1;
		
		int r = 0;
		for(int i = 1; i <= N; i++) {
			r = (r+K)%i;
		}
		System.out.println(r+1);
//		for(int i = 1; i < N; i++) {
//			if(dp[i] == i) {
//				dp[i+1] = 2;
//			}
//			else if(dp[i] ==  i-1) {
//				dp[i+1] = 1;
//			}
//			else
//				dp[i+1] = dp[i]+K;
//		}
//		
//		System.out.println(dp[N]);
	}
}
