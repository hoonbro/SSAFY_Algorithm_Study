# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [2212] 센서
> https://www.acmicpc.net/problem/2212
## 알고리즘 분류
> 그리디, 정렬

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_2212_센서 {
	static int N, K, ans = 0;
	static int[] arr, len;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		len = new int[N-1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for(int i = 0; i < N-1; i++) {
			len[i] = arr[i+1] - arr[i]; 
		}
		
		Arrays.sort(len);
		
		for(int i = 0; i < N-K; i++) {
			ans += len[i];
		}

		System.out.println(ans);
	}
}
```

## 문제 풀이
1. 삼성 코딩테스트 기출문제로 주어진 조건을 만족하는 시뮬레이션을 코딩하면 해결
