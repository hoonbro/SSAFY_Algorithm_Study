# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [16719] ZOAC
> https://www.acmicpc.net/problem/16719
## 알고리즘 분류
> 그리디

## 코드
```java
package etc._2022_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_16719_ZOAC {
	static int N;
	static char[] input;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input = br.readLine().toCharArray();
		N = input.length;
		visited= new boolean[N];
		
		dfs(0, N-1);
		
		System.out.println(sb.toString());
	}

	// 사전순으로 가장 앞에오는 문자를 찾고 해당 문자를 기준으로 오른쪽을 탐색하며 그 다음으로 빠른 문자를 찾는다.
	// 오른쪽 탐색을 마무리했다면 왼쪽을 탐색하며 빠른 문자열을 찾는다.
	static void dfs(int left, int right) {
		if(left > right)
			return;
		
		int idx = left;
		for(int i = left; i <= right; i++) {
			if(input[idx] > input[i])
				idx = i;
		}
		visited[idx] = true;
		
		for(int i = 0; i < N; i++) {
			if(!visited[i])
				continue;
			
			sb.append(input[i]);
		}
		sb.append("\n");
		
		dfs(idx+1, right);
		dfs(left, idx-1);
    }
}
```

## 문제 풀이
1. 
