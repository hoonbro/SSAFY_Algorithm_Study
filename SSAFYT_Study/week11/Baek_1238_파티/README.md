# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [1238] 파티
## 문제 링크
> https://www.acmicpc.net/problem/1238
## 알고리즘 분류
> 다익스트라 알고리즘

## 코드
```java
package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1238_파티 {
	static int N, M, X, ans, INF = 987654321;
	static ArrayList<ArrayList<Node>> al;
	static ArrayList<ArrayList<Node>> al2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		al = new ArrayList<ArrayList<Node>>();
		al2 = new ArrayList<ArrayList<Node>>();
		
		for(int i = 0; i <= N; i++) {
			al.add(new ArrayList<Node>());
			al2.add(new ArrayList<Node>());
		}
		
		int a,b,t;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			al.get(a).add(new Node(b, t));
			al2.get(b).add(new Node(a, t));
		}
		
		int[] dis = dijkstra(al);
		int[] dis2 = dijkstra(al2);
		
		for(int i = 1; i <= N; i++) {
			ans = Math.max(dis[i] + dis2[i], ans);
		}
		System.out.println(ans);
	}
	
	static int[] dijkstra(ArrayList<ArrayList<Node>> list) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dis = new int[N+1]; 
		Arrays.fill(dis, INF);
		dis[X] = 0;
		pq.add(new Node(X, 0));
		Node now;
		while(!pq.isEmpty()) {
			now = pq.poll();
			
			for(Node next : list.get(now.to)) {
				if(dis[next.to] > now.weight + next.weight) {
					dis[next.to] = now.weight + next.weight;
					pq.add(new Node(next.to, dis[next.to]));
				}
			}
		}
		return dis;
	}
	
	static class Node implements Comparable<Node>{
		int to, weight;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
	}
}
```

## 문제 풀이
* 다익스트라 알고리즘을 사용해 문제를 해결했다.
* 왕복을 하며 최단거리를 구해야 하는데 단방향 그래프이기 때문에 기존 입력과 반대 입력에 대한 2개의 배열리스트를 만들어줬다.
* 각 리스트 마다 다익스트라 알고리즘을 적용해준 뒤, 인덱스를 확인하며 왕복비용이 가장 적은 인덱스를 출력한다.
