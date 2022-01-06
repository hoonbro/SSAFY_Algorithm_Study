# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [10159] 저울
> https://www.acmicpc.net/problem/10159
## 알고리즘 분류
> 플로이드 와샬

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_10159_저울 {
	static int N, M, INF = 987654321;
	static int[] cnt;
	static int[][] stuff;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		stuff = new int[N + 1][N + 1];
		cnt = new int[N + 1];
		Arrays.fill(cnt, N - 1);

		for (int i = 1; i <= N; i++) {
			Arrays.fill(stuff[i], INF);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			stuff[a][b] = 1;
		}

		floyd();

		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				if(stuff[i][j] == INF && stuff[j][i] == INF)
					cnt++;
			}
			sb.append(cnt).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
	}

	static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					stuff[i][j] = Math.min(stuff[i][j], stuff[i][k] + stuff[k][j]);
				}
			}
		}
	}
}
```

## 문제 풀이
1. 플로이드 와샬 알고리즘을 사용하면 간단하게 풀 수 있는 문제
2. stuff[i][j]와 stuff[j][i]를 모두 확인하며 크거나 작은 것 모두 확인할 수 없다면 cnt++해준다.
