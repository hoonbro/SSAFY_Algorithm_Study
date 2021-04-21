# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [1916] 최소비용 구하기
## 문제 링크
> https://www.acmicpc.net/problem/1916
## 알고리즘 분류
> 

## 코드
```java
package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1916_최소비용_구하기 {
	static int N,M, INF = 987654321;
	static int[] dis;
	static ArrayList<ArrayList<Node>> al;
	
	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dis[start] = 0;
		
		Node now;
		while(!pq.isEmpty()) {
			now = pq.poll();
			
			for(Node next : al.get(now.to)) {
				if(dis[next.to] > now.weight + next.weight) {
					dis[next.to] = now.weight + next.weight;
					pq.offer(new Node(next.to, dis[next.to]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dis = new int[N+1];
		Arrays.fill(dis, INF);
		al = new ArrayList<ArrayList<Node>>();
		for(int i = 0; i <= N; i++) {
			al.add(new ArrayList<>());
		}
		
		
		int from, to, weight, start, end;
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			al.get(from).add(new Node(to, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		dijkstra(start);
		System.out.println(dis[end]);
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
* 기본적인 다익스트라 문제이다.
* 주어지는 정점과 간선을 인접리스트에 저장을 시킨다.
* 그리고 주어진 시작점과 도착점을 사용해 다익스트라 알고리즘을 실행시킨 후 도착지의 최소 비용을 출력해준다.
