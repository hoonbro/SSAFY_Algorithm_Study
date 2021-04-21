package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_16562_친구비 {
	static int N, M, K, ans;
	static int[] parent, cost;
	static boolean[] isFriend;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		cost = new int[N + 1];
		isFriend = new boolean[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
			if (i == 0)
				continue;
			cost[i] = Integer.parseInt(st.nextToken());
		}

		int from, to;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			union(from, to);
		}

		for (int i = 1; i <= N; i++) {
			if(ans > K) {
				System.out.println("Oh no");
				return;
			}
			int temp = find(i);

			if (temp == 0)
				continue;

			ans += cost[temp];
			union(0, temp);
		}

		System.out.println(ans);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (cost[a] <= cost[b])
			parent[b] = a;
		else
			parent[a] = b;
	}

	static int find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}
}
