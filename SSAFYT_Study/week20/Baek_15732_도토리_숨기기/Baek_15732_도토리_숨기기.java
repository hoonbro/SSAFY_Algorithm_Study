package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_15732_도토리_숨기기 {
	static int N, K, D;
	static Box[] boxes;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		boxes = new Box[K];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int gap = Integer.parseInt(st.nextToken());
			
			boxes[i] = new Box(start, end, gap);
		}
		
		int left = 0;
		int right = N;
		int mid;
		long cnt;
		while(left <= right) {
			mid = (left+right) / 2;
			
			cnt = 0;
			for(Box b : boxes) {
				if(b.end <= mid)
					cnt += (b.end - b.start)/ b.gap + 1;
				
				else if(b.start > mid)
					continue;
				
				else 
					cnt += (mid - b.start) == 0 ? 1 : (mid - b.start)/b.gap + 1;
			}
			
			if(cnt >= D)
				right = mid - 1;
			else
				left = mid + 1;
		}
		System.out.println(left);
	}
	
	static class Box{
		int start, end, gap;

		public Box(int start, int end, int gap) {
			this.start = start;
			this.end = end;
			this.gap = gap;
		}
	}
}
