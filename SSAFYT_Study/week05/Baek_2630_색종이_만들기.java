package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2630_색종이_만들기 {
	static int N;
	static int[][] paper;
	static int white = 0;
	static int blue = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		paper = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cut(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
	}

	static void cut(int x, int y, int size) {
		if (same(x, y, size)) {
			if (paper[x][y] == 1)
				blue++;
			else
				white++;
			return;
		}

		cut(x, y, size / 2);
		cut(x, y + (size / 2), size / 2);
		cut(x + (size / 2), y, size / 2);
		cut(x + (size / 2), y + (size / 2), size / 2);
	}

	static boolean same(int x, int y, int size) {
		int temp = paper[x][y];

		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (paper[i][j] != temp) {
					return false;
				}
			}
		}
		return true;
	}
}
