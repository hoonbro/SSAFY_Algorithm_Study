# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/14.svg" width="30"> [2211] 네트워크 복구
> https://www.acmicpc.net/problem/2211
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

public class Baek_2211_네트워크복구 {
	static int N, M;
	static int[] path;
	static StringBuilder sb = new StringBuilder();
	static ArrayList<ArrayList<Node>> al;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		path = new int[N+1];
		al = new ArrayList<ArrayList<Node>>();
		for(int i = 0; i <= N; i++) {
			al.add(new ArrayList<Node>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			al.get(a).add(new Node(b, c));
			al.get(b).add(new Node(a, c));
		}
		
		dijkstra();
		System.out.println(N-1);
		System.out.println(sb.toString());
	}
	
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		int[] dis = new int[N+1];
		Arrays.fill(dis, 987654321);

		dis[1] = 0;
		pq.offer(new Node(1, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(visited[now.v])
				continue;
			
			visited[now.v]= true; 
			
			for(Node next : al.get(now.v)) {
				if(dis[next.v] > now.c + next.c) {
					dis[next.v] = now.c + next.c;
					pq.offer(new Node(next.v, dis[next.v]));
					path[next.v]= now.v; 
				}
			}
		}
		
		for(int i = 2; i <= N; i++)
			sb.append(i).append(" ").append(path[i]).append("\n");
	}
	
	static class Node implements Comparable<Node>{
		int v, c;

		public Node(int v, int c) {
			this.v = v;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return this.c - o.c;
		}
	}
}
```

## 문제 풀이
1. 
