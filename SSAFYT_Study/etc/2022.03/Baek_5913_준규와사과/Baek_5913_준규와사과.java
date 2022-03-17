package etc._2022_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_5913_준규와사과 {
	static int N, total, ans;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[5][5];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;

			map[x][y] = 1;
		}
		
		total = 24- N;
		ans = 0;
		map[0][0] = 2;
		map[4][4] = 3;
		dfs(0,0,4,4,0);
		System.out.println(ans);
	}
	
	static void dfs(int x1, int y1, int x2, int y2, int cnt) {
		if(x1 == x2 && y1 == y2) {
			if(cnt == total) {
				ans++;
			}
			
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nx1 = x1 + X[i];
			int ny1 = y1 + Y[i];
			
			if(nx1 < 0 || ny1 < 0 || nx1 >= 5 || ny1 >= 5 || map[nx1][ny1] >= 1 )
				continue;
			
			for(int j = 0; j < 4; j++) {
				int nx2 = x2 + X[j];
				int ny2 = y2 + Y[j];
				
				if(nx2 < 0 || ny2 < 0 || nx2 >= 5 || ny2 >= 5 || map[nx2][ny2] >= 1 )
					continue;
				
				map[nx1][ny1] = 2;
				map[nx2][ny2] = 3;
				dfs(nx1, ny1, nx2, ny2, cnt+2);
				map[nx1][ny1] = 0;
				map[nx2][ny2] = 0;
			}
		}
	}
}
