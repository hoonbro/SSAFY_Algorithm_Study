package etc._2022_02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_13913_숨바꼭질4 {
	static int N, K, ans = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (N >= K) {
			sb.append(N - K).append("\n");
			for (int i = N; i >= K; i--) {
				sb.append(i).append(" ");
			}
		} else
			bfs();
		bw.write(sb.toString());
		bw.flush();
	}

	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		int[] visited = new int[100001];
		
		Arrays.fill(visited, -1);

		q.offer(K);
		visited[K] = Integer.MAX_VALUE;

		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			
			while(size-- > 0) {
				int now = q.poll();
				
				if (now == N) {
					sb.append(time).append("\n");
					
					int next = N;
					while(next != Integer.MAX_VALUE) {
						sb.append(next).append(" ");
						next = visited[next];
					}
					return;
				}

				if (now - 1 >= 0 && visited[now - 1] == -1) {
					visited[now - 1] = now;
					q.offer(now-1);
				}

				if (now + 1 <= 100000 && visited[now + 1] == -1) {
					visited[now + 1] = now;
					q.offer(now+1);
				}
				if (now %2 == 0 && visited[now / 2] == -1) {
					visited[now / 2] = now;
					q.offer(now/2);
				}
			}
			time++;
		}
	}
}
