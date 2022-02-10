# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/14.svg" width="30"> [17837] 새로운 게임2
> https://www.acmicpc.net/problem/17837
## 알고리즘 분류
> 구현 시뮬레이션

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_17837_새로운_게임2 {
	static int N, K;
	static int[] X = { 0, 0, -1, 1 };
	static int[] Y = { 1, -1, 0, 0 };
	static int[][] map;
	static Horse[] horses;
	static ArrayList<Integer>[][] order;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		order = new ArrayList[N][N];
		map = new int[N][N];
		horses = new Horse[K];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				order[i][j] = new ArrayList<Integer>();
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) - 1;

			horses[i] = new Horse(x, y, dir);
			order[x][y].add(i);
		}

		System.out.println(play());
	}

	static int play() {
		int time = 1;

		game: while (time < 1000) {
			for (int i = 0; i < K; i++) {
				int x = horses[i].x;
				int y = horses[i].y;
				int dir = horses[i].dir;

				int nx = x + X[dir];
				int ny = y + Y[dir];

				// 파란색 = -1
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2) {
					dir = reverseDir(dir);
					horses[i].dir = dir;
					nx = x + X[dir];
					ny = y + Y[dir];

					if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2) {
						continue;
					}
				}

				// horse 위치 찾기
				int idx = -1;
				for (int j = 0; j < order[x][y].size(); j++) {
					if (order[x][y].get(j) == i) {
						idx = j;
						break;
					}
				}

				// 빨간색
				if (map[nx][ny] == 1) {
					// 이동 지역에 옮김
					for (int j = order[x][y].size() - 1; j >= idx; j--) {
						int horseIdx = order[x][y].get(j);

						order[nx][ny].add(horseIdx);
						horses[horseIdx].x = nx;
						horses[horseIdx].y = ny;
					}
					
					// 이전 위치에서 삭제
					for (; idx < order[x][y].size();) {
						order[x][y].remove(idx);
					}
				}
				// 흰색
				else {
					// 이동 지역에 옮김
					for (int j = idx; j < order[x][y].size(); j++) {
						int horseIdx = order[x][y].get(j);

						order[nx][ny].add(horseIdx);
						horses[horseIdx].x = nx;
						horses[horseIdx].y = ny;
					}

					// 이전 위치에서 삭제
					for (; idx < order[x][y].size();) {
						order[x][y].remove(idx);
					}
				}

				if (order[nx][ny].size() >= 4) {
					break game;
				}
			}
			time++;
		}

		return time >= 1000 ? -1 : time;
	}

	static int reverseDir(int dir) {
		if (dir == 0 || dir == 1)
			return 1 - dir;
		else
			return 5 - dir;
	}

	static class Horse {
		int x, y, dir;

		public Horse(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}
```

## 문제 풀이
1. 주어진 조건을 맞추면 문제를 해결할 수 있다.
