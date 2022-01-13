package etc._2021_12;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Baek_4803_트리 {
	static int N, M;
	static int[] parent;
	static Set<Integer> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = 1;
		while (true) {
			set = new HashSet<>();
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0)
				break;

			parent = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parent[i] = i;
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}

			for (int i = 1; i <= N; i++) {
				int root = find(i);
				if (root != 0) {
					set.add(root);
				}
			}

			sb.append("Case ").append(T++);
			if (set.isEmpty()) {
				sb.append(": No trees.").append("\n");
			} else if (set.size() == 1) {
				sb.append(": There is one tree.").append("\n");
			} else {
				sb.append(": A forest of ").append(set.size()).append(" trees.").append("\n");
			}
		}

		bw.write(sb.toString());
		bw.flush();
	}

	static int find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) {
			parent[b] = a;
			parent[a] = 0;
		} else if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}
}
