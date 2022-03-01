# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [1092] 배
> https://www.acmicpc.net/problem/1092
## 알고리즘 분류
> 정렬, 그리디

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1092_배 {
	static int N, M;
	static int[] crain, weight;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		crain = new int[N+1];
		weight = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			crain[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(crain);
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		boolean flag = false;
		for(int i = 0; i < M; i++) {
			int w = Integer.parseInt(st.nextToken());
			
			if(flag || w >crain[N]) {
				flag = true;
				continue;
			}
			
			for(int j = 0; j <= N; j++) {
				if(w <= crain[j]) {
					weight[j]++;
					break;
				}
			}
		}
		
		if(flag) {
			System.out.println(-1);
			System.exit(0);
		}
		
		int cnt = 0;
		loop:
		while(M > 0) {
			cnt++;
			for(int i = N; i >= 1; i--) {
				for(int j = i; j >= 1; j--) {
					if(M == 0)
						break loop;
					if(weight[j] == 0)
						continue;
					
					M--;
					weight[j]--;
					break;
				}
			}
		}
		System.out.println(cnt);
	}
}
```

## 문제 풀이
1. 무게가 작은 크레인부터 크레인보다 작은 박스들의 개수를 세어둔다.
1. 무게제한이 높은 크레인부터 확인하며 들 수 있는 박스를 빼준다.
1. 박스의 수가 0개가 되었을 때 종료하며 time을 return
