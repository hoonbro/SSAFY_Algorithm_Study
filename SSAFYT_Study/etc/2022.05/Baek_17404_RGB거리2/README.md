# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [17404] 17404
> https://www.acmicpc.net/problem/17404
## 알고리즘 분류
> DP

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17404_RGB거리2{
	static int N, INF = 987654321;;
	static int[][] arr, dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][3];
		dp = new int[N+1][3];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int min = INF;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(i == j)
					dp[1][j] = arr[1][j];
				else
					dp[1][j] = INF; 
			}
			
			for(int j = 2; j <= N; j++) {
				dp[j][0] = arr[j][0] + Math.min(dp[j-1][1], dp[j-1][2]);
				dp[j][1] = arr[j][1] + Math.min(dp[j-1][0], dp[j-1][2]);
				dp[j][2] = arr[j][2] + Math.min(dp[j-1][0], dp[j-1][1]);
			}
			
			for(int j = 0; j < 3; j++) {
				if(j == i)
					continue;
				
				min = Math.min(min, dp[N][j]);
			}
		}
		
		System.out.println(min);
	}
}
```

## 문제 풀이
1. 
