package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_4179_불 {
	static int N, M, ans;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static char[][] map;
	static boolean[][] visited;
	static Queue<Pos> jq = new LinkedList<>();
	static Queue<Pos> fq = new LinkedList<>();

	static void bfs() {
		Pos pos;
		int nx, ny;
		int fSize, jSize;

		int time = 1;
		while (true) {
			fSize = fq.size();
			jSize = jq.size();

			if(jSize == 0)
				break;
			
			while (fSize-- > 0) {
				pos = fq.poll();

				for (int d = 0; d < 4; d++) {
					nx = pos.x + X[d];
					ny = pos.y + Y[d];

					if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == '#' || map[nx][ny] == 'F') {
						continue;
					}
					map[nx][ny] = 'F';
					fq.offer(new Pos(nx, ny));
				}
			}

			while (jSize-- > 0) {
				pos = jq.poll();
				
				for(int d = 0; d < 4; d++) {
					nx = pos.x + X[d];
					ny = pos.y + Y[d];
					
					if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
						System.out.println(time);
						return;
					}
					if(map[nx][ny] == '#' || map[nx][ny] == 'F' || visited[nx][ny])
						continue;
					
					visited[nx][ny] = true;
					map[nx][ny] = 'J';
					jq.offer(new Pos(nx, ny));
				}
			}
			time++;
		}
		System.out.println("IMPOSSIBLE");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'J') {
					jq.offer(new Pos(i, j));
					visited[i][j] = true;
				} else if (map[i][j] == 'F') {
					fq.offer(new Pos(i, j));
				}
			}
		}
		bfs();
	}

	static class Pos {
		int x, y, time;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
