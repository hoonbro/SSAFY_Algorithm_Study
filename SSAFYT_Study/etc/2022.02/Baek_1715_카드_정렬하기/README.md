# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [1715] 카드 정렬하기
> https://www.acmicpc.net/problem/1715
## 알고리즘 분류
> 우선순위 큐

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Baek_1715_카드_정렬하기 {
	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}

		int ans = 0;
		int sum = 0;
		while (pq.size() >= 2) {
			int a = pq.poll();
			int b = pq.poll();

			sum = a + b;
			ans += sum;
			pq.offer(sum);
		}

		System.out.println(ans);
	}
}
```

## 문제 풀이
1. 우선순위 큐를 사용해 PQ의 크기가 2이상일 때, 상위 2가지의 값을 더한 뒤 큐에 넣는 행동을 반복하며 더한 값을 결과에 더해준다.
