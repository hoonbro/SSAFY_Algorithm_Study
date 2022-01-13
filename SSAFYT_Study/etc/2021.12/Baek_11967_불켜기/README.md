# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [11967] 불켜기
> https://www.acmicpc.net/problem/11967
## 알고리즘 분류
> 그래프 탐색, BFS

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_11967_불켜기 {
	static int N, M, ans = 1;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static ArrayList<Node>[][] map;
	static boolean[][] canMove, visited, isLight;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new ArrayList[N + 1][N + 1];
		canMove = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];
		isLight = new boolean[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			if (map[x1][y1] == null) {
				map[x1][y1] = new ArrayList<>();
			}
			map[x1][y1].add(new Node(x2, y2));
		}
		bfs();
		System.out.println(ans);
	}

	static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(1, 1));
		canMove[1][1] = visited[1][1] = isLight[1][1] = true;
		while (!q.isEmpty()) {
			Node now = q.poll();
			
			//갈 수 있는 방 체크
			for (int i = 0; i < 4; i++) {
				int nx = now.x + X[i];
				int ny = now.y + Y[i];

				if (nx <= 0 || ny <= 0 || nx > N || ny > N || canMove[nx][ny] || visited[nx][ny])
					continue;
				
				canMove[nx][ny] = true;
			}

			// 불켜기
			if (map[now.x][now.y] != null) {
				for (Node n : map[now.x][now.y]) {
					if(!isLight[n.x][n.y]) {
						isLight[n.x][n.y] = true;
						ans++;
					}
					
					// 불을 켠곳이 갈 수 있는 방일 경우
					if(!visited[n.x][n.y] && canMove[n.x][n.y]) {
						visited[n.x][n.y]= true; 
						q.offer(new Node(n.x, n.y));
					}
				}
			}

			//현재 위치에서 갈 수 있는 방으로 이동
			for (int i = 0; i < 4; i++) {
				int nx = now.x + X[i];
				int ny = now.y + Y[i];

				if (nx <= 0 || ny <= 0 || nx > N || ny > N || !isLight[nx][ny] || visited[nx][ny])
					continue;
				
				visited[nx][ny] = true;
				q.offer(new Node(nx, ny));
			}
		}
	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
```

## 문제 풀이
1. 방문체크, 불 켜짐 여부 체크, 갈 수 있는지 체크의 총 3가지 boolean 배열을 사용한 bfs
2. bfs를 실행하며 갈 수 있는 곳을 모두 체크한다.
3. 불을 키고 갈 수 있는 곳이라면 큐에 넣는다.
4. 현재 위치에서 이동이 가능한 곳을 모두 넣어준다.
5. 3번을 실행하며 불을 킬 수 있는 곳을 카운트해주고 마지막으로 출력한다.
