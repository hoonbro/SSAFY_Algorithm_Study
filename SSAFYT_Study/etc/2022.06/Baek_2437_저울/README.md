# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/14.svg" width="30"> [2437] 저울
> https://www.acmicpc.net/problem/2437
## 알고리즘 분류
> 그리디, 정렬

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int sum = 0;
		for(int i = 0; i < N; i++) {
			if(sum+1 < arr[i])
				break;
			
			sum += arr[i];
		}
		
		System.out.println(sum+1);
	}
}
```

