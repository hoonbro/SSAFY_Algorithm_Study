# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [5582] 공통 부분 문자열
> https://www.acmicpc.net/problem/5582
## 알고리즘 분류
> DP

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_5582_공통_부분_문자열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		String b = br.readLine();
		int[][] dp = new int[a.length()+1][b.length()+1];
		int max = 0;
		
		for(int i = 1; i <= a.length(); i++) {
			for(int j = 1; j <= b.length(); j++) {
				
				if(a.charAt(i-1) == b.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1]+1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		
		System.out.println(max);
		
	}
}
```

