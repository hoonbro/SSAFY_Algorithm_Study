# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [4803] 트리
> https://www.acmicpc.net/problem/4803
## 알고리즘 분류
> MST

## 코드
```java
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
		if (a == b) { // 사이클 발생
			parent[b] = a;
			parent[a] = 0;
		} else if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}
}
```

## 문제 풀이
1. Union-find 알고리즘을 사용해 문제를 해결할 수 있다.
2. 입력을 받을 때마다 union 함수를 적용시켜준다.
   1. find(a)==find(b)일 때, 사이클이 발생한 것이기 때문에 root인 a를 0으로 만들어줘 사이클이 발생했다는 것을 표시한다.
   2. find(a) 와 find(b) 중 작은 것을 사용해 parent[b] = a 혹은 parent[a] = b를 해줘 사이클이 발생했음을 표시한다.
3. 사이클(0)이 아닌 트리의 갯수를 세고 출력한다.
