package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_2667_단지번호붙이기 {
	static int[][] map;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static int N;
	static Queue<Pos> q;
	static ArrayList<Integer> al;

	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		q = new LinkedList<>();
		al = new ArrayList<>();
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					q.offer(new Pos(i, j));
					bfs();
					cnt++;
				}
			}
		}
		System.out.println(cnt);
		Collections.sort(al);

		for (int i : al) {
			System.out.println(i);
		}
	}

	static void bfs() {
		int cnt = 1;
		Pos pos;
		map[q.peek().x][q.peek().y] = 2;
		
		while (!q.isEmpty()) {
			pos = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = pos.x + X[i];
				int ny = pos.y + Y[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (map[nx][ny] == 1) {
					cnt++;
					map[nx][ny] = 2;
					q.offer(new Pos(nx, ny));
				}
			}
		}
		al.add(cnt);
	}
}
