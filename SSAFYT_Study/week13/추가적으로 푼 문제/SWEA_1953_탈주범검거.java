package week13.추가;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_탈주범검거 {
	static int T, N, M, R, C, L, ans;
	static int[][] map;
	static boolean[][] visited;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	static int[][] shape = {{1,1,1,1},{1,0,1,0},{0,1,0,1},{1,1,0,0},{0,1,1,0},{0,0,1,1},{1,0,0,1}};
	
	static Queue<Pos> q = new LinkedList<>();
	
	static void bfs() {
		q.clear();
		q.offer(new Pos(R, C, map[R][C]));
		visited[R][C] = true;
		Pos pos;
		int nx,ny; 
		while(L-- > 0) {
			int size = q.size();
			
			for(int i = 0; i < size; i++) {
				pos = q.poll();
				ans++;
				
				for(int d = 0; d < 4; d++) {
					nx = pos.x + X[d];
					ny = pos.y + Y[d];
					
					if(!check(nx, ny) || shape[pos.shape-1][d] == 0)
						continue;
					
					if(shape[map[nx][ny] - 1][(d + 2) % 4] == 1) {
						q.offer(new Pos(nx, ny, map[nx][ny]));
						visited[nx][ny] = true;
					}
				}
			}
		}
	}
	
	static boolean check(int x, int y) {
		if(x < 0 || y < 0 || x >= N || y >= M || map[x][y] == 0 || visited[x][y])
			return false;
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken()); //맨홀 세로
			C = Integer.parseInt(st.nextToken()); //맨홀 가로
			L = Integer.parseInt(st.nextToken()); // 탈출 소요시간
			map = new int[N][M];
			visited = new boolean[N][M];
			ans = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			bfs();
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static class Pos{
		int x, y, shape;

		public Pos(int x, int y, int shape) {
			this.x = x;
			this.y = y;
			this.shape = shape;
		}
	}
}
