# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [2931] 가스관
> https://www.acmicpc.net/problem/2931
## 알고리즘 분류
> 구현, 시뮬레이션

## 코드
```java
package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2931_가스관 {
	static int N, M;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static char[][] map;
	static boolean[][] visited;
	static Queue<Pos> q = new LinkedList<>();
	static boolean[][] pipes = {{true,false,true,false},{false,true,false,true},{true,true,true,true},{false,true,true,false},{true,true,false,false},{true,false,false,true},{false,false,true,true}}; 
	
	static char findPipe(int x, int y) {
		char pipe = ' ';
		
		boolean[] chk = new boolean[4];
		int nx,ny;
		for(int d = 0; d < 4; d++) {
			nx = x + X[d];
			ny = y + Y[d];
			
			if(!check(nx, ny))
				continue;
			
			if(map[nx][ny] == '.' || map[nx][ny] == 'Z' || map[nx][ny] == 'M')
				continue;
			
			if(d == 0 && (map[nx][ny] == '|' || map[nx][ny] == '+' || map[nx][ny] == '1' || map[nx][ny] == '4')) 
				chk[0] = true;
			else if(d == 1&& (map[nx][ny] == '-' || map[nx][ny] == '+' || map[nx][ny] == '3' || map[nx][ny] == '4'))
				chk[1] = true;
			else if(d == 2&& (map[nx][ny] == '|' || map[nx][ny] == '+' || map[nx][ny] == '2' || map[nx][ny] == '3'))
				chk[2] = true;
			else if(d == 3&& (map[nx][ny] == '-' || map[nx][ny] == '+' || map[nx][ny] == '1' || map[nx][ny] == '2'))
				chk[3] = true;
		}
		int n = 0;
		loop:
		for(int i = 0; i < pipes.length; i++) {
			for(int j = 0; j < 4; j++) {
				if(chk[j] != pipes[i][j])
					continue loop;
			}
			n = i;
			break;
		}
		if(n == 0) 
			pipe = '|';
		else if(n == 1)
			pipe = '-';
		else if(n == 2)
			pipe = '+';
		else if(n == 3)
			pipe = '1';
		else if(n == 4)
			pipe = '2';
		else if(n == 5)
			pipe = '3';
		else if(n == 6)
			pipe = '4';
		
		return pipe;
	}
	static boolean check(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}

	static void bfs() {
		Pos pos;
		int nx, ny;
		char pipe;
		while (!q.isEmpty()) {
			pos = q.poll();
			pipe = map[pos.x][pos.y];

			if (pipe == '.') {
				pipe = findPipe(pos.x, pos.y);
				System.out.println(++pos.x + " " + ++pos.y + " " + pipe);
				break;
			}
			switch (pipe) {
			case '-': // 가로
				for (int d = 1; d < 4; d += 2) {
					nx = pos.x + X[d];
					ny = pos.y + Y[d];

					if (!check(nx, ny))
						continue;

					if (!visited[nx][ny]) {
						visited[nx][ny] = true;
						q.offer(new Pos(nx, ny));
					}
				}
				break;
			case '|': // 세로
				for (int d = 0; d < 4; d += 2) {
					nx = pos.x + X[d];
					ny = pos.y + Y[d];

					if (!check(nx, ny))
						continue;

					if (!visited[nx][ny]) {
						visited[nx][ny] = true;
						q.offer(new Pos(nx, ny));
					}
				}
				break;
			case '+': // 십자
				for (int d = 0; d < 4; d++) {
					nx = pos.x + X[d];
					ny = pos.y + Y[d];

					if (!check(nx, ny))
						continue;

					if (!visited[nx][ny]) {
						visited[nx][ny] = true;
						q.offer(new Pos(nx, ny));
					}
				}
				break;
			case '1': // r
				for (int d = 1; d < 3; d++) {
					nx = pos.x + X[d];
					ny = pos.y + Y[d];

					if (!check(nx, ny))
						continue;

					if (!visited[nx][ny]) {
						visited[nx][ny] = true;
						q.offer(new Pos(nx, ny));
					}
				}
				break;
			case '2': // ㄴ
				for (int d = 0; d < 2; d++) {
					nx = pos.x + X[d];
					ny = pos.y + Y[d];

					if (!check(nx, ny))
						continue;

					if (!visited[nx][ny]) {
						visited[nx][ny] = true;
						q.offer(new Pos(nx, ny));
					}
				}
				break;
			case '3': //
				for (int d = 0; d < 4; d += 3) {
					nx = pos.x + X[d];
					ny = pos.y + Y[d];

					if (!check(nx, ny))
						continue;

					if (!visited[nx][ny]) {
						visited[nx][ny] = true;
						q.offer(new Pos(nx, ny));
					}
				}
				break;
			case '4': // ㄱ
				for (int d = 2; d < 4; d++) {
					nx = pos.x + X[d];
					ny = pos.y + Y[d];

					if (!check(nx, ny))
						continue;

					if (!visited[nx][ny]) {
						visited[nx][ny] = true;
						q.offer(new Pos(nx, ny));
					}
				}
				break;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);

			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != '.' && map[i][j] != 'M' && map[i][j] != 'Z') {
					q.offer(new Pos(i, j));
					visited[i][j] = true;
					bfs();
					break;
				}
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
* 구현 시뮬레이션 문제이고 bfs를 사용했다.
* findPipe메서드를 사용해 연결이 끊긴 곳의 파이프가 어떤 모양의 파이프인지를 찾게된다.
* pipes 2차원 배열에 파이프들의 모양을 저장해 놨고 chk배열을 사용해서 4방위를 확인하며 파이프가 연결될 수 있는 방위를 찾는다.
* 연결할 수 있는 방위를 찾은 chk배열을 사용해 어떤 파이프를 사용해야 하는지 찾아주면 문제를 해결할 수 있다.
