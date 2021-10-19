package N과M;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_15651_N과M3 {
	static int N, M;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		permutation(1, 0);
		System.out.println(sb.toString());
	}

	static void permutation(int idx, int cnt) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			arr[cnt] = i;
			permutation(i + 1, cnt + 1);
		}
	}
}