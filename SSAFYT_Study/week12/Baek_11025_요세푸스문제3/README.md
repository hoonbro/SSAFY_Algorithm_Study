# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/14.svg" width="30"> [11025] 요세푸스 문제3
## 문제 링크
> https://www.acmicpc.net/problem/11025
## 알고리즘 분류
> 요세푸스 문제

## 코드
```java
package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_11025_요세푸스문제3 {
	static int N, K;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N+1];
		
		dp[1] = 1;
		
		int r = 0;
		for(int i = 1; i <= N; i++) {
			r = (r+K)%i;
		}
		System.out.println(r+1);
//		for(int i = 1; i < N; i++) {
//			if(dp[i] == i) {
//				dp[i+1] = 2;
//			}
//			else if(dp[i] ==  i-1) {
//				dp[i+1] = 1;
//			}
//			else
//				dp[i+1] = dp[i]+K;
//		}
//		
//		System.out.println(dp[N]);
	}
}

```

## 문제 풀이
* 다른분의 코드를 참고했다.
* 주석부분이 K가 3일때 요세푸스 문제를 dp로 푸는 점화식이다.
* K가 달라질 때마다 점화식이 달라져 다른 해결방법이 필요했다.