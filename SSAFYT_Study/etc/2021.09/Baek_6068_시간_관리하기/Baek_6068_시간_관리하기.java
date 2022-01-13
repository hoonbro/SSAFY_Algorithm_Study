package etc._2021_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_6068_시간_관리하기 {
	static int N;
	static PriorityQueue<Job> pq = new PriorityQueue<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			pq.offer(new Job(time, end));
		}
		Job endJob = pq.poll();
		int start = endJob.end - endJob.time;
		
		while(!pq.isEmpty()) {
			Job now = pq.poll();
			if(start < now.end)
				start -= now.time;
			else
				start = now.end-now.time;
		}
		
		if(start < 0)
			start = -1;
		
		System.out.println(start);
	}
	
	static class Job implements Comparable<Job>{
		int time;
		int end;
		
		public Job(int time, int end) {
			this.time = time;
			this.end = end;
		}

		@Override
		public int compareTo(Job o) {
			return o.end - this.end;
		}
	}
}
