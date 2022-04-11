# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [2174]  로봇 시뮬레이션
> https://www.acmicpc.net/problem/2174
## 알고리즘 분류
> 구현, 시뮬레이션

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2174_로봇_시뮬레이션 {
	static int N, M, A, B;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static int[][] map;
	static Robot[] robots;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		robots = new Robot[A + 1];

		map = new int[N][M];
		for (int i = 1; i <= A; i++) {
			st = new StringTokenizer(br.readLine());

			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int dir = 0;
			char c = st.nextToken().charAt(0);

			if (c == 'S')
				dir = 0;
			else if (c == 'E')
				dir = 1;
			else if (c == 'N')
				dir = 2;
			else if (c == 'W')
				dir = 3;

			robots[i] = new Robot(x, y, dir);
			map[x][y] = i;
		}

		for (int i = 0; i < B; i++) {
			st = new StringTokenizer(br.readLine());

			int idx = Integer.parseInt(st.nextToken());
			char order = st.nextToken().charAt(0);
			int cnt = Integer.parseInt(st.nextToken());

			if (sb.length() != 0) {
				continue;
			}

			if (order == 'L' || order == 'R') {
				rotate(idx, order == 'L' ? 1 : -1, cnt);
			} else {
				move(idx, cnt);
			}
		}

		if(sb.length() == 0)
			sb.append("OK");
		
		System.out.println(sb.toString());
	}

	static void move(int idx, int cnt) {
		map[robots[idx].x][robots[idx].y] = 0;

		int nx = robots[idx].x;
		int ny = robots[idx].y;

		
		for (int i = 0; i < cnt; i++) {
			nx += X[robots[idx].dir];
			ny += Y[robots[idx].dir];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
				sb.append("Robot ").append(idx).append(" crashes into the wall\n");
				return;
			}
			if (map[nx][ny] != 0) {
				sb.append("Robot ").append(idx).append(" crashes into robot ").append(map[nx][ny]).append("\n");
				return;
			}
		}

		map[nx][ny] = idx;
		robots[idx].x = nx;
		robots[idx].y = ny;
	}

	static void rotate(int idx, int dir, int cnt) {
		cnt %= 4;

		robots[idx].dir += (dir * cnt);

		if (robots[idx].dir < 0)
			robots[idx].dir += 4;
		else if (robots[idx].dir > 3)
			robots[idx].dir -= 4;
	}

	static class Robot {
		int x, y, dir;

		public Robot(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}
```

## 문제 풀이
1. 문제에 나와있는 조건을 만족시키면 해결되는 시뮬레이션 문제이다.
1. JAVA에서 배열은 상단의 index가 0이고 하단의 index가 N인 반면에 문제에 주어진 내용은 상단이 N 하단이 0이기 때문에 방향을 바꿔서 적용시켜야한다.
