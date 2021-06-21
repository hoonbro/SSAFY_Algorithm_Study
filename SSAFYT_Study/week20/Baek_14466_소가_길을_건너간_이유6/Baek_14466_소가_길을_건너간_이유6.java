package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_14466_소가_길을_건너간_이유6 {
	static int N, K, R, ans = 0;
	static int[][][] map;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static boolean visited[][];
	static Queue<Pos> q = new LinkedList<>();
	static ArrayList<Pos> al = new ArrayList<>();

	static void bfs(int x, int y) {
		q.offer(new Pos(x, y));
		visited = new boolean[N+1][N+1];
		visited[x][y] = true;
		Pos p;
		int nx, ny;
		while(!q.isEmpty()) {
			p = q.poll();
			
			
			for(int i = 0; i < 4; i++) {
				if((map[p.x][p.y][0] & (1 << i)) != 0)
					continue;
				
				nx = p.x + X[i];
				ny = p.y + Y[i];
				
				
				if(nx < 1 || ny < 1 || nx > N || ny > N || visited[nx][ny])
					continue;
				
				visited[nx][ny] = true;
				q.offer(new Pos(nx, ny));
			}
		}

		
		for(int i = 0; i < al.size(); i++) {
			p = al.get(i);
			if(!visited[p.x][p.y])
				ans++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1][2];
		
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			if (x1 > x2) {
				map[x1][y1][0] += 1;
				map[x2][y2][0] += 4;
			} else if (y1 < y2) {
				map[x1][y1][0] += 2;
				map[x2][y2][0] += 8;
			} else if (x1 < x2) {
				map[x1][y1][0] += 4;
				map[x2][y2][0] += 1;
			} else if (y1 > y2) {
				map[x1][y1][0] += 8;
				map[x2][y2][0] += 2;
			}
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y][1] = 1;
			al.add(new Pos(x,y));
		}
		
		for(int i = 0; i < K; i++) {
			bfs(al.get(0).x, al.get(0).y);
			al.remove(0);
		}
		System.out.println(ans);
	}

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

