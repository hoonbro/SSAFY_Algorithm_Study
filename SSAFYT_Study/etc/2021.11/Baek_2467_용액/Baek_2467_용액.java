package etc._2021_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_2467_용액 {
	static int N, a, b, min = Integer.MAX_VALUE;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		binary();
		System.out.println(a + " " + b);
	}	
	
	static void binary() {
		int left = 0;
		int right = N-1;
		
		while(left < right) {
			int sum = arr[left] + arr[right];
			
			if(Math.abs(sum) < min) {
				min = Math.abs(sum);
				a = arr[left];
				b = arr[right];
			}
			
			if(sum > 0) {
				right--;
			}else {
				left++;
			}
		}
	}
}
