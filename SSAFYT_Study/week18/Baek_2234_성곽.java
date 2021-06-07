package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2234_성곽 {
	static int N, M, removeMax = 0, max = 0, total = 0;
	static int[] X = {0,-1,0,1};
	static int[] Y = {-1,0,1,0};
	static int[][][] map;
	static boolean[][] visited;
	static int[] room;
	static Queue<Pos> q = new LinkedList<>();

	static int bfs() {
		int nx, ny, cnt = 1;
		Pos pos;
		while(!q.isEmpty()) {
			pos = q.poll();
			
			for(int i = 0; i < 4; i++) {
				if(((1<<i) & map[pos.x][pos.y][0]) != 0 ) 
					continue;
				
				nx = pos.x + X[i];
				ny = pos.y + Y[i];
				
				if(nx <0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny])
					continue;
				
				visited[nx][ny] = true;
				map[nx][ny][1] = total;
				q.offer(new Pos(nx,ny));
				cnt++;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M][2];
		visited = new boolean[N][M];
		room = new int[N*M+1];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					map[i][j][1] = total;
					q.offer(new Pos(i, j));
					room[total] = bfs();
					max = Math.max(max, room[total]);
					total++;
				}
			}
		}
		int nx, ny;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				for(int d = 2; d < 4; d++) {
					nx = i + X[d];
					ny = j + Y[d];
					
					if(nx < 0 || ny < 0 || nx >= N || ny>= M)
						continue;
					if(map[i][j][1] == map[i+X[d]][j+Y[d]][1]) 
						continue;
					
					removeMax = Math.max(removeMax, room[map[i][j][1]] + room[map[i+X[d]][j+Y[d]][1]]);
				}
			}
		}
		System.out.println(total);
		System.out.println(max);
		System.out.println(removeMax);
	}

	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
