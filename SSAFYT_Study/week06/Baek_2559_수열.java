package week06;

import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Baek_2559_수열 {
	static int N, K, max = Integer.MIN_VALUE;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int sum = 0;
		for(int i = 0; i < K; i++) {
			sum += arr[i];
		}
		
		max = sum;
		for(int i = 0; i < N-K; i++) {
			sum-=arr[i];
			sum+= arr[K+i];
			max = Math.max(sum, max);
		}
		System.out.println(max);
	}
}
