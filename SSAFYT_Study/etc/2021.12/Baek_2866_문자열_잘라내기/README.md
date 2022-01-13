# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [2866] 문자열 잘라내기
> https://www.acmicpc.net/problem/2866
## 알고리즘 분류
> 이분탐색

## 코드
```java
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Baek_2866_문자열_잘라내기 {
	static int N, M, ans;
	static char[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		binarySearch(); 
		System.out.println(ans);
	}
	
	static void binarySearch() {
		int left = 0;
		int right = N-1;
		int mid = 0;
		
		while(left <= right) {
			mid = (left + right)/2;
			
			if(check(mid, N)) {
				ans = mid;
				left = mid +1;
			}else
				right = mid -1;
		}
		
	}
	
	static boolean check(int start, int end) {
		Stack<String> stack = new Stack<>();

		for(int i = 0; i < M; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j = start; j < end; j++) {
				sb.append(arr[j][i]);
			}
			
			if(stack.contains(sb.toString()))
				return false;
			else
				stack.add(sb.toString());
		}
		return true;
	}
}
```

## 문제 풀이
1. 중복되는 문자열은 다음 문자열도 중복이 되므로 이분탐색을 사용한다.
