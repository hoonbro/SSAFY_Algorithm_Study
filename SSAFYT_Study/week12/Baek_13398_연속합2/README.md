# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [13398] 연속합2
## 문제 링크
> https://www.acmicpc.net/problem/13398
## 알고리즘 분류
> DP

## 코드
```java
package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_13398_연속합2 {
	static int N;
	static int[] arr;
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N][2];
		st= new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][0] = arr[0];
		dp[0][1] = arr[0];
		int max = arr[0];
		for(int i = 1; i < N; i++) {
			//안건너뛴 값 + 현재값 vs 현재값
			dp[i][0] = Math.max(dp[i-1][0] + arr[i], arr[i]);
			
			//건너뛴값 + 현재 vs 건너뛰지 않은값
			dp[i][1] = Math.max(dp[i-1][0], arr[i] + dp[i-1][1]);
			
			max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
		}
		System.out.println(max);
	}
}
```

## 문제 풀이
* DP를 사용해 문제를 해결했다.
* 2차원 배열의 dp[n][0]은 건너뛰지 않은 값, dp[n][1]은 건너뛴 값으로 사용했다.
* N-1번 반복하며 안건너뛴 값 + 현재값 vs 현재값으로 dp[n][0]을 갱신해주고 건너뛴값 + 현재 vs 건너뛰지 않은값으로 dp[n][1]을 갱신해준다.
* 마지막으로 각 반복마다 max값을 갱신해준다.