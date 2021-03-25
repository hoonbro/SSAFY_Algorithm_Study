package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2644_촌수계산 {
	static int N,a,b, M, ans = -1;
	static boolean[] visited;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		map = new int[N+1][N+1];
		
		int x,y;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			map[x][y] = map[y][x] = 1;
		}
		dfs(a, b, 0);
		System.out.println(ans);
	}
	
	static void dfs(int from, int to, int cnt) {
		if(from == to) {
			ans = cnt;
			return;
		}
		
		visited[from] = true;
		
		for(int i = 1; i <= N; i++) {
			if(map[from][i] == 1 && !visited[i])
				dfs(i, to, cnt+1);
		}
	}
}
