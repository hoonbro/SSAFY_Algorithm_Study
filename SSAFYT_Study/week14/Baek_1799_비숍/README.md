# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/14.svg" width="30"> [1799] 비숍
> https://www.acmicpc.net/problem/1799
## 알고리즘 분류
> 백트래킹

## 코드
```java
package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1799_비숍 {
	static int N, ans1 = 0, ans2 = 0;
	static int[] X = { -1, -1 };
	static int[] Y = { 1, -1 };
	static int[][] map;
	static boolean color;
	static boolean[][] visited;

	static boolean check(int x, int y) {
		int nx, ny;
		for (int d = 0; d < 2; d++) {
			nx = x;
			ny = y;

			while (true) {
				nx += X[d];
				ny += Y[d];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					break;

				if (visited[nx][ny])
					return false;
			}
		}
		return true;
	}

	static void dfs(int idx, int cnt) {
		if(idx == N*N) {
			if (color)
				ans1 = Math.max(ans1, cnt);
			else
				ans2 = Math.max(ans2, cnt);
			return;
		}
		
		int x = idx / N;
		int y = idx % N;

		if ((color && ((x + y) % 2 == 0)) || (!color && ((x + y) % 2 == 1))) 
			dfs(idx + 1, cnt);

		else if (map[x][y] == 0 || !check(x, y))
			dfs(idx + 1, cnt);
		
		else {
			visited[x][y] = true;
			dfs(idx + 1, cnt + 1);
			visited[x][y] = false;
			dfs(idx+1, cnt);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		color = false;
		dfs(0, 0);
		color = true;
		dfs(1, 0);
		System.out.println(ans1 + ans2);
	}
}
```

## 문제 풀이
> 대각선을 확인하는 체크 배열에서 모든 대각선을 확인했기 때문에 시간이 더 오래걸렸다.
> check1[x+y] || check2[x-y+N-1] 이렇게 확인하면 모두 확인을 할 필요가 없어 시간복잡도가 많이 줄어든다.
* subset을 사용해서 문제를 해결했다.
* 비숍의 경우 대각선으로만 갈 수 있기 때문에 비숍이 위치한 색이 다른색을 침범할 수 없다는 특징이 있다.
* 그렇기 때문에 boolean형 color변수를 사용해 색을 판단해 subset을 2번 진행했다.
* if ((color && ((x + y) % 2 == 0)) || (!color && ((x + y) % 2 == 1))) 이 부분을 색을 구분해줬고 해당하는 색만 탐색하도록 해줬다.
* idx가 마지막까지 탐색했다면 check배열을 통해서 겹치는 부분이 없는지를 확인해준 후에 ans를 갱신해줌으로써 문제를 해결했다.