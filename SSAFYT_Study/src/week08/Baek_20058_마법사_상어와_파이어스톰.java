package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_20058_마법사_상어와_파이어스톰 {
	static int N, Q, Size = 0;
	static int total = 0, maxMass = 0;
	static int[][] map;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		N = (int) Math.pow(2, N);
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int k = 0; k < Q; k++) {
			Size = Integer.parseInt(st.nextToken());
			Size = (int) Math.pow(2, Size);
			split(0, 0, N);
			ice();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 0) {
					maxMass = Math.max(mass(i, j), maxMass);
					
				}
			}
		}

		
		System.out.println(total);
		System.out.println(maxMass);
	}

	// 4분할
	static void split(int start, int end, int size) {
		if (Size == size) {
			fireStorm(start, end, size);

			return;
		}

		split(start, end, size / 2);
		split(start, end + (size / 2), size / 2);
		split(start + (size / 2), end, size / 2);
		split(start + (size / 2), end + (size / 2), size / 2);
	}

	// 배열 돌리기
	static void fireStorm(int start, int end, int size) {
		int[][] temp = new int[size][size];

		int k = size - 1;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				temp[j][k] = map[i + start][j + end];
			}
			k--;
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				map[i + start][j + end] = temp[i][j];
			}
		}
	}

	// 총 얼음
	static void ice() {
		int cnt = 0;
		Queue<Pos> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cnt = 0;
				for (int k = 0; k < 4; k++) {
					int nx = i + X[k];
					int ny = j + Y[k];

					if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] < 1)
						continue;
					cnt++;
				}
				if (cnt < 3) {
					q.offer(new Pos(i, j));
				}
			}
		}

		while (!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.poll().y;
			if (map[x][y] > 0) {
				map[x][y]--;
				total--;
			}

		}
	}

	// 최대 덩어리
	static int mass(int x, int y) {
		int cnt = 1;
		map[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + X[i];
			int ny = y + Y[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 0)
				continue;

			cnt += mass(nx, ny);
		}
		return cnt;
	}

	static class Pos {
		int x, y;

		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
