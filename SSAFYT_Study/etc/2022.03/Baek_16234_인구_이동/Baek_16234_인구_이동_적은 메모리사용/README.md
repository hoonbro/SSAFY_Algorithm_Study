# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [16234] 인구 이동
> https://www.acmicpc.net/problem/16234
## 알고리즘 분류
> BFS, 시뮬레이션

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_16234_인구_이동2 {
	static int N, L, R;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visited;
	static Queue<Pos> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		visited = new boolean[N][N];
		q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean isMove;
		int day = 0;
		while (true) {
			init();
			isMove = false;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j])
						continue;

					for(int d = 1; d <= 2; d++) {
						int nx = i + X[d];
						int ny = j + Y[d];
						
						if(!check(i, j, nx, ny))
							continue;
						
						if (findUnion(i, j)) {
							isMove = true;
						}
						break;
					}
					
					
				}
			}

			if (!isMove)
				break;

			day++;
		}

		System.out.println(day);
	}

	static void init() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j] = false;
			}
		}
	}

	static boolean findUnion(int x, int y) {
		ArrayList<Pos> al = new ArrayList<>();

		q.offer(new Pos(x, y));
		al.add(new Pos(x, y));
		visited[x][y] = true;

		int populaion = map[x][y];
		while (!q.isEmpty()) {
			Pos p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];

				if(!check(p.x, p.y, nx, ny))
					continue;

				visited[nx][ny] = true;
				q.offer(new Pos(nx, ny));
				populaion += map[nx][ny];
				al.add(new Pos(nx, ny));
			}
		}

		if (al.size() == 1)
			return false;

		populaion /= al.size();
		for (Pos p : al) {
			map[p.x][p.y] = populaion;
		}
		return true;
	}

	static boolean check(int x, int y, int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])
			return false;

		if (L > Math.abs(map[x][y] - map[nx][ny]) || Math.abs(map[x][y] - map[nx][ny]) > R)
			return false;
		return true;
	}

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
```

## 문제 풀이
1. 삼성 코딩테스트 기출문제로 주어진 조건을 만족하는 시뮬레이션을 코딩하면 해결
2. 또 한번의 bfs로 인구를 바꾸지 않고 첫번째 bfs를 돌릴 때 변수에 저장해둔 후 사용했다.
