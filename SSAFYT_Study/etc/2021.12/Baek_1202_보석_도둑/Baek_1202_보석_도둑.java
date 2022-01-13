package etc._2021_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1202_보석_도둑 {
	static int N, K;
	static int[] bags;
	static Jewel[] jewels;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		jewels = new Jewel[N];
		bags = new int[K];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			jewels[i] = new Jewel(m, v);
		}
		Arrays.sort(jewels);
		
		for(int i = 0; i < K; i++) {
			int weight = Integer.parseInt(br.readLine());
			bags[i] = weight;
		}
		Arrays.sort(bags);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		long ans = 0;
		int jewelIdx = 0;
		for(int i = 0; i < K; i++) {
			while(jewelIdx < N && jewels[jewelIdx].weight <= bags[i]) {
				pq.offer(jewels[jewelIdx++].value);
			}
			
			if(!pq.isEmpty()) {
				ans += pq.poll();
			}
		}
		System.out.println(ans);
	}
	
	static class Jewel implements Comparable<Jewel>{
		int weight, value;
		
		public Jewel(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		@Override
		public int compareTo(Jewel o) {
			return this.weight - o.weight;
		}
	}
}
