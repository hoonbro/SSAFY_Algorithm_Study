package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_14719_빗물 {
	static int H, W, ans = 0;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		arr = new int[W];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < W; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int leftMax, rightMax;
		for(int i = 1; i < W-1; i++) {
			leftMax = rightMax = 0;
			
			for(int j = 0; j < i; j++) {
				leftMax = Math.max(leftMax, arr[j]);
			}
			
			for(int j = i+1; j < W; j++) {
				rightMax = Math.max(rightMax, arr[j]);
			}
			
			if(Math.min(leftMax, rightMax) > arr[i]) 
				ans += Math.min(leftMax, rightMax)-arr[i];
		}
		System.out.println(ans);
	}
}
