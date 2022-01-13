# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [2230] 수 고르기
> https://www.acmicpc.net/problem/2230
## 알고리즘 분류
> 투포인터

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_2230_수_고르기 {
	static int N, M;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		
		int ans = Integer.MAX_VALUE;
		for(int i = 0, j = 0; i < N;) {
			if(arr[j] - arr[i] < M) {
				i++;
			}
			else if(arr[i] - arr[j] == M){
				ans = M;
				break;
			}
			else {
				ans = Math.min(arr[i]-arr[j], ans);
				j++;
			}
		}
		
		System.out.println(ans);
	}
}
```

## 문제 풀이

1. 입력 값을 배열에 저장하고 배열을 정렬한다.
2. 투 포인터 알고리즘을 사용해 M의 크기만큼 옮겨가며 확인하면 됨.
