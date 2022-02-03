package etc._2022_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_1938_통나무_옮기기 {
	static int N, ans = 0;
	static Pos log, destination;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static char[][] map;
	static boolean[][][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N][2];

		int c1 = 0, c2 = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);

				if (map[i][j] == 'B') {
					
					if (i != 0 && c1 == 1 && map[i - 1][j] == 'B') {
						log = new Pos(i, j, 0, 1);
					} else if(j != 0 && c1 == 1 && map[i][j-1] == 'B') {
						log = new Pos(i, j, 0, 0);
					}

					c1++;

				} else if (map[i][j] == 'E') {
					if (i != 0 && c2 == 1 && map[i - 1][j] == 'E') {
						destination = new Pos(i, j, 0, 1);
					}else if(j != 0 && c2 == 1 && map[i][j-1] == 'E') {
						destination = new Pos(i, j, 0, 0);
					}
					c2++;
				}

			}
		}
		
		bfs();
		System.out.println(ans);
	}

	static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.offer(log);
		visited[log.x][log.y][log.dir] = true;

		while (!q.isEmpty()) {
			Pos p = q.poll();
			int rotateDir = p.dir == 0 ? 1 : 0;
			
			if(p.x == destination.x && p.y == destination.y && p.dir == destination.dir) {
				ans = p.cnt;
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];
				
				if (!check(nx, ny, p.dir)) {
					if (!check(p.x, p.y, rotateDir))
						continue;
					
					if(!rotate(p.x, p.y, rotateDir))
						continue;

					visited[p.x][p.y][rotateDir] = true;
					q.offer(new Pos(p.x, p.y, p.cnt+1, rotateDir));
				}
				else {
					visited[nx][ny][p.dir] = true;
					q.offer(new Pos(nx, ny, p.cnt+1, p.dir));
				}
			}
		}
	}
	
	static boolean rotate(int x, int y, int dir) {
		//가로
		if(dir == 0) {
			for(int i = x-1; i <= x+1; i+=2) {
				for(int j = y-1; j <= y+1; j++) {
					if(map[i][j] == '1')
						return false;
				}
			}
		}
		else {
			for(int j = y-1; j <= y+1; j+=2) {
				for(int i = x-1; i <= x+1; i++) {
					if(map[i][j] == '1')
						return false;
				}
			}
		}
		
		return true;
	}

	static boolean check(int x, int y, int dir) {
		// 가로
		if (dir == 0) {
			if (x < 0 || x >= N || y - 1 < 0 || y + 1 >= N || visited[x][y][dir])
				return false;
			
			if(map[x][y] == '1' || map[x][y-1] == '1' || map[x][y+1] == '1')
				return false;
		}
		// 세로
		else {
			if (x - 1 < 0 || x + 1 >= N || y < 0 || y >= N || visited[x][y][dir])
				return false;
			if(map[x][y] == '1' ||map[x-1][y] == '1' || map[x+1][y] == '1')
				return false;
		}
		return true;
	}

	static class Pos {
		// dir : 가로 0, 세로 1
		int x, y, cnt, dir;

		public Pos(int x, int y, int cnt, int dir) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}
	}
}
