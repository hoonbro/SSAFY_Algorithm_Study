# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [17140] 이차원 배열과 연산
> https://www.acmicpc.net/problem/17140
## 알고리즘 분류
> 구현, 시뮬레이션

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_17140_이차원_배열과_연산 {
	static int N, M, R, C, K;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = 3;
		M = 3;
		R = Integer.parseInt(st.nextToken()) - 1;
		C = Integer.parseInt(st.nextToken()) - 1;
		K = Integer.parseInt(st.nextToken());

		arr = new int[100][100];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int time = 0;

		while (time <= 100 && arr[R][C] != K) {
			time++;

			if (N >= M) {
				R();

			} else {
				C();
			}
		}
		System.out.println(time > 100 ? -1 : time);
	}

	static void R() {
		int len = M;
		for (int i = 0; i < N; i++) {
			int[] cnt = new int[101];

			int total = 0;
			for (int j = 0; j < len; j++) {
				total++;
			}

			Node[] nodes = new Node[total];
			for (int j = 1; j <= 100 && total > 0; j++) {
				if (cnt[j] == 0)
					continue;

				nodes[--total] = new Node(j, cnt[j]);
			}
			Arrays.sort(nodes);

			int idx = 0;
			for (Node n : nodes) {
				if (idx >= 100)
					break;
				arr[i][idx++] = n.num;
				arr[i][idx++] = n.cnt;
			}

			for (int j = idx; j <= len; j++) {
				arr[i][j] = 0;
			}
			M = Math.max(M, idx);
		}
	}

	static void C() {
		int len = N;
		for (int i = 0; i < M; i++) {
			int[] cnt = new int[101];

			int total = 0;
			for (int j = 0; j < len; j++) {
				if (arr[j][i] != 0 && cnt[arr[j][i]]++ == 0)
					total++;
			}

			Node[] nodes = new Node[total];
			for (int j = 1; j <= 100 && total > 0; j++) {
				if (cnt[j] == 0)
					continue;

				nodes[--total] = new Node(j, cnt[j]);
			}
			Arrays.sort(nodes);

			int idx = 0;
			for (Node n : nodes) {
				if (idx >= 100)
					break;
				arr[idx++][i] = n.num;
				arr[idx++][i] = n.cnt;
			}

			for (int j = idx; j <= len; j++) {
				arr[j][i] = 0;
			}
			N = Math.max(N, idx);
		}
	}

	static class Node implements Comparable<Node> {
		int num, cnt;

		public Node(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			if (this.cnt == o.cnt) {
				return this.num - o.num;
			}
			return this.cnt - o.cnt;
		}
	}
}
```

## 문제 풀이
1. 삼성 코딩테스트 기출문제로 주어진 조건을 만족하는 시뮬레이션을 코딩하면 해결
