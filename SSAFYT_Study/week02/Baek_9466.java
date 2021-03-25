package week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//텀 프로젝트
public class Baek_9466 {
	static int[] arr;
	static boolean[] visited, isCycle;
	static int n, answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());
			answer = n;
			arr = new int[n + 1];
			visited = new boolean[n + 1];
			isCycle = new boolean[n + 1];

			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 1; i <= n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= n; i++) {
				if (!isCycle[i])
					dfs(i);
			}

			System.out.println(answer);
		}
	}

	static void dfs(int now) {
		if (visited[now]) {
			isCycle[now] = true;
			answer--;
		}
		else
			visited[now] = true;

		if (!isCycle[arr[now]])
			dfs(arr[now]);

		visited[now] = false;
		isCycle[now] = true;
	}
}
