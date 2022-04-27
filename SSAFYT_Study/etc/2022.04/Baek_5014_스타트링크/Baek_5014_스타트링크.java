package etc._2022_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_5014_스타트링크 {
	static int F, S, G, U, D, ans = Integer.MAX_VALUE;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		visited = new boolean[F + 1];

		bfs();

		System.out.println(ans == Integer.MAX_VALUE ? "use the stairs" : ans);
	}

	static void bfs() {
		PriorityQueue<State> pq = new PriorityQueue<>();
		visited[S] = true;
		pq.offer(new State(S, 0));

		while (!pq.isEmpty()) {
			State s = pq.poll();

			if (s.now == G) {
				ans = s.cnt;
				break;
			}

			if (s.now - D > 0 && !visited[s.now - D]) {
				visited[s.now - D] = true;
				pq.offer(new State(s.now - D, s.cnt + 1));
			}

			if (s.now + U <= F && !visited[s.now + U]) {
				visited[s.now + U] = true;
				pq.offer(new State(s.now + U, s.cnt + 1));
			}
		}
	}

	static class State implements Comparable<State>{
		int now, cnt;

		public State(int now, int cnt) {
			this.now = now;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(State o) {
			return this.cnt - o.cnt;
		}
	}
}
