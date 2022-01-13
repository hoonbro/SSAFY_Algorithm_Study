package etc._2022_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_18809_Gaaaaaaaaaarden {
	static int N, M, G, R, len, ans = 0;
	static int[] arrG, arrR;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static int[][] map;
	static Pos[][] visited;
	static ArrayList<Pos> al = new ArrayList<>();
	static Queue<Pos> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		arrG = new int[G];
		arrR = new int[R];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					al.add(new Pos(i, j, 0));
				}
			}
		}
		len = al.size();

		dfs(0, 0, 0);

		System.out.println(ans);
	}

	static void bfs() {
		visited = new Pos[N][M];

		for (int i = 0; i < G; i++) {
			Pos p = al.get(arrG[i]);
			q.offer(p);
			visited[p.x][p.y] = new Pos(p.cnt, 'G');
		}

		for (int i = 0; i < R; i++) {
			Pos p = al.get(arrR[i]);
			q.offer(p);
			visited[p.x][p.y] = new Pos(p.cnt, 'R');
		}

		int flower = 0;
		while (!q.isEmpty()) {
			Pos now = q.poll();
			int cnt = visited[now.x][now.y].cnt;
			char color = visited[now.x][now.y].color;
			
			if (visited[now.x][now.y].color == 'F')
				continue;

			for (int d = 0; d < 4; d++) {
				int nx = now.x + X[d];
				int ny = now.y + Y[d];

				if (!check(nx, ny))
					continue;

				if (visited[nx][ny] == null) {
					visited[nx][ny] = new Pos(now.cnt + 1, color);
					q.offer(new Pos(nx, ny, now.cnt + 1));
				} 
				else if(visited[nx][ny].color == 'G') {
					if(color == 'G' || visited[nx][ny].cnt != cnt+1)
						continue;
					
					flower++;
					visited[nx][ny].color = 'F';
				}
				else if(visited[nx][ny].color == 'R') {
					if(color == 'R' || visited[nx][ny].cnt != cnt +1)
						continue;
					
					flower++;
					visited[nx][ny].color = 'F';
				}
			}
		}
		ans = Math.max(ans, flower);
	}

	static boolean check(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 0)
			return false;

		return true;
	}

	static void dfs(int idx, int g, int r) {
		if (g == G && r == R) {
			bfs();
			return;
		}

		if (g < G) {
			for (int i = idx; i < len; i++) {
				arrG[g] = i;
				dfs(i + 1, g + 1, r);
			}
		}

		if (r < R) {
			for (int i = idx; i < len; i++) {
				arrR[r] = i;
				dfs(i + 1, g, r + 1);
			}
		}
	}

	static class Pos {
		int x, y, cnt;
		char color;

		public Pos(int cnt, char color) {
			this.cnt = cnt;
			this.color = color;
		}

		public Pos(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}
