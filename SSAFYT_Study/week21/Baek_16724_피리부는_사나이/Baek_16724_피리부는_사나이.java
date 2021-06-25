package week21;

import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Baek_16724_피리부는_사나이 {
	static int N, M, ans = 0;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	static int[][] map, visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				char c = s.charAt(j);
				if(c == 'U')
					map[i][j] = 0;
				else if(c == 'R')
					map[i][j] = 1;
				else if(c == 'D')
					map[i][j] = 2;
				else
					map[i][j] = 3;
			}
		}
		
		int cnt = 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(visited[i][j] != 0)
					continue;
				dfs(i, j, cnt);
				cnt++;
			}
		}
		System.out.println(ans);
	}
	
	static void dfs(int x, int y, int cnt) {
		if(visited[x][y] != 0) {
			if(visited[x][y] == cnt) {
				ans++;
			}
			return;
		}
		
		visited[x][y] = cnt;
		
		dfs(x + X[map[x][y]], y + Y[map[x][y]], cnt);
	}
}
