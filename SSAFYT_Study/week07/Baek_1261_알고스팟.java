package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1261_알고스팟 {
	static int N, M, ans = Integer.MAX_VALUE;
	static int[][] map;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	
	static class Pos implements Comparable<Pos>{
		int x;
		int y;
		int cnt;
		Pos(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Pos o) {
			// TODO Auto-generated method stub
			return this.cnt - o.cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		bfs();
		System.out.println(ans);
	}
	
	static void bfs() {
		PriorityQueue<Pos> q = new PriorityQueue<>();
		q.offer(new Pos(0,0,0));
		map[0][0] = 2;
		Pos pos;
		
		while(!q.isEmpty()) {
			pos = q.poll();
			
			if(pos.x == N-1 && pos.y == M-1) {
				ans = Math.min(ans, pos.cnt);
				continue;
			}
			
			for(int i = 0; i< 4; i++) {
				int nx = pos.x + X[i];
				int ny = pos.y + Y[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				
				if(map[nx][ny] == 1) {
					q.offer(new Pos(nx,ny,pos.cnt+1));
				}else if(map[nx][ny] == 0){
					q.offer(new Pos(nx,ny,pos.cnt));
				}
				map[nx][ny] = 2;
			}
		}
	}
}
