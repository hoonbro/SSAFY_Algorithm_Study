# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/15.svg" width="30"> [18809] Gaaaaaaaaaarden
> https://www.acmicpc.net/problem/18809
## 알고리즘 분류
> 순열 + BFS

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_18809_Gaaaaaaaaaarden {
	static int N, M, G, R, len, ans = 0;
	static int[] arrG, arrR;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static int[][] map;
	static Pos[][] visited;
	static ArrayList<Pos> al = new ArrayList<>();
	static Queue<Pos> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		arrG = new int[G];
		arrR = new int[R];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					al.add(new Pos(i, j, 0));
				}
			}
		}
		len = al.size();

		dfs(0, 0, 0);

		System.out.println(ans);
	}

	static void bfs() {
		visited = new Pos[N][M];

		for (int i = 0; i < G; i++) {
			Pos p = al.get(arrG[i]);
			q.offer(p);
			visited[p.x][p.y] = new Pos(p.cnt, 'G');
		}

		for (int i = 0; i < R; i++) {
			Pos p = al.get(arrR[i]);
			q.offer(p);
			visited[p.x][p.y] = new Pos(p.cnt, 'R');
		}

		int flower = 0;
		while (!q.isEmpty()) {
			Pos now = q.poll();
			int cnt = visited[now.x][now.y].cnt;
			char color = visited[now.x][now.y].color;
			
			if (visited[now.x][now.y].color == 'F')
				continue;

			for (int d = 0; d < 4; d++) {
				int nx = now.x + X[d];
				int ny = now.y + Y[d];

				if (!check(nx, ny))
					continue;

				if (visited[nx][ny] == null) {
					visited[nx][ny] = new Pos(now.cnt + 1, color);
					q.offer(new Pos(nx, ny, now.cnt + 1));
				} 
				else if(visited[nx][ny].color == 'G') {
					if(color == 'G' || visited[nx][ny].cnt != cnt+1)
						continue;
					
					flower++;
					visited[nx][ny].color = 'F';
				}
				else if(visited[nx][ny].color == 'R') {
					if(color == 'R' || visited[nx][ny].cnt != cnt +1)
						continue;
					
					flower++;
					visited[nx][ny].color = 'F';
				}
			}
		}
		ans = Math.max(ans, flower);
	}

	static boolean check(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 0)
			return false;

		return true;
	}

	static void dfs(int idx, int g, int r) {
		if (g == G && r == R) {
			bfs();
			return;
		}

		if (g < G) {
			for (int i = idx; i < len; i++) {
				arrG[g] = i;
				dfs(i + 1, g + 1, r);
			}
		}

		if (r < R) {
			for (int i = idx; i < len; i++) {
				arrR[r] = i;
				dfs(i + 1, g, r + 1);
			}
		}
	}

	static class Pos {
		int x, y, cnt;
		char color;

		public Pos(int cnt, char color) {
			this.cnt = cnt;
			this.color = color;
		}

		public Pos(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}
```

## 문제 풀이
1. 배양액의 상태를 나타내는 클래스 Pos를 선언한다.

   - 생성자를 2개 사용해 각각 큐와 visited배열에 사용했다.

2. input값을 입력 받으며 배양액을 줄 수 있는 토양(2)를 ArrayList에 넣어준다.

3. ArrayList를 가지고 red, green의 순열을 만들어준다.

4. 순열이 완성되었을 때 bfs함수를 실행시킨다.

   1. red와 green 순열을 각각 queue에 넣어준다.

   2. bfs를 시작하는데 **visited의 color가 Flower가 된다면 더이상 돌리지 않는다.**

   3. 현재 배양액 위치에서 4방위를 탐색한다.

      3 - 1. 배양액이 없다면, cnt+1를 하고 같은 색생의 배양액을 Queue에 넣어준다.

      3 - 2. 배양액이 있다면, 현재 배양액의 색상과 비교하고 같다면 무시하고 다르다면 cnt를 비교한 후, cnt가 같다면 꽃(F)를 넣어준다. 넣어준 후 현재위치 visited[nx[ny].color를 꽃(F)라고 표시한다.

   4. Queue가 비었다면 flower의 수를 ans와 비교하며 최대값을 ans에 넣어준다.ㅇ

5. 더이상 순열이 없을 때까지 4번을 반복해준다.
6. ans를 출력한다.
