package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_1300_K번째수 {
	static long N, K;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Long.parseLong(br.readLine());
		K = Long.parseLong(br.readLine());
		
		long left = 1, right = K, mid = 0, ans = 0;
		int cnt = 0;
		while(left <= right) {
			cnt = 0;
			mid = (left+right)/2;
			
			for(int i = 1; i <= N; i++) {
				cnt += Math.min(mid/i, N);
			}
			
			if(cnt < K) {
				left = mid + 1;
			}else {
				ans = mid;
				right = mid-1;
			}
		}
		System.out.println(ans);
	}
}
