package etc._2022_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_12869_뮤탈리스크 {
	static int N, ans = Integer.MAX_VALUE;
	static int[] SCV;
	static boolean[][][] visited;
	static int[][] damage = {{1,3,9},{1,9,3},{3,1,9},{3,9,1},{9,3,1},{9,1,3}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		SCV = new int[3];
		visited = new boolean[61][61][61];
		for(int i = 0; i < N; i++) {
			SCV[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(SCV, 0);
		
		System.out.println(ans);
	}
	
	static void dfs(int[] hp, int cnt) {
		if(ans < cnt)
			return;
		
		Arrays.sort(hp);
		
		if(hp[0] <= 0 && hp[1] <= 0 && hp[2] <= 0) {
			ans = Math.min(cnt, ans);
			return;
		}
		
		if(visited[hp[0]][hp[1]][hp[2]])
			return;
		
		visited[hp[0]][hp[1]][hp[2]] = true;
		
		for(int i = 0; i < 6; i++) {
			dfs(new int[] {Math.max(hp[0]-damage[i][0], 0), Math.max(hp[1]-damage[i][1],0), Math.max(hp[2]-damage[i][2], 0)}, cnt + 1);
		}
	}
}
