package week15.추가;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2846_오르막길 {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		int climb = 0;
		int max = 0;
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(i == 0) {
				continue;
			}
			
			if(arr[i] > arr[i-1]) {
				climb += arr[i]-arr[i-1];
				max = Math.max(climb, max);
			}
			else 
				climb = 0;
		}
		System.out.println(max);
	}

}
