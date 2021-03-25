# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30">[11404] 플로이드
## 문제 링크
> https://www.acmicpc.net/problem/11404
## 알고리즘 분류
> 플로이드 와샬 알고리즘

## 코드
```java
package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_11404_플로이드 {
	static int N, M;
	static int[][] map;

	static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(i==j) {
						continue;
					}
					if(map[i][j] > map[i][k] + map[k][j])
						map[i][j] = map[i][k] + map[k][j];
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++)
			Arrays.fill(map[i], 123456789);
		
		int from, to, weight;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			map[from][to] = Math.min(weight, map[from][to]);
		}
		
		floyd();
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(map[i][j] == 123456789)
					sb.append(0).append(" ");
				else
					sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}


```

## 문제 풀이
적어야되는데?
