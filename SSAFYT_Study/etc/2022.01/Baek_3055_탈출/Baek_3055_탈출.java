package etc._2022_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_3055_탈출 {
	static int N, M, ans = 0, cnt = 0;
	static Pos beaver;
	static Queue<Pos> moveQ = new LinkedList<>();
	static Queue<Pos> waterQ = new LinkedList<>();
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static char[][] map;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);

				if (map[i][j] == 'S') {
					moveQ.offer(new Pos(i, j));
					visited[i][j][0] = true;
				}
				else if(map[i][j] == 'D') {
					beaver = new Pos(i, j);
				}
				else if (map[i][j] == '*') {
					waterQ.offer(new Pos(i, j));
					visited[i][j][1] = true;
				}
			}
		}
		
		while (true) {
			int qSize = moveQ.size();
			int waterSize = waterQ.size();
			
			if(qSize == 0)
				break;
			
			water(waterSize);
			move(qSize);
			cnt++;
		}

		System.out.println(ans == 0 ? "KAKTUS" : ans);
	}

	static void water(int size) {
		while(size-- > 0) {
			Pos now = waterQ.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = now.x + X[i];
				int ny = now.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny][1] || map[nx][ny] != '.')
					continue;
				
				map[nx][ny] = '*';
				visited[nx][ny][1] = true;
				waterQ.offer(new Pos(nx, ny));
			}
		}
	}
	
	static void move(int size) {
		while (size-- > 0) {
			Pos now = moveQ.poll();
			
			if(now.x == beaver.x && now.y == beaver.y) {
				ans = cnt;	
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now.x + X[i];
				int ny = now.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny][0] || map[nx][ny] == '*' || map[nx][ny] == 'X')
					continue;
				
				map[nx][ny] = 'S';
				visited[nx][ny][0] = true;
				moveQ.offer(new Pos(nx, ny));
			}
		}
	}
	
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
