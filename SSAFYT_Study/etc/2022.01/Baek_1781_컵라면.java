package etc._2022_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1781_컵라면 {
	static int N, ans = 0;
	static int[] arr; 
	static PriorityQueue<Ramen> pq = new PriorityQueue<>();
	static PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			pq.offer(new Ramen(d, c));
		}
		arr = new int[200001];
		
		
		int time = 1;
		while(!pq.isEmpty()) {
			Ramen now = pq.poll();
			
			if(arr[now.deadLine] == 0) {
				arr[time] = now.cnt;
				minHeap.offer(new int[]{time++, now.cnt});
			}
			else {
				if(now.cnt > minHeap.peek()[1]) {
					int[] temp = minHeap.poll();
					
					arr[temp[0]] = now.cnt;
					minHeap.offer(new int[]{temp[0], now.cnt});
				}
			}
		}
		
		for(int i = 1; i <= arr.length; i++) {
			if(arr[i] == 0)
				break;
			ans += arr[i];
		}
		
		System.out.println(ans);
	}
	
	
	static class Ramen implements Comparable<Ramen>{
		int deadLine, cnt;
		
		public Ramen(int deadLine, int cnt) {
			this.deadLine = deadLine;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Ramen o) {
			return this.deadLine == o.deadLine ? o.cnt - this.cnt : this.deadLine - o.deadLine;
		}
	}
}
