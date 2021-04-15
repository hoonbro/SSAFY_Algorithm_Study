package week13.추가;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1976_여행가자 {
	static int N, M, from, to;
	static boolean[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new boolean[N+1][N+1];
		
		int a = 0;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				a = Integer.parseInt(st.nextToken());
				
				if(i == j)
					map[i][j] = true;
				else if(a == 1)
					map[i][j] = true;
				else
					map[i][j] = false;
			}
		}
		
		floyd();
		
		st = new StringTokenizer(br.readLine());
		from = Integer.parseInt(st.nextToken());
		for(int i = 1; i < M; i++) {
			to = Integer.parseInt(st.nextToken());
			
			if(!map[from][to]) {
				System.out.println("NO");
				return;
			}
			from = to;
		}
		System.out.println("YES");
	}	
	
	
	static void floyd() {
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(map[i][k] && map[k][j])
						map[i][j] = true;
				}
			}
		}
	}
}
