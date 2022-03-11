package etc._2022_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_2212_센서 {
	static int N, K, ans = 0;
	static int[] arr, len;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		len = new int[N-1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for(int i = 0; i < N-1; i++) {
			len[i] = arr[i+1] - arr[i]; 
		}
		
		Arrays.sort(len);
		
		for(int i = 0; i < N-K; i++) {
			ans += len[i];
		}

		System.out.println(ans);
	}
}

