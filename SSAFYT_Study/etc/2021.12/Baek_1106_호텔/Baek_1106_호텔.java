package etc._2021_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1106_호텔 {
	static int C, N, INF = 987654321, ans = 987654321;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int[] dp = new int[C+1001];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int customer = Integer.parseInt(st.nextToken());
			
			for(int j = customer; j < C+1001; j++) {
				dp[j] = Math.min(dp[j], cost + dp[j-customer]);
			}
		}
		
		for(int i = C; i < C+100; i++) {
			ans = Math.min(ans, dp[i]);
		}
		System.out.println(ans);
	}
}
