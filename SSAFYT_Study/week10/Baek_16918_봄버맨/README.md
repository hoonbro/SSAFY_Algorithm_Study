# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/10.svg" width="30"> [16918] 봄버맨
## 문제 링크
> https://www.acmicpc.net/problem/16918
## 알고리즘 분류
> 구현, BFS

## 코드
```java
package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_16918_봄버맨 {
	static int N, M, T, nx,ny;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	static char[][] map;
	static int[][] time;
	
	static class Pos{
		int x, y, cnt;

		public Pos(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.cnt = time;
		}
	}
	
	static void boom() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(time[i][j] == 1) {
					map[i][j] = '.';
					for(int d = 0; d < 4; d++) {
						nx = i + X[d];
						ny = j + Y[d];
						
						if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == '.')
							continue;
						
						map[nx][ny] = '.';
					}
				}
				else {
					if(map[i][j] == '.')
						continue;
					time[i][j]--;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		time = new int[N][M];
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'O') {
					time[i][j] = 3;
				}
			}
		}
		boom();
		while(T-- > 1) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == '.') {
						time[i][j] = 4;
						map[i][j] = 'O';
					}
				}
			}
			boom();

		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}

```

## 문제 풀이
* 2차원 int배열과 2차원 char배열을 선언해 폭탄의 유무와 지역의 남은 시간을 체크해줬다.
* 봄버맨이 첫 1초까지는 행동을 취하지 않으므로 반복문전에 boom메서드를 한번 실행시킨다.
* 그리고 각 시간마다 폭탄이 없는 지역에는 폭탄을 채우고 boom메서드를 실행해 폭파 or 시간을 줄인다.
