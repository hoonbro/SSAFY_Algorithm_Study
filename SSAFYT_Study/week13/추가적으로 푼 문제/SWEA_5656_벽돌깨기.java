package week13.추가;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기 {
	static int T, K, M, N, ans;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken()); // 구슬개수
			M = Integer.parseInt(st.nextToken()); // 너비
			N = Integer.parseInt(st.nextToken()); // 높이

			int total = 0; 
			int[][] map = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 0)
						total++;
				}
			}

			ans = Integer.MAX_VALUE;
			dfs(0, total, map);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static boolean dfs(int cnt, int remain, int[][] map) {
		if (remain == 0) {
			ans = 0;
			return true;
		}
		if (cnt == K) {
			ans = Math.min(ans, remain);
			return false;
		}

		int[][] newMap = new int[N][M];
		for (int c = 0; c < M; c++) {

			int r = 0;
			while (r < N && map[r][c] == 0)
				++r;
			if (r == N)
				continue;

			copyMap(map, newMap);
			int burstCnt = bomb(newMap, r, c);
			down(newMap);

			if (dfs(cnt + 1, remain - burstCnt, newMap))
				return true;
		}

		return false;
	}

	//내리기
	static void down(int[][] map) {
		for (int c = 0; c < M; c++) {
			int r = N - 1;
			while (r > 0) {
				if (map[r][c] == 0) {
					int nr = r - 1;
					while (nr > 0 && map[nr][c] == 0)
						nr--;

					map[r][c] = map[nr][c];
					map[nr][c] = 0;
				}
				r--;
			}
		}
	}

	//폭탄 터뜨리기
	static int bomb(int[][] map, int r, int c) {
		int cnt = 0;
		Queue<Pos> q = new LinkedList<>();
		if (map[r][c] > 1)
			q.add(new Pos(r, c, map[r][c]));
		map[r][c] = 0;
		cnt++;
		
		Pos pos;
		int nx, ny;
		while (!q.isEmpty()) {

			pos = q.poll();

			for (int d = 0; d < 4; d++) {
				nx = pos.x;
				ny = pos.y;
				
				for (int i = 0; i < pos.cnt - 1; i++) {
					nx += X[d];
					ny += Y[d];
					// 범위 확인
					if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 0)
						continue;
					
					if (map[nx][ny] > 1)
						q.add(new Pos(nx, ny, map[nx][ny]));

					map[nx][ny] = 0;
					cnt++;
				}
			}
		}

		return cnt;
	}

	static void copyMap(int[][] map, int[][] newMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}
	
	static class Pos {
		int x, y, cnt;

		public Pos(int r, int c, int cnt) {
			this.x = r;
			this.y = c;
			this.cnt = cnt;
		}
	}
}
