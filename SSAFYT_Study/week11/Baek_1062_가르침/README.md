# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [1062] 가르침
## 문제 링크
> https://www.acmicpc.net/problem/1062
## 알고리즘 분류
> 조합

## 코드
```java
package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1062_가르침 {
	static int N, K, ans = 0;
	static String[] teach;
	static boolean[] alpha;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		teach = new String[N];
		alpha = new boolean[26];

		alpha['a'-'a'] = true;
		alpha['n'-'a'] = true;
		alpha['t'-'a'] = true;
		alpha['i'-'a'] = true;
		alpha['c'-'a'] = true;
		
		for (int i = 0; i < N; i++) {
			teach[i] = br.readLine();
			teach[i] = teach[i].substring(4, teach[i].length() - 4);
		}
		if (K < 5)
			System.out.println(0);
		else if (K == 26)
			System.out.println(N);
		else {
			K-=5;
			combi(0, 0);
			System.out.println(ans);
		}
	}

	static void combi(int idx, int cnt) {
		if (cnt == K) {
			int total = 0;
			for (int i = 0; i < N; i++) {
				boolean flag = false;
				for (int j = 0; j < teach[i].length(); j++) {
					if (!alpha[teach[i].charAt(j)- 'a'] ) {
						flag = true;
						break;
					}
				}
				if (!flag)
					total++;
			}
			ans = Math.max(total, ans);
			return;
		}

		for (int i = idx; i < 26; i++) {
			if (alpha[i])
				continue;

			alpha[i] = true;
			combi(i + 1, cnt + 1);
			alpha[i] = false;
		}
	}
}

```

## 문제 풀이
* 조합을 사용해 문제를 해결했다.
* alpha 배열(체크배열)를 사용했고, 기본적으로 사용되는 a,n,t,i,c는 사용중이라는 것을 체크해준다.
* combi메서드를 사용해 조합을 해준 뒤, cnt==K일때 알 수 있는 문자의 갯수를 세주고 최대값과 비교하며 초기화해준다.
