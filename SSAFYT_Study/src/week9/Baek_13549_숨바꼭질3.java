package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_13549_숨바꼭질3 {
	static int X, K, minTime = 0;;
	static int[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visited = new int[100001];
		Arrays.fill(visited, Integer.MAX_VALUE);
		bfs();
		System.out.println(minTime);
	}
	
	static void bfs() {
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.offer(new Node(X, 0));
		visited[X] = 0;
		int[] next;
		
		Node now;
		while(!q.isEmpty()) {
			now = q.poll();
			
			if(now.pos == K) {
				minTime = now.time;
				return;
			}
			
			next = new int[]{now.pos-1, now.pos+1, now.pos*2};
			
			for(int i = 0; i < 3; i++) {
				if(next[i] < 0 || next[i] > 100000 || visited[next[i]] < now.time + 1)
					continue;
				
				if(i == 2) {
					visited[next[i]] = now.time;
					q.offer(new Node(next[i], now.time));
				}
				else
					visited[next[i]] = now.time + 1;
					q.offer(new Node(next[i], now.time+1));
			}
		}
	}
	
	static class Node implements Comparable<Node>{
		int pos;
		int time;
		Node(int pos, int time){
			this.pos = pos;
			this.time = time;
		}
		@Override
		public int compareTo(Node o) {
			return this.time - o.time;
		}
	}
}
