# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [15684]사다리 조작
> https://www.acmicpc.net/problem/15684
## 알고리즘 분류
> DFS

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_15684_사다리_조작 {
	static int N, M, H, ans = -1;
	static boolean flag = false;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			map[a][b] = 1;
			map[a][b + 1] = 2;
		}

		for (int i = 0; i <= 3; i++) {
			dfs(0, 0, 0, i);

			if (flag) {
				ans = i;
				break;
			}
		}

		System.out.println(ans);
	}

	static void dfs(int x, int y, int cnt, int total) {
		if (flag)
			return;

		if (cnt == total) {
			if (check()) 
				flag = true;
			return;
		}

		for (int i = x; i < H; i++) {
			for (int j = y; j < N - 1; j++) {
				if (map[i][j] != 0 || map[i][j + 1] != 0)
					continue;

				map[i][j] = 1;
				map[i][j + 1] = 2;

				dfs(i, j+2, cnt + 1, total);
				
				map[i][j] = map[i][j + 1] = 0;
			}
			y=0;
		}
	}

	static boolean check() {
		for (int i = 0; i < N; i++) {
			int y = i;

			for (int x = 0; x < H; x++) {
				if (map[x][y] == 1)
					y++;
				else if(map[x][y] == 2)
					y--;
			}

			if (i != y) {
				return false;
			}

		}
		return true;
	}
}
```

## 문제 풀이
1. 
