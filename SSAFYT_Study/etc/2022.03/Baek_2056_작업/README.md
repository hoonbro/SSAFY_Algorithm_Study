# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [2056] 작업
> https://www.acmicpc.net/problem/2056
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

public class Baek_2056_작업 {
	static int N, ans = 0;
	static int[] result, cost, indegree;
	static ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		result = new int[N+1];
		cost = new int[N+1];
		indegree = new int[N+1];
		
		for(int i = 0; i <= N; i++) {
			al.add(new ArrayList<>());
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			cost[i] = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < num; j++) {
				int prev = Integer.parseInt(st.nextToken());
				indegree[i]++;
				al.get(prev).add(i);
			}
		}
		
		topologicalSort();
		
		for(int i = 1; i <= N; i++)
			ans = Math.max(result[i], ans);
		
		System.out.println(ans);
	}

	static void topologicalSort() {
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 1; i <= N; i++) {
			result[i] = cost[i];
			if(indegree[i] != 0)
				continue;
			
			q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int next : al.get(now)) {
				result[next] = Math.max(result[next], result[now] + cost[next]);
				
				if(--indegree[next] == 0) {
					q.offer(next);
				}
			}
		}
	}
}
```

## 문제 풀이
1. 기본 로직은 위상정렬 알고리즘의 로직을 따른다.
1. 위상정렬 알고리즘에서 현재 노드까지의 cost를 저장하는 result 배열을 만들고 cost를 갱신해간다.
1. 위상 정렬 알고리즘이 끝난 후 가장 큰 cost를 가진 노드가 마지막 노드이기 때문에 ans값에 넣어주고 출력한다.
