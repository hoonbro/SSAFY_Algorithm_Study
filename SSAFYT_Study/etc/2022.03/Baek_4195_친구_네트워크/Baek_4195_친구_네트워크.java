package etc._2022_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Baek_4195_친구_네트워크 {
	static int T, F;
	static int[] parent, friends;
	static Map<String, Integer> map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			F = Integer.parseInt(br.readLine());

			int idx = 0;

			parent = new int[F * 2];
			friends = new int[F * 2];
			map = new HashMap<>();

			for (int i = 0; i < F * 2; i++) {
				parent[i] = i;
				friends[i] = 1;
			}

			for (int i = 0; i < F; i++) {
				st = new StringTokenizer(br.readLine());

				String a = st.nextToken();
				String b = st.nextToken();

				if (!map.containsKey(a))
					map.put(a, idx++);

				if (!map.containsKey(b))
					map.put(b, idx++);

				sb.append(union(map.get(a), map.get(b))).append("\n");
			}
		}

		System.out.println(sb.toString());
	}

	static int find(int a) {
		if (a == parent[a])
			return a;

		return parent[a] = find(parent[a]);
	}

	static int union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b)
			return friends[a];

		if (parent[a] > parent[b]) {
			parent[b] = a;
			friends[a] += friends[b];
			return friends[a];
		}else {
			parent[a] = b;
			friends[b] += friends[a];
			return friends[b];
		}
	}
}
