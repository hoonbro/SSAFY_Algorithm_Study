# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [1593] 문자 해독
> https://www.acmicpc.net/problem/1593
## 알고리즘 분류
> 슬라이딩 윈도우

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1593_문자_해독 {
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		String W = br.readLine();
		String S = br.readLine();
		
		int[] wUsed = new int[58];
		int[] sUsed = new int[58];
		
		for(int i = 0; i < N; i++) {
			int a = W.charAt(i) - 'A';
			wUsed[a]++;
			
			int b = S.charAt(i) - 'A';
			sUsed[b]++;
		}
		
		int left = 0, right = N, ans = 0;
		while(true) {
			if(Arrays.equals(wUsed, sUsed))
				ans++;
			
			if(right >= M)
				break;
			
			sUsed[S.charAt(left++) - 'A']--;
			sUsed[S.charAt(right++) - 'A']++;
		}
		
		System.out.println(ans);
	}
}
```

## 문제 풀이
1. 슬라이딩 윈도우 알고리즘을 사용해 사용된 알파벳의 갯수가 같은지 확인하면 되는 문제이다.
1. Arrays.equal를 처음 알게 되었고 배열 두개의 일치여부를 판단하는데 좋은 라이브러리인 것같다.
