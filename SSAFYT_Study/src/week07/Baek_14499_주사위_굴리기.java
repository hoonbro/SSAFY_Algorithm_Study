package week07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_14499_주사위_굴리기 {
	static int N, M, x, y, K;
	static int[][] map;
	static int[] X = { 0, 0, 0, -1, 1 };
	static int[] Y = { 0, 1, -1, 0, 0 };
	static Queue<Integer> q = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();

	static class Dice {
		int n1, n2, n3, n4, n5, n6;

		public void up() {
			int temp = n6;
			n6 = n5;
			n5 = n1;
			n1 = n2;
			n2 = temp;
		}

		public void down() {
			int temp = n2;
			n2 = n1;
			n1 = n5;
			n5 = n6;
			n6 = temp;
		}

		public void right() {
			int temp = n3;
			n3 = n1;
			n1 = n4;
			n4 = n6;
			n6 = temp;
		}

		public void left() {
			int temp = n6;
			n6 = n4;
			n4 = n1;
			n1 = n3;
			n3 = temp;
		}
	}

	static void move() {
		Dice dice = new Dice();

		while (!q.isEmpty()) {
			int command = q.poll();
			int nx = x + X[command];
			int ny = y + Y[command];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;

			x = nx;
			y = ny;

			// 오른쪽 왼쪽 위 아래
			switch (command) {
			case 1:
				dice.right();
				break;
			case 2:
				dice.left();
				break;
			case 3:
				dice.up();
				break;
			case 4:
				dice.down();
				break;
			}

			if (map[nx][ny] == 0) {// 이동하는 칸이 0이면
				map[nx][ny] = dice.n1; // 주사위 바닥면이 칸에 복사됨
			} else { //0이 아니면
				dice.n1 = map[nx][ny]; //바닥면에 이동하는 칸의 수 복사
				map[nx][ny] = 0; //칸을 0으로
			}
			sb.append(dice.n6).append("\n");
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 지도 크기 row
		M = Integer.parseInt(st.nextToken()); // 지도 크기 col
		x = Integer.parseInt(st.nextToken()); // 주사위 좌표 row
		y = Integer.parseInt(st.nextToken()); // 주사위 좌표 col
		K = Integer.parseInt(st.nextToken()); // 명령어 갯수

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			q.offer(Integer.parseInt(st.nextToken()));
		}

		move();
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
