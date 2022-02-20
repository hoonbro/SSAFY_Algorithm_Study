# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [1774] 우주신과의 교감
> https://www.acmicpc.net/problem/17837
## 알고리즘 분류
> 최소 스패닝 트리, 크루스칼

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1774_우주신과의_교감 {
	static int N, M;
	static int[] parent;
	static Pos[] arr;
	static PriorityQueue<Edge> pq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		arr = new Pos[N+1];
		pq = new PriorityQueue<>();
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			arr[i] = new Pos(x, y);
			parent[i] = i;
		}
		
		for(int i = 1; i <= N-1; i++) {
			for(int j = i+1; j <= N; j++) {
				double dx = arr[i].x - arr[j].x;
				double dy = arr[i].y - arr[j].y;
				double cost = Math.sqrt(dx*dx + dy*dy);
				
				pq.offer(new Edge(i, j, cost));
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			union(a, b);
		}
		
		int cnt = M;
		double ans = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if(union(e.from, e.to)) {
				ans += e.cost;
				
				if(++cnt == N-1) {
					break;
				}
			}
		}
		
		System.out.printf("%.2f",ans);
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
	
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int from, to;
		double cost;
		
		public Edge(int from, int to, double cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost > o.cost ? 1 : -1;
		}
	}
}
```

## 문제 풀이
1. 각 좌표/정점(Vertex) 끼리의 간선(Edge)을 구해 우선순위 큐에 넣는다.
2. 이미 연결되어 있는 M개의 간선들을 union 메서드를 사용해 연결한다.
3. 정점의 갯수 -1 번만큼의 반복문을 돌며 간선의 길이 가 가장 짧은 간선을 연결해주며 간선의 길이를 ans에 더해준다.
4. 반복문 종료 후 ans 출력.
