package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1806_부분합 {
	static int N;
	static Long S;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Long.parseLong(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int sum = 0 ;
		int min = 123456789;
		while(true) {
			if(sum >= S) {
				sum -= arr[start++];
				min = Math.min(min, (end-start+1));
			}else if(end == N)
				break;
			else {
				sum += arr[end++];
			}
		}
		if(min == 123456789)
			min = 0;
		System.out.println(min);
	}
}
