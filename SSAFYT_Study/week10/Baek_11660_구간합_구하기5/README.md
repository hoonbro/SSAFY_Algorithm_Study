# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/10.svg" width="30"> [11660] 구간 합 구하기 5
## 문제 링크
> https://www.acmicpc.net/problem/11660
## 알고리즘 분류
> DP

## 코드
```java
package week10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baek_11660_구간합_구하기5 {
	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = map[i][j - 1] + map[i - 1][j] - map[i - 1][j - 1] + map[i][j];
			}
		}
		
		int x1, y1, x2, y2;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
			sb.append(map[x2][y2] - map[x2][y1-1] - map[x1-1][y2] + map[x1-1][y1-1]).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
```

## 문제 풀이
* 누적합을 이용해서 문제를 해결했다.
* 현재까지의 누적합은 배열의 왼쪽, 위쪽값을 더하고 중복되는 왼쪽위 값을 빼주는 것으로 구해준다.
* 구간의 누적합을 구해야하기 때문에 처음 좌표를 기준으로 중복되는 값을 빼주는것으로 결과를 구해준다.
