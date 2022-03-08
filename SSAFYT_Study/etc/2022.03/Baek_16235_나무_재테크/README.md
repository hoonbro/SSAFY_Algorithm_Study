# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [16235] 나무 재테크
> https://www.acmicpc.net/problem/16235
## 알고리즘 분류
> BFS, 시뮬레이션

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Baek_16235_나무_재테크 {
	static int N, M, K;
	static int[] X = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] Y = { -1, 0, 1, 1, -1, 0, 1, -1 };
	static int[][] map, food;
	static Queue<Pos> tree, deadTree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		food = new int[N][N];
		tree = new LinkedList<>();
		deadTree = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = 5;
				food[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());

			tree.add(new Pos(x, y, age));
		}

		Collections.sort((List<Pos>) tree);

		while (K-- > 0) {
			spring();
			summer();
			fall();
			winter();
		}

		System.out.println(tree.size());
	}

	static void spring() {
		int size = tree.size();

		while (size-- > 0) {
			Pos p = tree.poll();

			if (map[p.x][p.y] >= p.age) {
				map[p.x][p.y] -= p.age++;
				tree.add(p);
			} else {
				deadTree.add(p);
			}
		}
	}

	static void summer() {
		while (!deadTree.isEmpty()) {
			Pos p = deadTree.poll();

			map[p.x][p.y] += p.age / 2;
		}
	}

	static void fall() {
		ArrayList<Pos> al = new ArrayList<>();

		int size = tree.size();
		while (size-- > 0) {
			Pos p = tree.poll();
			al.add(p);

			if (p.age % 5 != 0)
				continue;

			for (int d = 0; d < 8; d++) {
				int nx = p.x + X[d];
				int ny = p.y + Y[d];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				tree.add(new Pos(nx, ny, 1));
			}
		}

		for (Pos p : al)
			tree.add(p);
	}

	static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += food[i][j];
			}
		}
	}

	static class Pos implements Comparable<Pos> {
		int x, y, age;

		public Pos(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Pos o) {
			return this.age - o.age;
		}
	}
}
```

## 문제 풀이
1. 삼성 코딩테스트 기출문제로 주어진 조건을 만족하는 시뮬레이션을 코딩하면 해결
