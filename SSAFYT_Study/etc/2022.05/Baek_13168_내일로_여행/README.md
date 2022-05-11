# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [13168] 내일로 여행
> https://www.acmicpc.net/problem/13168
## 알고리즘 분류
> 플로이드 와샬

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Baek_13168_내일로_여행 {
	static int N, R, M, K, INF = 1000000;
	static int[] trip;
	static int[][][] arr;
	static Map<String, Integer> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			String city = st.nextToken();
			map.put(city, i);
		}

		M = Integer.parseInt(br.readLine());
		trip = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			trip[i] = map.get(st.nextToken());
		}

		arr = new int[N][N][2];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(arr[i][j], INF);
			}
		}

		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			String type = st.nextToken();
			int a = map.get(st.nextToken());
			int b = map.get(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			int discount = cost*2;
			if (type.equals("Mugunghwa") || type.equals("ITX-Saemaeul") || type.equals("ITX-Cheongchun")) {
				discount = 0;
			}
			else if (type.equals("S-Train") || type.equals("v-Train"))
				discount /= 2;
			

			arr[a][b][0] = Math.min(cost*2, arr[a][b][0]);
			arr[b][a][0] = Math.min(cost*2, arr[b][a][0]);

			arr[a][b][1] = Math.min(discount, arr[a][b][1]);
			arr[b][a][1] = Math.min(discount, arr[b][a][1]);
		}

		floyd();

		int total1 = 0, total2 = R*2;

		for (int i = 1; i < M; i++) {
			total1 += arr[trip[i - 1]][trip[i]][0];
			total2 += arr[trip[i - 1]][trip[i]][1];
		}
		System.out.println(total1 <= total2 ? "No" : "Yes");
	}

	static void floyd() {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j || i == k || j == k)
						continue;

					if (arr[i][j][0] > arr[i][k][0] + arr[k][j][0])
						arr[i][j][0] = arr[i][k][0] + arr[k][j][0];

					if (arr[i][j][1] > arr[i][k][1] + arr[k][j][1])
						arr[i][j][1] = arr[i][k][1] + arr[k][j][1];
				}
			}
		}
	}
}
```

## 문제 풀이
1. 플로이드 와샬 알고리즘을 사용해 문제를 해결할 수 있다.
