package etc._2022_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_3079_입국심사 {
	static long N, M;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Long.parseLong(st.nextToken());
		M = Long.parseLong(st.nextToken());
		
		arr = new int[(int) N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		System.out.println(binarySearch());
	}

	static long binarySearch() {
		long left = 0;
		long right = arr[(int)N-1] * M;
		long mid, sum;
		
		
		while(left <= right) {
			mid = (left + right) / 2;
			
			sum = 0;
			for(int n : arr) {
				sum += mid/n;
			}
			
			if(sum >= M) 
				right = mid -1;
			else if(sum < M)
				left = mid + 1;
			else
				break;
		}
		
		return left;
	}
}
