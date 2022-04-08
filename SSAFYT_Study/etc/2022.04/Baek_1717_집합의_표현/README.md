# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [1717]  집합의 표현
> https://www.acmicpc.net/problem/1717
## 알고리즘 분류
> 분리 집합

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1717_집합의_표현 {
	static int N, M;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		
		for(int i = 1; i <= N; i++)
			parent[i] = i;
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int type = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(type == 0) {
				union(a, b);
			}else {
				sb.append(find(a) == find(b) ? "YES" : "NO").append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static int find(int a) {
		if(a == parent[a])
			return a;
		
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b)
			return false;
		
		parent[b] = a;
		return true;
	}
}
```

## 문제 풀이
1. 두 원소가 같은 집합에 포함되어있는지 판단하기 위해 Union Find 알고리즘을 사용했다.
