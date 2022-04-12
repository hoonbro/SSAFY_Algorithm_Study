# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/15.svg" width="30"> [1443]  망가진 계산기
> https://www.acmicpc.net/problem/1443
## 알고리즘 분류
> 백트래킹

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Baek_1443_망가진_계산기 {
	static int D, P;
	static long ans, max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		D = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		ans = 0L;
		max = (long) (Math.pow(10, D) - 1);

		dfs(1, 9, 0);
		System.out.println(ans == 0 ? -1 : ans);
	}

	static void dfs(long result, int num, int cnt) {
		if (result > max)
			return;
		
		if (cnt == P) {
			ans = Math.max(result, ans);
			return;
		}

		for (int i = num; i >= 2; i--) {
			dfs(result * i, i, cnt + 1);
		}
	}
}
```

## 문제 풀이
1. 백트래킹 문제로 1에서 곱해가는 수가 최대 자릿수를 넘어가지 않게 확인하며 DFS를 돌렸다.
