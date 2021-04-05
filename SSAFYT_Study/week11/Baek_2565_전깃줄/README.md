# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/10.svg" width="30"> [2565] 전깃줄
## 문제 링크
> https://www.acmicpc.net/problem/2565
## 알고리즘 분류
> DP, LCS

## 코드
```java
package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Baek_2565_전깃줄 {
	static int N;
	static int[] dp;
	static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][2];
		dp = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[0] - o2[0];
			}
		});
		
		int line = 1;
		for(int i = 1; i <= N; i++) {
			dp[i] = 1;
			for(int j = 1; j < i; j++) {
				// 과거 A의 전깃줄과 이어진 B 번호보다 현재 B 번호가 더 커야 이을 수 있다
				if(arr[i][0] > arr[j][0])
					dp[i] = Math.max(dp[i], dp[j]+1);
			}
			line = Math.max(line, dp[i]);
		}
		
		System.out.println(N - line);
	}
}
```

## 문제 풀이
> O(n^2)의 형태로 풀었다. 이분 탐색을 사용하면 O(nlogn)으로 줄일 수가 있다.
* 전형적인 LCS문제이다.
* 1차원 배열사용해서 이전값과 비교하며 현재값보다 작은 값이 이전에 있다면 갱신해주면 된다.

