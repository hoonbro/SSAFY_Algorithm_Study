# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [5972] 택배 배송
> https://www.acmicpc.net/problem/5972
## 알고리즘 분류
> 다익스트라

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_5972_택배_배송 {
	static int N, M;
	static int[] dis;
	static boolean[] visited;
	static Node[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dis = new int[N+1];
		visited = new boolean[N+1];
		list = new Node[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list[from] = new Node(to, cost, list[from]);
			list[to] = new Node(from, cost, list[to]);
		}
		
		djikstra();
		
		System.out.println(dis[N]);
	}
	
	static void djikstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(dis, 987654321);
		pq.offer(new Node(1, 0));
		dis[1] = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(visited[now.v])
				continue;
			
			visited[now.v] = true;
			
			for(Node next = list[now.v]; next!=null; next=next.prev) {
				if(dis[next.v] > now.c + next.c) {
					dis[next.v] = now.c + next.c;
					pq.offer(new Node(next.v, dis[next.v]));
				}
			}
		}
	}

	static class Node implements Comparable<Node>{
		int v, c;
		Node prev;

		public Node(int v, int c) {
			this.v = v;
			this.c = c;
		}
		
		public Node(int v, int c, Node prev) {
			this.v = v;
			this.c = c;
			this.prev = prev;
		}

		@Override
		public int compareTo(Node o) {
			return this.c - o.c;
		}
	}
}
```

## 문제 풀이
1. 다익스트라 알고리즘을 사용해 간단하게 문제를 해결했다.
