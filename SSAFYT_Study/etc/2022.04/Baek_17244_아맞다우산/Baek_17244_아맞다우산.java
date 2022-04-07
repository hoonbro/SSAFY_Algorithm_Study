package etc._2022_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_17244_아맞다우산 {
	static int N, M, total, ans = 0;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static char[][] map;
	static boolean[][][] visited;
	static Queue<Pos> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M][1 << 6];

		total = 0;
		char c = '0';
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'S') {
					q.offer(new Pos(i, j, 0, 0));
					visited[i][j][0] = true;
				} else if (map[i][j] == 'X') {
					map[i][j] = c++;
					total++;
				}
			}
		}

		bfs();
		System.out.println(ans);
	}

	static void bfs() {
		while (!q.isEmpty()) {
			Pos p = q.poll();
			
			if (map[p.x][p.y] == 'E' && p.stuff == (1 << total) - 1) {
				ans = p.time;
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];
				int newStuff = p.stuff;
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny][p.stuff] || map[nx][ny] == '#')
					continue;

				if ('0' <= map[nx][ny] && map[nx][ny] <= '5') {
					int num = map[nx][ny] - '0';

					if ((p.stuff & (1 << num)) == 0) 
						newStuff = (p.stuff | (1 << num));
				}

				visited[nx][ny][newStuff] = true;
				q.add(new Pos(nx, ny, newStuff, p.time + 1));
			}
		}
	}

	static class Pos {
		int x, y, stuff, time;

		public Pos(int x, int y, int stuff, int time) {
			this.x = x;
			this.y = y;
			this.stuff = stuff;
			this.time = time;
		}
	}
}
