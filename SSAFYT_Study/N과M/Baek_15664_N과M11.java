package N과M;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Baek_15664_N과M11 {
	static int N, M;
	static int[] arr;
	static int[] result;
	static StringBuilder sb = new StringBuilder();
	static Set<String> set = new HashSet<String>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		result = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		dfs(0);
		
		bw.write(sb.toString());
		bw.flush();
	}

	static void dfs(int cnt) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(result[i]).append(" ");
			}
			
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			if(arr[i] == arr[i-1])
				continue;
			result[cnt] = arr[i];
			dfs(cnt + 1);
		}
	}
}
