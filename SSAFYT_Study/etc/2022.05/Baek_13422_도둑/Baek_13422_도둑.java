package etc._2022_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_13422_도둑 {
	static int T, N, M, K, ans= 0;
	static int[] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			ans = 0;
			arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			count();
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void count() {
		int sum = 0;
		for(int i = 0; i < M; i++) {
			sum += arr[i];
		}
		
		if(sum < K)
			ans++;
		
		if(N == M)
			return;
		
		for(int i = 0, j = M; i < N-1; i++, j++) {
			if(j == N)
				j = 0;
			
			sum = sum - arr[i] + arr[j];
			if(sum < K)
				ans++;
		}
	}
}
