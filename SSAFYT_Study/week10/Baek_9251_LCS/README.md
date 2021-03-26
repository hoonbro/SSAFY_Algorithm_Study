# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [9251] LCS
## 문제 링크
> https://www.acmicpc.net/problem/9251
## 알고리즘 분류
> DP

## 코드
```java
package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_9251_LCS {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		String b = br.readLine();
		int[][] dp = new int[a.length()+1][b.length()+1];
		
		for(int i = 1; i <= a.length(); i++) {
			for(int j = 1; j <= b.length(); j++) {
				
				if(a.charAt(i-1) == b.charAt(j-1))
					dp[i][j] = dp[i-1][j-1]+1;
				else
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		
		System.out.println(dp[a.length()][b.length()]);
	}
}

```

## 문제 풀이
* 2차원 DP테이블을 만든다.
	* 각 행과 열의 값이 다르다면 dp[i][j-1]과 dp[i-1][j]값중 큰값을 대입한다.
	* 각 행과 열의 값이 같다면 dp[i-1][j-1]값에서 1을 증가시킨 값을 대입한다.
