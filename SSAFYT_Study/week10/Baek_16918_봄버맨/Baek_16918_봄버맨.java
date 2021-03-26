package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_16918_봄버맨 {
	static int N, M, T, nx,ny;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	static char[][] map;
	static int[][] time;
	
	static class Pos{
		int x, y, cnt;

		public Pos(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.cnt = time;
		}
	}
	
	static void boom() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(time[i][j] == 1) {
					map[i][j] = '.';
					for(int d = 0; d < 4; d++) {
						nx = i + X[d];
						ny = j + Y[d];
						
						if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == '.')
							continue;
						
						map[nx][ny] = '.';
					}
				}
				else {
					if(map[i][j] == '.')
						continue;
					time[i][j]--;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		time = new int[N][M];
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'O') {
					time[i][j] = 3;
				}
			}
		}
		boom();
		while(T-- > 1) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == '.') {
						time[i][j] = 4;
						map[i][j] = 'O';
					}
				}
			}
			boom();

		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
