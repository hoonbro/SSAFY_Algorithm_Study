package etc._2022_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2146_다리_만들기 {
	static int N, ans = Integer.MAX_VALUE;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static boolean[][] visited;
	static int[][] map;
	static Queue<Pos> q = new LinkedList<>();
	static ArrayList<Pos> al;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		visited = new boolean[N][N];
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					al = new ArrayList<>();
					bfs(i, j, cnt);
					for(Pos start : al) {
						makeBridge(start, cnt);
					}
					cnt++;
				}
			}
		}
		
		System.out.println(ans);
	}

	static void makeBridge(Pos start, int cnt) {
		boolean[][] check = new boolean[N][N];
		Queue<Pos> bq = new LinkedList<>();
		bq.offer(start);
		check[start.x][start.y] = true;
		
		while(!bq.isEmpty()) {
			Pos p = bq.poll();
			
			if(p.len >= ans)
				return;
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || check[nx][ny] || map[nx][ny] == cnt)
					continue;
				
				if(map[nx][ny] == 0) {
					check[nx][ny] = true;
					bq.offer(new Pos(nx, ny, p.len+1));
				}else{
					ans = Math.min(ans, p.len);
					return;
				}
			}
		}
	}
	
	static void bfs(int x, int y, int cnt) {
		q.offer(new Pos(x, y));
		visited[x][y] = true;

		while (!q.isEmpty()) {
			Pos p = q.poll();

			map[p.x][p.y] = cnt;

			boolean flag = false;
			for (int i = 0; i < 4; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])
					continue;
				
				if(map[nx][ny] == 0) {
					flag = true;
					continue;
				}

				visited[nx][ny] = true;
				q.offer(new Pos(nx ,ny));
			}
			if(flag)
				al.add(p);
		}
	}
	
	static class Pos {
		int x, y, len;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Pos(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}
}
