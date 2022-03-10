# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [17779] 개리맨더링2
> https://www.acmicpc.net/problem/17779
## 알고리즘 분류
> 구현, 시뮬레이션

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_17779_개리맨더링2 {
	static int N, min = Integer.MAX_VALUE, total = 0;
	static int[] sum;
	static int[][] map;
	static boolean[][] check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		sum = new int[5];
		map = new int[N][N];
		check = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		}

		for (int x = 1; x < N - 1; x++) {
			for (int y = 2; y < N; y++) {
				for (int d1 = 1; d1 <= y - 1; d1++) {
					for (int d2 = 1; d2 <= N - y; d2++) {
						if (x + d1 + d2 >= N || y - d1 < 0 || y + d2 >= N)
							continue;

						split(x, y, d1, d2);
						count(x, y, d1, d2);
					}
				}
			}
		}

		System.out.println(min);
	}

	static void split(int x, int y, int d1, int d2) {
		sum = new int[5];
		check = new boolean[N][N];

		for (int i = 0; i <= d1; i++) {
			check[x + i][y - i] = true;
			check[x + d2 + i][y + d2 - i] = true;
		}

		for (int i = 0; i <= d2; i++) {
			check[x + i][y + i] = true;
			check[x + d1 + i][y - d1 + i] = true;
		}
	}

	static void count(int x, int y, int d1, int d2) {
		// 1선거구
		for (int i = 0; i < x + d1; i++) {
			for (int j = 0; j <= y; j++) {
				if (check[i][j])
					break;

				sum[0] += map[i][j];
			}
		}

		// 2선거구
		for (int i = 0; i <= x + d2; i++) {
			for (int j = N - 1; j > y; j--) {
				if (check[i][j])
					break;

				sum[1] += map[i][j];
			}
		}

		// 3선거구
		for (int i = x + d1; i < N; i++) {
			for (int j = 0; j < y - d1 + d2; j++) {
				if (check[i][j])
					break;

				sum[2] += map[i][j];
			}
		}

		// 4선거구
		for (int i = x + d2 + 1; i < N; i++) {
			for (int j = N - 1; j >= y - d1 + d2; j--) {
				if (check[i][j])
					break;

				sum[3] += map[i][j];
			}
		}

		sum[4] = total;

		for (int i = 0; i < 4; i++) {
			sum[4] -= sum[i];
		}

		Arrays.sort(sum);

		min = Math.min(min, sum[4] - sum[0]);
	}

	static void init() {
		for (int i = 0; i < 5; i++)
			sum[i] = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				check[i][j] = false;
			}
		}
	}
}
```

## 문제 풀이
1. 삼성 코딩테스트 기출문제로 주어진 조건을 만족하는 시뮬레이션을 코딩하면 해결
