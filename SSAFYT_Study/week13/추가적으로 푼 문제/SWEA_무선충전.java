package week13.추가;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_무선충전 {
	static int N, M, T, ans;
	static int[] X = { 0, -1, 0, 1, 0 }; // 상우하좌
	static int[] Y = { 0, 0, 1, 0, -1 };
	static int[] moveA, moveB;
	static int[][][] map;

	static void battery(int x, int y, int len, int power, int cnt) {
		for (int i = x - len; i <= x + len; i++) {
			for (int j = y - len; j <= y + len; j++) {
				if (i < 0 || j < 0 || i > 10 || j > 10)
					continue;

				if (len >= Math.abs(i - x) + Math.abs(j - y))
					map[i][j][cnt] = power;
			}
		}
	}

//	static void battery(int x, int y, int len, int power, int cnt) {
//		for(int i = 0; i <= len; i++) {
//			for(int j = y - len+i; j <= y+len-i; j++) {
//				if(j < 0 || j > 10)
//					continue;
//				
//				if(x-i >= 0)
//					map[x-i][j][cnt] = power;
//				if(x+i <= 10)
//					map[x+i][j][cnt] = power;
//			}
//		}
//	}

	static void move() {
		int ax = 1, ay = 1, bx = 10, by = 10;
		for (int d = 0; d <= N; d++) {
			ax += X[moveA[d]];
			ay += Y[moveA[d]];
			bx += X[moveB[d]];
			by += Y[moveB[d]];
			
			int max = 0;
			for (int i = 1; i <= M; i++) {
				for (int j = 1; j <= M; j++) {
					// 같은 배터리를 쓰고있을때
					if (i == j )
						max = Math.max(max, Math.max(map[ax][ay][i], map[bx][by][j]));
					// 아닐때
					else
						max = Math.max(max, map[ax][ay][i] + map[bx][by][j]);
				}
			}

			ans += max;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 이동시간
			M = Integer.parseInt(st.nextToken()); // bc갯수
			ans = 0;

			map = new int[11][11][M + 1];
			moveA = new int[N + 1];
			moveB = new int[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}

			int x, y, len, power, cnt = 1;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				y = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				len = Integer.parseInt(st.nextToken());
				power = Integer.parseInt(st.nextToken());

				battery(x, y, len, power, cnt++);
			}
			move();
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}