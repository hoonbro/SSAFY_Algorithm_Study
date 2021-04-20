package week13.추가;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨뜨리기 {
	static int N, M, K, T, nx, ny, ans;
	static int[][] map, temp;
	static boolean[][] visited;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static Queue<Pos> q = new LinkedList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken()); // 구슬갯수
			M = Integer.parseInt(st.nextToken()); // 넓이
			N = Integer.parseInt(st.nextToken()); // 높이
			map = new int[N][M];
			Pos[] arr = new Pos[M];
			ans = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if(map[i][j] != 0 && arr[j] == null) {
						arr[j] = new Pos(i, j, map[i][j]);
					}
				}
			}
			
			choice(arr, 0);
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void choice(Pos[] arr, int cnt) {
		if(cnt == N) {
			int sum = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] != 0)
						sum+= map[i][j];
				}
			}
			ans = Math.max(ans, sum);
			return;
		}
		for(int i = 0; i < M; i++) {
			if(arr[i].size == 0)
				continue;
			
			boom(arr[i].x, arr[i].y);
			down();
			choice(findTop(), cnt+1);
		}
	}
	
	static Pos[] findTop() {
		Pos[] p = new Pos[N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 0 && p[j] == null)
					p[j] = new Pos(i,j,map[i][j]);
			}
		}
		return p;
	}
	
	static void down() {
		int[] arr;
		int idx;
		for(int i = 0; i < M; i++) {
			arr = new int[N];
			idx= N-1;
			for(int j = N-1; j >= 0; j--) {
				if(temp[j][i] > 0) {
					arr[idx--] = temp[j][i];
				}
			}
			
			for(int j = N-1; j >= 0; j--) {
				temp[j][i] = arr[j];
			}
		}
	}
	
	static void boom(int x, int y) {
		q.offer(new Pos(x, y, temp[x][y]));
		copyMap();
		visited = new boolean[N][M];
		temp[x][y] = 0;
		visited[x][y] = true;
		Pos pos;
		while (!q.isEmpty()) {
			pos = q.poll();
			for (int d = 0; d < 4; d++) {
				for (int i = 1; i < pos.size; i++) {
					nx = pos.x + X[d] * i;
					ny = pos.y + Y[d] * i;

					// 바깥범위면 그만
					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						break;
					if (visited[nx][ny])
						continue;
					
					// 1이 아닌 칸 만날경우
					if (temp[nx][ny] > 1) {
						q.offer(new Pos(nx, ny, temp[nx][ny]));
					}
					temp[nx][ny] = 0;
					visited[nx][ny] = true;
				}
			}
		}
	}
	
	static void copyMap() {
		temp = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}
	}
	
	static class Pos {
		int x, y, size;

		public Pos(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}
}
