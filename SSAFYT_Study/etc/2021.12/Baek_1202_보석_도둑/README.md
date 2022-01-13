# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [1012] 보석도둑
> https://www.acmicpc.net/problem/1012
## 알고리즘 분류
> 그리디, 우선순위 큐

## 코드
```java
package etc._2021_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1202_보석_도둑 {
	static int N, K;
	static int[] bags;
	static Jewel[] jewels;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		jewels = new Jewel[N];
		bags = new int[K];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			jewels[i] = new Jewel(m, v);
		}
		Arrays.sort(jewels);
		
		for(int i = 0; i < K; i++) {
			int weight = Integer.parseInt(br.readLine());
			bags[i] = weight;
		}
		Arrays.sort(bags);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		long ans = 0;
		int jewelIdx = 0;
		for(int i = 0; i < K; i++) {
            //보석을 다 탐색하지 않거나 보석의 무게가 가방의 무게보다 가볍다면 pq에 넣음
			while(jewelIdx < N && jewels[jewelIdx].weight <= bags[i]) {
				pq.offer(jewels[jewelIdx++].value);
			}
			
			if(!pq.isEmpty()) {
				ans += pq.poll();
			}
		}
		System.out.println(ans);
	}
	
	static class Jewel implements Comparable<Jewel>{
		int weight, value;
		
		public Jewel(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		@Override
		public int compareTo(Jewel o) {
			return this.weight - o.weight;
		}
	}
}
```

## 문제 풀이

> 단순 배열을 사용하면 가방마다 모든 보석들을 처음부터 반복해서 확인해야하기 때문에 시간초과가 날 수 있다.
>
> 정렬과 우선순위 큐를 사용해 이미 확인한 보석은 다시 확인하지 않도록 해 시간 복잡도를 줄일 수 있다.(우선순위 큐를 value의 내림차순으로 했기 때문에 가방마다 최대의 value값이 들어가게 된다.)

1. 보석의 정보들을 jewels 배열에 담고 보석의 무게 오름차순으로 정렬한다.
2. 가방의 정보들을 bags 배열에 담고 가방의 무게 내림차순으로 정렬한다.
3. pq를 내림차순으로 선언한다.
4. 보석을 다 탐색하지 않거나 보석의 무게가 가방의 무게보다 가볍다면 pq에 넣는다.
5. pq를 내림차순으로 선언했기 때문에 가장 앞의 보석이 가장 높은 value를 가지게 된다.
