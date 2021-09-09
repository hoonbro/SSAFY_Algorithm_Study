package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1477_휴게소_세우기 {
	static int N, M, L;
	static int[] rest;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		rest = new int[N+2];

		st = new StringTokenizer(br.readLine());
		
		rest[0] = 0;
		rest[N+1] = L;
		for (int i = 1; i <= N; i++) {
			rest[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(rest);
		
		System.out.println(binary());
	}

	static int binary() {
		int left = 0, right = L;
		
		while(left <= right) {
			int mid = (right + left)/2;
			int sum = 0;
			
			for(int i = 1; i <= N+1; i++) {
				sum += (rest[i] - rest[i-1] - 1)/mid;
			}
			if(sum > M)
				left = mid + 1;
			else
				right = mid - 1;
		}
		
		return left;
	}
}
