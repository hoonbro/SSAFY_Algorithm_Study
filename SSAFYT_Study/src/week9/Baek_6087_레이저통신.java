package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_6087_레이저통신 {
	static int N, M, min;
	static char[][] map;
	static int[][] visited;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static Pos[] laser;
	static PriorityQueue<Pos> q = new PriorityQueue<>();

	static class Pos implements Comparable<Pos>{
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

	static void bfs() {
		Pos p;
		Pos l1 = laser[0];
		Pos l2 = laser[1];
		int nx, ny;
		q.offer(l1);
		visited[l1.x][l1.y] = 0;

		while (!q.isEmpty()) {
			p = q.poll();

			if (p.x == l2.x && p.y == l2.y) {
				min = p.cnt;
				return;
			}

			for (int d = 0; d < 4; d++) {
				nx = p.x + X[d];
				ny = p.y + Y[d];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == '*')
					continue;

				if (p.dir == -1 || p.dir == d) { 
					if (visited[nx][ny] >= p.cnt) {
						visited[nx][ny] = p.cnt;
						q.add(new Pos(nx, ny, d, p.cnt));
					}
				} else { 
					if (visited[nx][ny] >= p.cnt + 1) {
						visited[nx][ny] = p.cnt + 1;
						q.add(new Pos(nx, ny, d, p.cnt + 1));
					}
				}
			}
		}
		
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new int[N][M];
		laser = new Pos[2];
		min = Integer.MAX_VALUE;

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				visited[i][j] = Integer.MAX_VALUE;
				map[i][j] = s.charAt(j);
				if (q.isEmpty() && map[i][j] == 'C') {
					laser[cnt++] = new Pos(i, j, -1, 0);
				}
			}
		}
		bfs();
		System.out.println(min);
	}
}
