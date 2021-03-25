# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/10.svg" width="30"> [1932] 정수삼각형
## 문제 링크
> https://www.acmicpc.net/problem/1932
## 알고리즘 분류
> DP

## 코드
```java
package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1932_정수삼각형 {
	static int N;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < i+1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int max = arr[0][0];
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < i+1; j++) {
				if(j == 0)
					arr[i][j] += arr[i-1][j];
				else if(j == N-1)
					arr[i][j] += arr[i-1][j-1];
				else
					arr[i][j] += Math.max(arr[i-1][j], arr[i-1][j-1]);
				max = Math.max(max, arr[i][j]);
			}
		}
		System.out.println(max);
	}
}

```

## 문제 풀이
적어야되는데?
