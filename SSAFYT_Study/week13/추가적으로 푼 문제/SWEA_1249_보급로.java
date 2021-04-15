package week13.추가;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class SWEA_1249_보급로 {
	static int N, T, ans;
	static int[][] map;
	static boolean[][] visited;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				String s = br.readLine();
				for(int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j) - '0';
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
	
	static void bfs() {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		pq.offer(new Pos(0,0,map[0][0]));
		visited[0][0] = true;
		Pos pos;
		int nx, ny;
	
		while(!pq.isEmpty()) {
			pos = pq.poll();
			
			if(pos.x == N-1 && pos.y == N-1) {
				ans = pos.cost;
				return;
			}
			
			for(int d = 0; d < 4; d++) {
				nx = pos.x + X[d];
				ny = pos.y + Y[d];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])
					continue;
				
				visited[nx][ny] = true;
				pq.offer(new Pos(nx,ny,pos.cost+map[nx][ny]));
			}
		}
	}
	
	static class Pos implements Comparable<Pos>{
		int x, y, cost;

		public Pos(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		public int compareTo(Pos o) {
			return this.cost - o.cost;
		}
	}
}
