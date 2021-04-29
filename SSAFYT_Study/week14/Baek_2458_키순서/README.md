# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [2458] 키순서
> https://www.acmicpc.net/problem/2458
## 알고리즘 분류
> 플로이드 와샬

## 코드
```java
package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2458_키순서 {
	static int N, M, T, ans;
	static boolean[][] know;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		know = new boolean[N + 1][N + 1];

		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			know[a][b] = true;
		}
		floyd();
		ans = 0;
		loop: for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				if (!know[i][j] && !know[j][i])
					continue loop;
			}
			ans++;
		}
		System.out.println(ans);
	}

	static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if(i == k || !know[i][k])
					continue;
				for (int j = 1; j <= N; j++) {
					if (!know[i][j] && (know[i][k] && know[k][j])) {
						know[i][j] = true;
					}
				}
			}
		}
	}
}
```

## 문제 풀이
* 플로이드와샬 알고리즘을 사용해 문제를 해결했다.
* 플로이드와샬 알고리즘을 사용해 학생들의 키 순서를 갱신해준다.
* 그리고 모든 학생들 다시 확인하며 ij 와 ji가 서로 누가 더 큰지 알고 있다면 정답의 갯수를 하나씩 늘려주면 간단하게 해결할 수 있다.