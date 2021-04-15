package week13.추가;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_14442_벽_부수고_이동하기2 {
	static int N, M, K;
	static int[][] map;
	static boolean[][][] visited;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][K+1];
		
		String s;
		for(int i = 0; i < N; i++) {
			s = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		System.out.println(bfs());
	}
	
	static int bfs() {
		PriorityQueue<Pos> q = new PriorityQueue<>();
		q.offer(new Pos(0, 0, 0, 0));
		visited[0][0][0] = true;
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
				if(nx < 0 || ny < 0 || nx >= N || ny >=M || visited[nx][ny][pos.cnt])
					continue;
				
				//벽이고 부순 횟수가 0이면
				if(map[nx][ny] ==1 &&pos.cnt + 1 <= K ) {
					visited[nx][ny][pos.cnt] = true;
					q.offer(new Pos(nx, ny, pos.dis+1, pos.cnt+1));
					continue;
				}
				// 벽이 아닐때
				if(map[nx][ny] == 0) {
					visited[nx][ny][pos.cnt] = true;
					q.offer(new Pos(nx, ny, pos.dis+1, pos.cnt));
				}
			}
		}
		return -1;
	}
	
	static class Pos implements Comparable<Pos>{
		int x, y, dis, cnt;
		
		public Pos(int x, int y, int dis, int cnt) {
			this.x = x;
			this.y = y;
			this.dis = dis;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Pos o) {
			return this.dis - o.dis;
		}
	}
}
