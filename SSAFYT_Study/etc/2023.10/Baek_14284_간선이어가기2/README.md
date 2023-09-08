# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [14284] 간선 이어가기 2
> https://www.acmicpc.net/problem/14284
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

public class Baek_14284_간선이어가기2 {
	static int N, M, S, T;
	static ArrayList<ArrayList<Node>> al = new ArrayList<ArrayList<Node>>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i <= N; i++) {
			al.add(new ArrayList<>());
		}
		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			al.get(from).add(new Node(to, cost));
			al.get(to).add(new Node(from, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		dijkstra();
	}
	
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		int[] dist = new int[N+1];
		Arrays.fill(dist, 987654321);
		
		pq.offer(new Node(S, 0));
		dist[S] = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(now.vertex == T) {
				System.out.print(dist[now.vertex]);
				break;
			}
					
			for(Node next : al.get(now.vertex)) {
				if(dist[next.vertex] > now.cost + next.cost) {
					dist[next.vertex] = now.cost + next.cost;
					pq.offer(new Node(next.vertex, dist[next.vertex]));
				}
			}
		}
	}
	
	static class Node implements Comparable<Node>{
		int vertex, cost;
		
		public Node(int vertex, int cost) {
			super();
			this.vertex = vertex;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
		
	}
}

```
