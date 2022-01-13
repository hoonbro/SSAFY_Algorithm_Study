# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/10.svg" width="30"> [12852] 1로 만들기 2
> https://www.acmicpc.net/problem/12852
## 알고리즘 분류
> 그래프 탐색, DP

## 코드
```java
package etc._2021_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_12852_1로_만들기2 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		bfs();
		
	}
	
	static void bfs(){
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		String s = Integer.toString(N) + " ";
		q.offer(new Node(N, 0, new StringBuilder(s)));
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			
			if(visited[n.num])
				continue;
			
			visited[n.num] = true;
			
			if(n.num == 1) {
				System.out.println(n.cnt);
				System.out.println(n.sb.toString());
				break;
			}
			
			if(n.num % 3 == 0) {
				StringBuilder sb = new StringBuilder(n.sb);
				q.offer(new Node(n.num/3, n.cnt+1, sb.append(n.num/3).append(" ")));
			}
			if(n.num %2 == 0) {
				StringBuilder sb = new StringBuilder(n.sb);
				q.offer(new Node(n.num/2, n.cnt+1, sb.append(n.num/2).append(" ")));
			}
			StringBuilder sb = new StringBuilder(n.sb);
			q.offer(new Node(n.num-1, n.cnt + 1, sb.append(n.num-1).append(" ")));
		}
	}
	
	static class Node{
		int num, cnt;
		StringBuilder sb = new StringBuilder();

		public Node(int num, int cnt, StringBuilder sb) {
			this.num = num;
			this.cnt = cnt;
			this.sb = sb;
		}
	}
}
```

## 문제 풀이
1. 완전탐색(BFS)를 활용해 문제를 해결했다.
2. visited배열을 활용해서 불필요한 연산을 줄임.
