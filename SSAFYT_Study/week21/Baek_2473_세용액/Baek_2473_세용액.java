package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_2473_세용액 {
	static int N;
	static long max = 3000000000L;
	static long[] arr, ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new long[N];
		ans = new long[3];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for(int i = 0; i < N-2; i++) {
			binary(i);
		}
		Arrays.sort(ans);
		for(long i : ans) {
			System.out.print(i + " ");
		}
	}
	
	static void binary(int idx) {
		int left = idx+1;
		int right = N-1;
		long sum = 0;
		while(left < right) {
			sum = arr[left] + arr[right] + arr[idx];
			if(Math.abs(sum) < max) {
				ans[0] = arr[idx];
				ans[1] = arr[left];
				ans[2] = arr[right];
				max = Math.abs(sum);
			}
			
			if(sum > 0)
				right--;
			else
				left++;
		}
	}
}
