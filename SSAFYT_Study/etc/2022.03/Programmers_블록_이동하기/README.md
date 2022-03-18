# [Level3] 광고 삽입
## 문제 링크
> https://programmers.co.kr/learn/courses/30/lessons/92344
## 알고리즘 분류
> 누적합

## 코드
```java
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_블록_이동하기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] board = { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 1, 0, 1, 1 }, { 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0} };

		System.out.println(solution(board));
	}

	static int N, M, ans;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static Queue<Robot> q = new LinkedList<>();
	static int[][][] visited;

	public static int solution(int[][] board) {
		N = board.length;
		M = board[0].length;

		visited = new int[N][M][2];

		bfs(board);

		return ans;
	}

	static void bfs(int[][] board) {
		visited[0][0][0] = 1;
		visited[0][1][0] = 1;
		q.offer(new Robot(0, 0, 0, 1, 0, 1));

		while (!q.isEmpty()) {
			Robot r = q.poll();
			if ((r.x1 == N - 1 && r.y1 == M - 1) || (r.x2 == N - 1 && r.y2 == M - 1)) {
				ans = r.time -1;
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nx1 = r.x1 + X[d];
				int ny1 = r.y1 + Y[d];
				int nx2 = r.x2 + X[d];
				int ny2 = r.y2 + Y[d];

				if (!check(nx1, ny1, board) || !check(nx2, ny2, board))
					continue;

				if (visited[nx1][ny1][r.dir] == 0 || visited[nx2][ny2][r.dir] == 0
						|| visited[nx1][ny1][r.dir] != visited[nx2][ny2][r.dir]) {
					visited[nx1][ny1][r.dir] = visited[nx2][ny2][r.dir] = r.time + 1;
					q.offer(new Robot(nx1, ny1, nx2, ny2, r.dir, r.time + 1));
				}
			}

			// 회전
			rotate(r.x1, r.y1, r.x2, r.y2, r.dir, r.time, board);
			rotate(r.x2, r.y2, r.x1, r.y1, r.dir, r.time, board);
		}
	}

	static void rotate(int x1, int y1, int x2, int y2, int dir, int time, int[][] board) {
		if (dir == 0) {
			if (check(x2 - 1, y2, board) && check(x1 - 1, y1, board)) {
				if (visited[x1][y1][1] == 0 || visited[x1 - 1][y1][1] == 0
						|| visited[x1][y1][1] != visited[x1 - 1][y1][1]) {
					visited[x1][y1][1] = visited[x1 - 1][y1][1] = time + 1;
					q.offer(new Robot(x1, y1, x1 - 1, y1, 1, time + 1));
				}
			}
			if (check(x2 + 1, y2, board) && check(x1 + 1, y1, board)) {
				if (visited[x1][y1][1] == 0 || visited[x1 + 1][y1][1] == 0
						|| visited[x1][y1][1] != visited[x1 + 1][y1][1]) {
					visited[x1][y1][1] = visited[x1 + 1][y1][1] = time + 1;
					q.offer(new Robot(x1, y1, x1 + 1, y1, 1, time + 1));
				}
			}
		} else {
			if (check(x2, y2 - 1, board) && check(x1, y1 - 1, board)) {
				if (visited[x1][y1][0] == 0 || visited[x1][y1 - 1][0] == 0
						|| visited[x1][y1][0] != visited[x1][y1 - 1][0]) {
					visited[x1][y1][0] = visited[x1][y1 - 1][0] = time + 1;
					q.offer(new Robot(x1, y1, x1, y1 - 1, 0, time + 1));
				}
			}
			if (check(x2, y2 + 1, board) && check(x1, y1 + 1, board)) {
				if (visited[x1][y1][0] == 0 || visited[x1][y1 + 1][0] == 0
						|| visited[x1][y1][0] != visited[x1][y1 + 1][0]) {
					visited[x1][y1][0] = visited[x1][y1 + 1][0] = time + 1;
					q.offer(new Robot(x1, y1, x1, y1 + 1, 0, time + 1));
				}
			}
		}
	}

	static boolean check(int x, int y, int[][] board) {
		if (x < 0 || y < 0 || x >= N || y >= M || board[x][y] == 1)
			return false;
		
		return true;
	}

	static class Robot {
		int x1, y1, x2, y2, dir, time;

		public Robot(int x1, int y1, int x2, int y2, int dir, int time) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.dir = dir;
			this.time = time;
		}
	}
}
```
## 문제 풀이

1. 문제의 형식은 BFS 문제이지만 로봇의 크기가 2개의 좌표를 포함하고 있기 때문에 까다로웠다.
2. 방문체크에 사용하는 visited배열을 int형으로 사용해 거리를 저장해두었고 3차원으로 만들어 0일때는 가로 1일때는 세로의 경우의 방문을 체크했다.
