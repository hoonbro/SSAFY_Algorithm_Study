package week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//dfs
public class Baek_2606_바이러스 {
	static int N;
	static int[][] map;
	static boolean[] visited;
	static int cnt=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		visited = new boolean[N+1];
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1;
			map[y][x] = 1;
		}
		dfs(1);
		System.out.println(cnt);
	}
	
	static void dfs(int idx) {
		visited[idx] = true;
		for(int i = 1; i <= N; i++) {
			if(map[idx][i] == 1 && !visited[i]) {
				cnt++;
				dfs(i);
			}
		}
	}
}
