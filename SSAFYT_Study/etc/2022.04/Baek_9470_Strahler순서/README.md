# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [9470]  Strahler 순서
> https://www.acmicpc.net/problem/9470
## 알고리즘 분류
> 위상정렬

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_9470_Strahler순서 {
	static int T, K, M, P, ans;
	static int[] indegree, depth, count;
	static Queue<Integer> q;
	static ArrayList<ArrayList<Integer>> al;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			ans = 0;
			K = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			
			indegree = new int[M+1];
			depth = new int[M+1];
			count = new int[M+1];
			q = new LinkedList<>();
			al = new ArrayList<ArrayList<Integer>>();
			
			for(int i = 0; i <= M; i++) {
				al.add(new ArrayList<>());
			}
			
			for(int i = 0; i < P; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				al.get(a).add(b);
				indegree[b]++;
			}
			
			topologySort();
			
			sb.append(K).append(" ").append(ans).append("\n");
			
		}
		
		System.out.println(sb.toString());
	}
	
	static void topologySort() {
		for(int i = 1; i <= M; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
				depth[i]++;
				count[i]++;
			}
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			if(count[now] >= 2)
				depth[now]++;
			
			ans = Math.max(ans, depth[now]);
			
			for(int next : al.get(now)) {
				if(--indegree[next] == 0)
					q.offer(next);
				
				if(depth[next] < depth[now]) {
					depth[next] = depth[now];
					count[next] = 1;
				}else if(depth[next] == depth[now]) {
					count[next]++;
				}
			}
		}
	}
}
```

## 문제 풀이
1. 위상정렬 알고리즘에 문제에 나온 제한조건을 적용시켜 문제를 해결했다.
