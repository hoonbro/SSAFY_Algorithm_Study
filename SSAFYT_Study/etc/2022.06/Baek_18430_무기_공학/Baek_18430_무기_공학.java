package etc._2022_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_18430_무기_공학 {
	static int N, M, max;
	static int[] X = { 0, 1, 0, -1, - 1, 0, 0, 1 };
	static int[] Y = { -1, 0, -1, 0, 0, 1, 1, 0 };
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		max = 0;
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0,0);
		
		System.out.println(max);
	}

	static void dfs(int idx, int sum) {
		if (idx == N*M) {
			max = Math.max(sum, max);
			return;
		}
		
		int x = idx/N;
		int y = idx%M;
		
		if(visited[x][y]) {
			dfs(idx+1, sum);
			return;
		}

		for(int i = 0; i < 8; i+=2) {
			int nx1 = x + X[i];
			int ny1 = y + Y[i];
			int nx2 = x + X[i+1];
			int ny2 = y + Y[i+1];
			
			if(check(nx1, ny1) && check(nx2, ny2)) {
				//사용
				visited[x][y] = true;
				visited[nx1][ny1] = true;
				visited[nx2][ny2] = true;
				dfs(idx+1, sum + map[x][y]*2 + map[nx1][ny1] + map[nx2][ny2]);
				visited[x][y] = false;
				visited[nx1][ny1] = false;
				visited[nx2][ny2] = false;
			}
			
		}
		dfs(idx+1, sum);
	}
	
	
	static boolean check(int x,  int y) {
		if(x < 0 || y < 0 || x >= N || y >= M || visited[x][y])
			return false;
		return true;
	}
}
