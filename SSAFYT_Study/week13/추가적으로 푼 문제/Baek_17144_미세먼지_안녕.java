package week13.추가;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17144_미세먼지_안녕 {
	static int N, M, T, total;
	static int map[][], temp[][];
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	static Cleaner cleaner;
	
	static void spread() {
		int nx = 0, ny=0;
		int cnt;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] < 5) {
					temp[i][j] += map[i][j];
					continue;
				}
				cnt = 0;
				
				for(int d = 0; d < 4; d++) {
					nx = i + X[d];
					ny = j + Y[d];
					if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == -1)
						continue;
					
					temp[nx][ny] += map[i][j]/5;
					cnt++;
				}
					temp[i][j] += map[i][j] - (map[i][j]/5)*cnt ;
			}
		}
		
		//배열 복사
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = temp[i][j];
				temp[i][j] = 0;
			}
		}
		map[cleaner.x1][0] = -1;
		map[cleaner.x2][0] = -1;
	}
	
	static void clean() {
		int x1 = cleaner.x1;
		int x2 = cleaner.x2;
		for(int i = x1-1; i > 0; i--) 
			map[i][0] = map[i-1][0];
		for(int i = 0; i < M-1; i++) 
			map[0][i] = map[0][i+1];
		for(int i = 0; i < x1; i++)
			map[i][M-1] = map[i+1][M-1];
		for(int i = M-1; i > 1; i--) 
			map[x1][i] = map[x1][i-1];
		map[x1][1] = 0;
		
		for(int i = x2+1; i < N-1; i++) 
			map[i][0] = map[i+1][0];
		for(int i = 0; i < M-1; i++) 
			map[N-1][i] = map[N-1][i+1];
		for(int i = N-1; i > x2; i--)
			map[i][M-1] = map[i-1][M-1];
		for(int i = M-1; i > 0; i--)
			map[x2][i] = map[x2][i-1];
		map[x2][1] = 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		temp = new int[N][M];
		
		boolean flag = false;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1 && !flag) {
					flag = true;
					cleaner = new Cleaner(i, i+1, j);
				}
			}
		}
		while(T-- > 0) {
			spread();
			clean();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] > 0)
					total += map[i][j];
			}
		}
		System.out.println(total);
	}
	
	static class Cleaner{
		int x1, x2, y;

		public Cleaner(int x1, int x2, int y) {
			this.x1 = x1;
			this.x2 = x2;
			this.y = y;
		}
	}
}
