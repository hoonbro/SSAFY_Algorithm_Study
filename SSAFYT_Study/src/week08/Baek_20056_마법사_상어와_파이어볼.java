package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek_20056_마법사_상어와_파이어볼 {
	static int[] X = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] Y = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static LinkedList<FireBall>[][] map, temp;
	static int N, M, K, total = 0;

//	static class Pos {
//		int x, y, m;
//
//		Pos(int x, int y, int m) {
//			this.x = x;
//			this.y = y;
//			this.m = m;
//		}
//
//		@Override
//		public int hashCode() {
//			return Objects.hash(x);
//		}
//
//		@Override
//		public boolean equals(Object o) {
//			Pos p = (Pos) o;
//			if (p.x == this.x && p.y == this.y)
//				return true;
//			return false;
//		}
//	}

	static class FireBall {
		int m, s, d;

		FireBall(int m, int s, int d) {
			this.m = m;
			this.d = d;
			this.s = s;
		}
	}

	static void magic() {
		// 모든 파이어볼 이동

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				while (map[i][j].size() != 0) {
					FireBall fb = map[i][j].get(0);
					int nx = i + X[fb.d] * fb.s % N;
					int ny = j + Y[fb.d] * fb.s % N;

					if (nx > N)
						nx -= N;
					else if (nx < 1)
						nx += N;

					if (ny > N)
						ny -= N;
					else if (ny < 1)
						ny += N;

					temp[nx][ny].add(map[i][j].removeFirst());
				}
			}
		}
		
		// 파이어볼 나누기
		divide();
	}

	static void divide() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (temp[i][j].size() == 1) {
					map[i][j].add(temp[i][j].removeFirst());
					continue;
				}
				if (temp[i][j].size() < 2)
					continue;

				int mSum = 0;
				int sSum = 0;
				boolean odd = true; // 홀
				boolean even = true; // 짝
				for (int k = 0; k < temp[i][j].size(); k++) {
					mSum += temp[i][j].get(k).m;
					sSum += temp[i][j].get(k).s;
					if (temp[i][j].get(k).d % 2 == 0)
						odd = false;
					else
						even = false;
				}

				total -= mSum;
				mSum /= 5;
				sSum /= temp[i][j].size();
				temp[i][j].clear();
				
				if (mSum <= 0) 
					continue;

				total += mSum * 4;
				for (int k = 0; k < 4; k++) {
					if (odd || even) {
						map[i][j].add(new FireBall(mSum, sSum, 2 * k));
					} else
						map[i][j].add(new FireBall(mSum, sSum, 1 + 2 * k));
				}
				
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 배열 크기
		M = Integer.parseInt(st.nextToken()); // 파이어볼 수
		K = Integer.parseInt(st.nextToken()); // 이동 명령
		map = new LinkedList[N + 1][N + 1];
		temp = new LinkedList[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = new LinkedList<>();
				temp[i][j] = new LinkedList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new FireBall(m, s, d));
			total += m;
		}
		for (int i = 0; i < K; i++) {
			magic();
		}

		System.out.println(total);
	}
}
