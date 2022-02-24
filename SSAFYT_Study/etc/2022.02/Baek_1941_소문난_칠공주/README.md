# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [1941] 소문난 칠공주
> https://www.acmicpc.net/problem/1941
## 알고리즘 분류
> 조합, BFS

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_1941_소문난_칠공주 {
	static int N = 5, ans = 0;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static char[][] map;
	static boolean[][] visited, check;
	static Queue<Pos> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[N][N];
		check = new boolean[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		combi(0, 0, 0, 0);
		System.out.println(ans);
	}

	static void combi(int idx, int cnt, int S, int Y) {
		if (cnt == 7) {
			if (S > 3 && bfs((idx-1)/N, (idx-1)%N)) {
				ans++;
			}
			return;
		}

		for (int i = idx; i < 25; i++) {
			int x = i / N;
			int y = i % N;

			visited[x][y] = true;
			if (map[x][y] == 'S')
				combi(i + 1, cnt + 1, S + 1, Y);
			else
				combi(i + 1, cnt + 1, S, Y + 1);

			visited[x][y] = false;
		}
	}

	static boolean bfs(int x, int y) {
		init();
		q.offer(new Pos(x, y));
		check[x][y] = true;

		int cnt = 1;
		while (!q.isEmpty()) {
			Pos p = q.poll();
					
			for (int d = 0; d < 4; d++) {
				int nx = p.x + X[d];
				int ny = p.y + Y[d];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || !visited[nx][ny] || check[nx][ny])
					continue;

				cnt++;
				check[nx][ny] = true;
				q.offer(new Pos(nx, ny));
			}
		}
		
		if(cnt == 7)
			return true;
		return false;
	}
	
	static void init() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				check[i][j] = false;
			}
		}
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
1. S가 4이상인 7개의 조합을 만든다.
2. 조합된 좌표들이 이어져있는지 bfs로 확인하며 이어져 있다면 정답을 1씩늘린다.
