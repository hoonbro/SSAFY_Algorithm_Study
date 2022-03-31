package etc._2022_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Baek_17398_통신망_분할 {
	static int N, M, Q;
	static int[] arr, parent;
	static boolean[] isCut;
	static int[][] edge;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		arr = new int[Q];
		edge = new int[N][2];
		isCut = new boolean[N + 1];

		Arrays.fill(parent, -1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			edge[i][0] = Integer.parseInt(st.nextToken());
			edge[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < Q; i++) {
			arr[i] = Integer.parseInt(br.readLine()) -1;
			isCut[arr[i]] = true;
		}

		for (int i = 0; i < M; i++) {
			if (isCut[i])
				continue;

			union(edge[i][0], edge[i][1]);
		}

		long ans = 0;
		for (int i = Q - 1; i >= 0; i--) {
			int a = find(edge[arr[i]][0]);
			int b = find(edge[arr[i]][1]);

			if (a != b) {
				System.out.println(a + " " + b);
				System.out.println(parent[a] + " " + parent[b]);
				ans += parent[a] * parent[b];
			}
			
			union(a, b);
		}
		
		System.out.println(ans);
	}

	static int find(int a) {
		if (parent[a] < 0)
			return a;

		return parent[a] = find(parent[a]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b)
			return;

		parent[a] += parent[b];
		parent[b] = a;
	}
}
