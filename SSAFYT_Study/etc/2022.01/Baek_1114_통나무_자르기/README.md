# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/15.svg" width="30"> [1114] 통나무 자르기
> https://www.acmicpc.net/problem/1114
## 알고리즘 분류
> 이분탐색

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1114_통나무_자르기 {
	static int L, K, C;
	static int[] cut, block;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		if (K < C)
			C = K;

		cut = new int[K + 1];
		block = new int[K + 1];

		cut[K] = L;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			cut[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(cut);
		block[0] = cut[0];

		for (int i = 1; i <= K; i++) {
			block[i] = cut[i] - cut[i - 1];
		}

		binarySearch();
	}

	static int check(int size) {
		int cnt = 0;
		int sum = 0;

		for (int i = K; i >= 0; i--) {
			if (block[i] > size)
				return -1;

			sum += block[i];

			if (sum > size) {
				cnt++;
				sum = block[i];

				if (cnt == C) {
					if (cut[i] > size)
						return -1;
					return cut[i];
				}
			}
		}
		return cut[0];
	}

	static void binarySearch() {
		int left = 1;
		int right = L;
		int max = L;
		int minIdx = L;

		while (left <= right) {
			int mid = (left + right) / 2;
			int idx = check(mid);

			if (idx >= 0) {
				max = mid;
				minIdx = idx;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		System.out.println(max + " " + minIdx);
	}
}

```

## 문제 풀이
1. 자를 수 있는 위치를 저장하는 배열 `cut`과 `cut` 들 사이의 길이를 저장하는 `block`배열 선언

2. `cut` 배열을 정렬한 후 자를 수 있는 길이를 `block`배열에 저장

3. 이분 탐색을 사용해 size 지정

4. check 메서드를 사용해 3번의 size가 자를 수 있는 크기인지 찾음

   4-1. block 배열의 뒤에서부터 확인

   4-2. block이 정한 크기인 size보다 크면 불가능(-1)

   4-3. sum에 block들을 더해감

   4-4. sum이 size보다 클 경우 이전 블럭에서 자르는 경우이기 때문에, cnt++하고 sum을 현재 블럭(block[i])으로 초기화시킴

   4-5. cnt가 입력값 C와 같아지면 종료
