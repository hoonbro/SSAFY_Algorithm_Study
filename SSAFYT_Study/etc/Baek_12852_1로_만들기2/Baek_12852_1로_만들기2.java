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
