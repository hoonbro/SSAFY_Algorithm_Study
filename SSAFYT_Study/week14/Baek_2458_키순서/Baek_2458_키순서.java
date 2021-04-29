package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2458_키순서 {
	static int N, M, T, ans;
	static boolean[][] know;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		know = new boolean[N + 1][N + 1];

		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			know[a][b] = true;
		}
		floyd();
		ans = 0;
		loop: for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				if (!know[i][j] && !know[j][i])
					continue loop;
			}
			ans++;
		}
		System.out.println(ans);
	}

	static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if(i == k || !know[i][k])
					continue;
				for (int j = 1; j <= N; j++) {
					if (!know[i][j] && (know[i][k] && know[k][j])) {
						know[i][j] = true;
					}
				}
			}
		}
	}
}
