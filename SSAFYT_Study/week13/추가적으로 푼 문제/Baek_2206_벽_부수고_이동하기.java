package week13.추가;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2206_벽_부수고_이동하기 {
	static int N, M;
	static int[][] map;
	static int[][] visited;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		
		String s;
		for(int i = 0; i < N; i++) {
			s = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(0, 0, 0, 0));
		map[0][0] = -1;
		visited[0][0] = 0;
		int nx, ny;
		Pos pos;
		while(!q.isEmpty()) {
			pos = q.poll();
			
			if(pos.x == N-1 && pos.y == M-1) {
				return pos.dis+1;
			}
			
			for(int i = 0; i < 4; i++) {
				nx = pos.x + X[i];
				ny = pos.y + Y[i];
				
				// 공사 횟수가 기존 값보다 작거나 같으면 continue
				if(nx < 0 || ny < 0 || nx >= N || ny >=M || visited[nx][ny] <= pos.hammer)
					continue;
				
				//벽이고 부순 횟수가 0이면
				if(map[nx][ny] ==1 && pos.hammer == 0) {
					visited[nx][ny] = pos.hammer+1;
					q.offer(new Pos(nx, ny, pos.dis+1, pos.hammer+1));
					continue;
				}
				// 벽이 아닐때
				if(map[nx][ny] == 0) {
					visited[nx][ny] = pos.hammer;
					q.offer(new Pos(nx, ny, pos.dis+1, pos.hammer));
				}
			}
		}
		return -1;
	}
	
	static class Pos{
		int x, y, dis, hammer;
		
		public Pos(int x, int y, int dis, int hammer) {
			this.x = x;
			this.y = y;
			this.dis = dis;
			this.hammer = hammer;
		}
	}
}
