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

public class Baek_15664_N과M12 {
	static int N, M, max = 0;
	static int[] arr;
	static int[] result;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	static Set<String> set = new HashSet<String>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		result = new int[M];
		visited = new boolean[10001];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(st.nextToken());
			if(visited[input])
				continue;
			
			arr[max] = input;
			visited[input] = true;
			max++;
		}
		Arrays.sort(arr);

		
		dfs(N-max, 0);

		bw.write(sb.toString());
		bw.flush();
	}

	static void dfs(int idx, int cnt) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(result[i]).append(" ");
			}

			sb.append("\n");
			return;
		}

		for (int i = idx; i < N; i++) {
			result[cnt] = arr[i];
			dfs(i, cnt + 1);
		}
	}
}
