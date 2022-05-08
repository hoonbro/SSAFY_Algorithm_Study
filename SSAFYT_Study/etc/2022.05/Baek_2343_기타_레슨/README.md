# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/10.svg" width="30"> [2343] 기타 레슨
> https://www.acmicpc.net/problem/13904
## 알고리즘 분류
> 이분탐색

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2343_기타_레슨 {
	static int N, M, ans = Integer.MAX_VALUE;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int left = 0;
		int right = 0;

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());

			left = Math.max(arr[i], left);
			right += arr[i];
		}

		binarySearch(left, right);
		System.out.println(ans);

	}

	static void binarySearch(int left, int right) {
		while (left <= right) {
			int mid = (left + right) / 2;

			int cnt = check(mid);
			
			if (cnt <= M)
				right = mid - 1;
			else
				left = mid + 1;
		}

		ans = left;
	}

	static int check(int mid) {
		int cnt = 0;
		int sum = 0;
		for (int i : arr) {
			if (sum + i > mid) {
				sum = 0;
				cnt++;
			}
			sum += i;
		}
		if (sum != 0)
			cnt++;

		return cnt;
	}
}
```

## 문제 풀이
1. 블루레이 크기를 기준으로 이분탐색을 진행하고 크기가 M을 만족하는지 check 메서드로 확인해 문제를 해결했다.
