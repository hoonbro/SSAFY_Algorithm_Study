package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1405_미친로봇 {
	static int N;
	static double ans;
	static int[] per = new int[4];
	static int[] X = {0,0,1,-1};
	static int[] Y = {1,-1,0,0};
	static boolean[][] visited = new boolean[30][30];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		for(int i = 0; i < 4; i++) {
			per[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(15,15,0,1.0);
		
		System.out.println(ans);
	}
	
	static void dfs(int x, int y, int cnt, double percentage) {
		if(cnt ==N) {
			ans += percentage;
			return;
		}
		
		visited[x][y] = true;
		for(int i = 0; i < 4; i++) {
			int nx = x + X[i];
			int ny = y + Y[i];
			
			if(visited[nx][ny])
				continue;
			
			dfs(nx, ny, cnt+1, percentage*per[i]* 0.01);
			visited[nx][ny] = false;
		}
	}
}
