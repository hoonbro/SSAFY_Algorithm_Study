import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_10423_전기가_부족해{
	static int N, M, K;
	static int[] parent;
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		pq = new PriorityQueue<>();

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			parent[Integer.parseInt(st.nextToken())] = 0;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			pq.offer(new Node(from, to, cost));
		}

		int ans = 0;
		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if(parent[now.from] == 0 && parent[now.to] == 0)
				continue;
			
			if (find(now.from) != find(now.to)) {
				ans += now.cost;
				
				union(now.from, now.to);
				
				if(check())
					break;
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean check() {
		for(int i = 1; i <= N; i++) {
			if(parent[i] != 0)
				return false;
		}
		
		return true;
	}

	static int find(int a) {
		if (a == parent[a])
			return a;

		return parent[a] = find(parent[a]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b)
			return ;

		if(parent[b] == 0) {
			parent[a] = b;
		}else {
			parent[b] = a;
		}
	}

	static class Node implements Comparable<Node> {
		int from, to, cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}