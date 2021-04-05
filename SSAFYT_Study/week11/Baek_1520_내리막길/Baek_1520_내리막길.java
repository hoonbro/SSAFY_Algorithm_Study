package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1520_내리막길 {
	static int N, M;
	static int[][] map;
	static int[][] dp;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		


		System.out.println(dfs(0,0));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static int dfs(int x, int y) {
		if(x == N -1&& y == M-1) {
			if(dp[x][y] == -1) {
				System.out.println("gd");
				dp[x][y] = 1;
			}
			else {
				System.out.println("dp");
				dp[x][y] += 1;
			}
			return dp[x][y];
		}
		
		if(dp[x][y] != -1)
			return dp[x][y];
		
		int nx, ny;
		dp[x][y] = 0;
		for(int i = 0; i < 4; i++) {
			nx = x + X[i];
			ny = y + Y[i];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;
			
			if(map[nx][ny] < map[x][y])
				dp[x][y] += dfs(nx, ny);
		}
		return dp[x][y];
	}
}
