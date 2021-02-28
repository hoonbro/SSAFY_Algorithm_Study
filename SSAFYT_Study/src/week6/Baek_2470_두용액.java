package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//이분 탐색
public class Baek_2470_두용액 {
	static int N, min = Integer.MAX_VALUE;
	static int[] arr, ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		ans = new int[2];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		binary();
		System.out.println(ans[0] +  " " + ans[1]);
	}
	
	static void binary() {
		int left = 0;
		int right = arr.length-1;
		
		while(left<right) {
			int sum = arr[left] + arr[right];
			
			if(Math.abs(sum) < min) {
				min = Math.abs(sum);
				ans[0] = arr[left];
				ans[1] = arr[right];
			}
			
			if(sum > 0) {
				right--;
			}
			else
				left++;
		}
	}
}
