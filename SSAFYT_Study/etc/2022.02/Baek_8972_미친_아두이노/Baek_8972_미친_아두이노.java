package etc._2022_02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_8972_미친_아두이노 {
	static int N, M;
	static int[] X = { 1, 1, 1, 0, 0, 0, -1, -1, -1 };
	static int[] Y = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
	static boolean isEnd = false;
	static int[][] map;
	static int[][] visited;
	static Pos jongsu;
	static Queue<Pos> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = s.charAt(j);

				if (c == 'I') {
					map[i][j] = -1;
					jongsu = new Pos(i, j);
				} else if (c == 'R') {
					map[i][j] = 1;
					q.offer(new Pos(i, j));
				}else {
					map[i][j] = 0;
				}
			}
		}

		String input = br.readLine();
		int cnt = 0;

		for (int i = 0; i < input.length(); i++) {
			int dir = input.charAt(i) - '1';

			move_Jongsu(dir);
			if (isEnd) {
				cnt = i;
				break;
			}
			move_Aduino();
			if (isEnd) {
				cnt = i;
				break;
			}
		}

		if (isEnd) {
			sb.append("kraj ").append(cnt+1);
		} else {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					char c = '.';
					if(map[i][j] >= 1)
						c = 'R';
					else if(map[i][j] == -1)
						c = 'I';
						
					sb.append(c);
				}
				sb.append("\n");
			}
		}

		bw.write(sb.toString());
		bw.flush();
	}

	static void move_Jongsu(int dir) {
		map[jongsu.x][jongsu.y] = 0;

		jongsu.x = jongsu.x + X[dir];
		jongsu.y = jongsu.y + Y[dir];

		if (map[jongsu.x][jongsu.y] != 0) {
			isEnd = true;
		}
		
		map[jongsu.x][jongsu.y] = -1;
	}

	static void move_Aduino() {
		int size = q.size();

		while (size-- > 0) {
			Pos p = q.poll();

			int min = Integer.MAX_VALUE;
			int x = 0, y = 0;
			for (int i = 0; i < 9; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;

				int dis = Math.abs(jongsu.x - nx) + Math.abs(jongsu.y - ny);
				if (dis < min) {
					min = dis;
					x = nx;
					y = ny;
				}
			}
			
			if(map[x][y] == -1) {
				isEnd = true;
				return;
			}

			map[p.x][p.y]--;
			map[x][y]++;
			q.offer(new Pos(x, y));
		}
		
		size = q.size();
		while(size-- > 0) {
			Pos p = q.poll();
			
			if(map[p.x][p.y] == 1) {
				q.offer(p);
			}else if(map[p.x][p.y] > 1) {
				map[p.x][p.y]= 0; 
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
