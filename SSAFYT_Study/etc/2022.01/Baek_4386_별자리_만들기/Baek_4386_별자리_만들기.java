package etc._2022_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_4386_별자리_만들기 {
	static int N;
	static int[] parent;
	static Star[] stars;
	static PriorityQueue<Star> pq = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		parent = new int[N];
		stars = new Star[N];
		
		for(int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			double a = Double.parseDouble(st.nextToken());
			double b = Double.parseDouble(st.nextToken());
			
			stars[i] = new Star(a,b);
			
			for(int j = 0; j < i; j++) {
				pq.offer(new Star(i, j, Math.sqrt(Math.pow(stars[i].x - stars[j].x, 2) + Math.pow(stars[i].y - stars[j].y, 2))));
			}
		}
		
		int cnt = 0;
		double ans = 0;
		while(!pq.isEmpty()) {
			Star now = pq.poll();
			
			if(find(now.from) != find(now.to)) {
				union(now.from, now.to);
				ans += now.distance;
				
				cnt++;
				
				if(cnt == N-1)
					break;
			}
		}
		
		System.out.printf("%.2f",ans);
	}
	
	static int find(int a) {
		if(a == parent[a])
			return a;
		
		return parent[a] = find(parent[a]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b)
			return;
		
		parent[b] = a;
	}
	
	static class Star implements Comparable<Star>{
		int from, to;
		double x, y, distance;
		public Star(int from, int to, double distance) {
			this.from = from;
			this.to = to;
			this.distance = distance;
		}

		public Star(double x, double y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Star o) {
			// TODO Auto-generated method stub
			return (int) (this.distance - o.distance);
		}
	}
}
