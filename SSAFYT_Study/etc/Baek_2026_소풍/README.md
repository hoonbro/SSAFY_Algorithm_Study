# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [2026] 소풍
> https://www.acmicpc.net/problem/2026
## 알고리즘 분류
> 백트래킹

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_2026_소풍 {
	static int K, N, F;
	static boolean flag = false;
	static int[] ans;
	static boolean[] check;
	static ArrayList<ArrayList<Integer>> al;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());

		ans = new int[K];
		check = new boolean[N + 1];
		al = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i <= N; i++) {
			al.add(new ArrayList<>());
		}

		for (int i = 0; i < F; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			al.get(a).add(b);
			al.get(b).add(a);
		}
		dfs(1, 0);

		if (flag) {
			for (int i = 0; i < K; i++) {
				sb.append(ans[i]).append("\n");
			}
			System.out.println(sb.toString());
		}else {
			System.out.println(-1);
		}
	}

	static void dfs(int idx, int cnt) {
		if (cnt == K || flag) {
			flag = true;
			return;
		}

		loop: for (int i = idx; i <= N; i++) {
			if (flag)
				return;

			if (check[i] || al.get(i).size() < K - 1)
				continue;

			if (cnt > 0) {
				for (int j = 0; j < cnt; j++) {
					if (!al.get(ans[j]).contains(i))
						continue loop;
				}
			}

			ans[cnt] = i;
			check[i] = true;
			dfs(i + 1, cnt + 1);
			check[i] = false;
		}
	}
}
```

## 문제 풀이

> 다시 생각해보니 check배열을 필요없을 것 같다.
>
> (번호가 작은 순으로 출력하는 것이고, idx를 증가시켜가며 확인하기 때문)

1. 학생들의 친구관계를 입력 받고 인접 리스트로 저장한다.(양방향 이므로 a->b,b->a 모두)
2. 재귀 함수를 돈다.
   1. 현재 인덱스를 확인하며 친구 관계를 확인하고 배열에 넣는다.(K보다 작은 값이라면 무시)
   2. check배열을 사용해 사용중이라는 것을 체크하고 넘어간다.
   3. cnt가 주어진 K와 같아졌을 때 함수를 멈춘다.
