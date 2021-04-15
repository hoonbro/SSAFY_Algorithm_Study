package week13.추가;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baek_14890_경사로 {
	static int N, X, ans;
	static int[][] map, map2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		map2 = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				map2[j][i] = map[i][j];
			}
		}
		ans = 0;
		check(map);
		check(map2);
		System.out.println(ans);
	}

	static void check(int[][] map) {
		boolean flag;
		boolean[] build;
		for (int i = 0; i < N; i++) {
			flag = true;
			build = new boolean[N];
			loop: for (int j = 0; j < N - 1; j++) {
				if (map[i][j] != map[i][j + 1]) {
					if (map[i][j] == map[i][j + 1] + 1) { // 내려감
						for (int k = j + 1; k < j + 1 + X; k++) {
							if (k >= N || map[i][j + 1] != map[i][k] || build[k]) {
								flag = false;
								break loop;
							} else
								build[k] = true;
						}
					} else if (map[i][j] == map[i][j + 1] - 1) {// 올라감
						for (int k = j; k >= j + 1 - X; k--) {
							if (k < 0 || map[i][j] != map[i][k] || build[k]) {
								flag = false;
								break loop;
							} else
								build[k] = true;
						}
					} else {
						flag = false;
						break loop;
					}
				}
			}
			if (flag) {
				ans++;
			}
		}
	}
}
