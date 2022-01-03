package etc._2022_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Baek_2151_거울설치 {
	static int N, ans = 0;
	static Pos start;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static char[][] map;
	static int[][] visited;
	static PriorityQueue<Pos> pq = new PriorityQueue<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new int[N][N];

		for(int i = 0; i < N; i++) {
			Arrays.fill(visited[i], -1);
		}
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				map[i][j] = s.charAt(j);

				if (map[i][j] == '#') {
					start = new Pos(i, j, 0, 0);
				}
			}
		}
		map[start.x][start.y] = '*';
		
		//문에서 출발하는 방향
		for (int d = 0; d < 4; d++) {
			int nx = start.x + X[d];
			int ny = start.y + Y[d];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == '*')
				continue;
			pq.offer(new Pos(start.x, start.y, d, 0));
		}
		
		bfs();
		System.out.println(ans);
	}

	static void bfs() {
		map[start.x][start.y] = '*'; 
		pq.offer(start);
		visited[start.x][start.y]= 0; 
		while (!pq.isEmpty()) {
			Pos now = pq.poll();
			
			if(map[now.x][now.y] =='#') {
				ans = now.cnt;
				return;
			}
			
			
			int nx = now.x + X[now.dir];
			int ny = now.y + Y[now.dir];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == '*' || visited[nx][ny] >= now.cnt)
				continue;
			
			visited[now.x][now.y] = now.cnt;
			
			//거울을 놓을 수 있는 경우
			if(map[nx][ny] == '!') {
				pq.offer(new Pos(nx, ny, (now.dir+1)%4, now.cnt+1));
				pq.offer(new Pos(nx, ny, (now.dir+3)%4, now.cnt+1));
			}
			pq.offer(new Pos(nx, ny, now.dir, now.cnt));
		}
	}

	static class Pos implements Comparable<Pos> {
		int x, y, dir, cnt;

		public Pos(int x, int y, int dir, int cnt) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Pos o) {
			return this.cnt - o.cnt;
		}
	}
}
