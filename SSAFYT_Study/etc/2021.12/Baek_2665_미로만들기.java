package algorithm; 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Baek_2665_미로만들기 { 
	static int[][] map;
	static boolean[][] visited;
	static int N;
	static int[] X = {0,1,0,-1};
	static int[] Y= {-1,0,1,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		bfs();
	}
	 
	static void bfs() {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		pq.offer(new Pos(0,0,0));
		visited[0][0] = true;
		
		while(!pq.isEmpty()) {
			Pos p = pq.poll();
			if(p.x == N-1 && p.y == N-1) {
				System.out.println(p.change);
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				
				if(visited[nx][ny])
					continue;
				
				visited[nx][ny] = true;
				
				if(map[nx][ny] == 1)
					pq.offer(new Pos(nx,ny,p.change));
				else
					pq.offer(new Pos(nx,ny,p.change+1));
			}
		}
	}
	
	static class Pos implements Comparable<Pos>{
		int x, y, change;

		public Pos(int x, int y, int change) {
			this.x = x;
			this.y = y;
			this.change = change;
		}

		@Override
		public int compareTo(Pos o) {
			// TODO Auto-generated method stub
			return this.change - o.change;
		}
	}
}
