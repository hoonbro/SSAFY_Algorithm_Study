package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//숨바꼭질 2
//Bfs(dfs실패)
public class Baek_12851 {
	static int n, k;
	static Queue<Integer> q;
	static boolean[] visited;
	static int minTime, minCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		q = new LinkedList<>();
		visited = new boolean[100001];
		q.offer(n);
		visited[n] = true;

		
		if (n == k) {
			System.out.println(0);
			System.out.println(1);
			return;
		}

		bfs();
		System.out.println(minTime);
		System.out.println(minCnt);
	}
	
	static void bfs() {
		minTime = 0;
		minCnt = 0;
		int[] next;
		
		//같은 시간대에 같은곳을 방문하면 괜찮지만 다른시간대에 들렸던 곳을 방문하면 볼 필요 없음
		while(!q.isEmpty()) {
			int n = q.size();
			minTime++;
			
			for(int i = 0; i < n; i++) {
				int now = q.poll();
				visited[now] = true;
				
				//next배열을 만들어서 사용
				next = new int[]{now -1, now +1, now *2};
				for(int j = 0; j < 3; j++) {
					//next가 0보다 작거나 100000이상이거나 갔던곳이라면 continue
					if(next[j] < 0 || next[j] > 100000 || visited[next[j]]) 
						continue;
					
					//다음이 k라면 방법의 수를 늘린다. 
					if(next[j] == k) {
						minCnt++;
						continue;
					}
					q.offer(next[j]);
				}
			}
			//최소시간이 나왔다면 더이상 실행할 필요 없음
			if(minCnt > 0)
				q.clear();
		}
	}
}

