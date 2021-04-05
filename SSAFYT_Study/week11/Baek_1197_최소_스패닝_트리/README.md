# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [1197] 최소 스패닝 트리
## 문제 링크
> https://www.acmicpc.net/problem/1197
## 알고리즘 분류
> 크루스칼 알고리즘

## 코드
```java
package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1197_최소_스패닝_트리 {
	static int V, E;
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parent = new int[V+1];
		
		int from, to, weight;
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			pq.add(new Edge(from, to, weight));
		}
		
		make();
		long ans = 0;
		Edge e;
		int cnt = 0;
		while(!pq.isEmpty()) {
			e = pq.poll();
			if(find(e.from) != find(e.to)) {
				union(e.from, e.to);
				ans += e.weight;
				cnt ++;
				
				if(cnt == V-1)
					break;
			}
		}
		System.out.println(ans);
	}
	
	static void make() {
		for(int i = 1; i <= V; i++) 
			parent[i] = i;
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
	
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
}
```

## 문제 풀이
* 크루스칼 알고리즘(Union Find)를 사용해 문제를 해결했다.
* 최소 스패닝 트리는, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리를 말한다.
* 간선의 가중치를 오름차순으로 정렬한 뒤, Union find를 사용해 가중치의 합이 최소인 트리를 완성하면 된다.
