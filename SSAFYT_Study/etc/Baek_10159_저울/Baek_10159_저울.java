package etc._2022_01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_10159_저울 {
	static int N, M, INF = 987654321;
	static int[] cnt;
	static int[][] stuff;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		stuff = new int[N + 1][N + 1];
		cnt = new int[N + 1];
		Arrays.fill(cnt, N - 1);

		for (int i = 1; i <= N; i++) {
			Arrays.fill(stuff[i], INF);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			stuff[a][b] = 1;
		}

		floyd();

		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				if(stuff[i][j] == INF && stuff[j][i] == INF)
					cnt++;
			}
			sb.append(cnt).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
	}

	static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (stuff[i][j] > stuff[i][k] + stuff[k][j]) {
						stuff[i][j] = stuff[i][k] + stuff[k][j];
					}
				}
			}
		}
	}
}
