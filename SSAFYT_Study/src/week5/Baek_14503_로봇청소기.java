package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_14503_로봇청소기 {
	static int N, M, row, col, dir, cnt = 0;
	static int[][] map;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i <N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		clean();
		System.out.println(cnt);
	}
	
	static void clean() {
		int x = row;
		int y = col;
		int d = dir;
		
		while(true) {
			boolean check = false;
			
			if(map[x][y] == 0) {
				map[x][y] = 2;
				cnt++;
			}
			
			for(int i = 0; i <4; i++) {
				d -=1;
				if(d < 0) d+=4;
				
				int nx = x + X[d];
				int ny = y + Y[d];
				
				if(map[nx][ny] == 0) {
					x = nx;
					y = ny;
					check = true;
					break;
				}
			}
			
			if(!check) {
				int tdir = d -2;
				if(tdir < 0) tdir+=4;
				
				x = x + X[tdir];
				y = y + Y[tdir];
				
				if(map[x][y] == 1)
					break;
			}
		}
	}
}
