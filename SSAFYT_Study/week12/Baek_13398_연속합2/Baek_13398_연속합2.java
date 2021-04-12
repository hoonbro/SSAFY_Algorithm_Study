package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_13398_연속합2 {
	static int N;
	static int[] arr;
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N][2];
		st= new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][0] = arr[0];
		dp[0][1] = arr[0];
		int max = arr[0];
		for(int i = 1; i < N; i++) {
			//안건너뛴 값 + 현재값 vs 현재값
			dp[i][0] = Math.max(dp[i-1][0] + arr[i], arr[i]);
			
			//건너뛴값 + 현재 vs 건너뛰지 않은값
			dp[i][1] = Math.max(dp[i-1][0], arr[i] + dp[i-1][1]);
			
			max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
		}
		System.out.println(max);
	}
}
