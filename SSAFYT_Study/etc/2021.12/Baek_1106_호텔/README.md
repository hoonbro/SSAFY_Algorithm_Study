# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/10.svg" width="30"> [1106] 호텔
> https://www.acmicpc.net/problem/1106
## 알고리즘 분류
> DP

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1106_호텔 {
	static int C, N, INF = 987654321, ans = 987654321;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int[] dp = new int[C+1001];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int customer = Integer.parseInt(st.nextToken());
			
			for(int j = customer; j < C+1001; j++) {
				dp[j] = Math.min(dp[j], cost + dp[j-customer]);
			}
		}
		
		for(int i = C; i < C+100; i++) {
			ans = Math.min(ans, dp[i]);
		}
		System.out.println(ans);
	}
}
```

## 문제 풀이
1. 배낭문제와 비슷한문제이다.
2. DP배열의 크기를 주어진 C가 아닌 최대값인 C+1001로 설정해줘야 한다.
