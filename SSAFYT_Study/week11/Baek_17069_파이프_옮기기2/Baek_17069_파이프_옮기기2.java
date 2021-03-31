package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17069_파이프_옮기기2 {
	static int N;
	static int[][] map;
	static long[][][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		dp = new long[N+1][N+1][3]; //가로, 세로, 대각선
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[1][2][0] = 1;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(map[i][j] != 0)
					continue;
				// 가로
				if(j <= N && map[i][j-1] == 0) {
					dp[i][j][0] += dp[i][j-1][0] + dp[i][j-1][2];
				}
				// 세로				
				if (i <= N && map[i-1][j] == 0) {
					dp[i][j][1] += dp[i-1][j][1] + dp[i-1][j][2];
				}
				//대각선
				if(i <= N && j <= N  && map[i-1][j-1] == 0 && map[i-1][j] == 0 && map[i][j-1] == 0) {
					dp[i][j][2] += dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
			}
		}
		
		long ans = 0;
		for(int i = 0; i < 3; i++)
			ans += dp[N][N][i];
		System.out.println(ans);
	}
}
