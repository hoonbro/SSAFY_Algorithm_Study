# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/10.svg" width="30"> [13335] 트럭
## 문제 링크
> https://www.acmicpc.net/problem/13335
## 알고리즘 분류
> 구현, 시뮬레이션

## 코드
```java
package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_13335_트럭 {
	static int N, W, L, ans = 0;
	static int[] truck, bridge;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		truck = new int[N];
		bridge = new int[W];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			truck[i] = Integer.parseInt(st.nextToken());
		}
		
		int i = 0, bridgeWeight = 0, cnt = 0;
		while(i < N) {
			cnt++;
			int a = bridge[0];
			for(int j = 1; j < W; j++) {
				bridge[j-1] = bridge[j]; //다리위 트럭 움직임
			}
			bridge[W-1] = 0;
			
			bridgeWeight -= a;
			
			if(bridgeWeight + truck[i] <= L) {
				bridge[W-1] = truck[i];
				bridgeWeight += truck[i];
				i++;
			}
		}
		System.out.println(W + cnt);
	}
}
```

## 문제 풀이
* 트럭배열과 다리 배열을 따로 선언해줬다.
* 다리위의 트럭을 먼저 움직이고, 다음에 올 트럭이 다리 위에 올라올 수 있는 무게라면 올려준다.
* 매초마다 바로 위의 행동을 하고 시간이 끝나면 다리의 길이와 시간을 더해주는 것으로 문제를 해결했다.