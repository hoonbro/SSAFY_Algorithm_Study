package etc._2022_04;

import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Baek_1507_궁금한_민호 {
	static int N, ans;
	static int[][] map, arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		arr = new int[N][N];
		ans = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				arr[i][j] = map[i][j];
				ans += map[i][j];
			}
		}

		if (floyd()) {
			ans /= 2;
		} else {
			ans = -1;
		}

		System.out.println(ans);
	}

	static boolean floyd() {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (k == i || k == j || i == j)
						continue;

					if (map[i][j] == map[i][k] + map[k][j]) {
						ans -= arr[i][j];
						arr[i][j] = 0;
					}

					if (map[i][j] > map[i][k] + map[k][j]) {
						return false;
					}
				}
			}
		}

		return true;
	}
}