package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17182_우주_탐사선 {
	static int N, K, ans = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		floyd();

		visited[K] = true;
		dfs(K, 0, 1);
		System.out.println(ans);
	}
	
	static void dfs(int now, int sum, int cnt) {
		if(cnt == N) {
			ans = Math.min(ans, sum);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(visited[i])
				continue;
			
			visited[i] = true;
			dfs(i, sum + map[now][i], cnt+1);
			visited[i] = false;
		}
	}
	
	static void floyd() {
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(i == j)
						continue;
					
					if(map[i][j] > map[i][k] + map[k][j])
						map[i][j] = map[i][k] + map[k][j];
				}
			}
		}
	}
}
