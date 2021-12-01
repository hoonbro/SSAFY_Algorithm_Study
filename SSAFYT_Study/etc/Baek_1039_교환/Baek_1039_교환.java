package etc._2021_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_1039_교환 {
	static boolean[][] visited;
	static int N, K, ans = -1, len;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String s = st.nextToken();
		len = s.length();
		N = Integer.parseInt(s);
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[11][1000001];
		
		bfs();
		System.out.println(ans);
	}
	
	static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(N, 0));
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			if(visited[node.cnt][node.num])
				continue;
			
			visited[node.cnt][node.num] = true;
			
			if(node.cnt == K) {
				ans = Math.max(ans, node.num);
				continue;
			}else if(node.cnt > K)
				break;
			
			for(int i = len-1; i > 0; i--) {
				for(int j = i-1; j >= 0; j--) {
					//첫번째 자리수가 0이 될 경우 제외
					if(i == len-1 && node.num % Math.pow(10, j+1) == 0)
						continue;
					
					int num = swap(i, j, node.num);
					q.offer(new Node(num, node.cnt+1));
				}
			}
		}
	}
	
	static int swap(int i, int j, int num) {
		int a = (int) (num / Math.pow(10, i) % 10);
		int b = (int) (num / Math.pow(10, j) % 10);
		num -= a * Math.pow(10, i) + b * Math.pow(10, j);
		num += a * Math.pow(10, j) + b * Math.pow(10, i);
		return num;
	}
	
	static class Node{
		int num, cnt;

		public Node(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
}
