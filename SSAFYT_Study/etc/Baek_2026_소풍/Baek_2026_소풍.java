package etc._2021_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_2026_소풍 {
	static int K, N, F;
	static boolean flag = false;
	static int[] ans;
	static boolean[] check;
	static ArrayList<ArrayList<Integer>> al;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());

		ans = new int[K];
		check = new boolean[N + 1];
		al = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i <= N; i++) {
			al.add(new ArrayList<>());
		}

		for (int i = 0; i < F; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			al.get(a).add(b);
			al.get(b).add(a);
		}
		dfs(1, 0);

		if (flag) {
			for (int i = 0; i < K; i++) {
				sb.append(ans[i]).append("\n");
			}
			System.out.println(sb.toString());
		}else {
			System.out.println(-1);
		}
	}

	static void dfs(int idx, int cnt) {
		if (cnt == K || flag) {
			flag = true;
			return;
		}

		loop: for (int i = idx; i <= N; i++) {
			if (flag)
				return;

			if (check[i] || al.get(i).size() < K - 1)
				continue;

			if (cnt > 0) {
				for (int j = 0; j < cnt; j++) {
					if (!al.get(ans[j]).contains(i))
						continue loop;
				}
			}

			ans[cnt] = i;
			check[i] = true;
			dfs(i + 1, cnt + 1);
			check[i] = false;
		}
	}
}
