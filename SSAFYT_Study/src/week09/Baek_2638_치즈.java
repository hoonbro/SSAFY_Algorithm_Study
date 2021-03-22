package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2638_치즈 {
	static int N, M, total;
	static int[][] map;
	static int[][] visited;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	static Queue<Pos> q;
	static Queue<Pos> side;
	
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void checkSide() {
		q.offer(new Pos(0,0));
		visited[0][0] = -1;
		Pos p;
		int nx, ny;
		while(!q.isEmpty()) {
			p = q.poll();
			
			for(int i = 0; i < 4; i++) {
				nx = p.x + X[i];
				ny = p.y + Y[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] == -1)
					continue;
				
				
				if(map[nx][ny] == 0) {
					visited[nx][ny] = -1;
					q.offer(new Pos(nx,ny));
				}
				else if(map[nx][ny] == 1) {
					visited[nx][ny]+=1;
				}
			}
		}
	}
	
	static void melt() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(visited[i][j] >= 2) {
					map[i][j] = 0;
					total--;
				}
				visited[i][j] = 0;
			}
		}
	}
			
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		q = new LinkedList<>();
		side = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1)
					total++;
			}
		}
		
		int time = 0;
		while(total != 0) {
			checkSide();
			melt();
			time++;
		}
		System.out.println(time);
	}
}
