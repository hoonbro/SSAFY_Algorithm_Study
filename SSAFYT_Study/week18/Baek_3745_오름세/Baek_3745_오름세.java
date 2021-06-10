package week18;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baek_3745_오름세 {
	static int N;
	static int[] arr, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		String s = "";
		while((s = br.readLine()) != null) {
			N = Integer.parseInt(s.trim());
			arr = new int[N];
			dp = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			dp[0] = arr[0];
			int idx = 0;
			int j = 0;
			for(int i = 1; i < N; i++) {
				if(dp[idx] < arr[i]) {
					dp[++idx] = arr[i];
				}else {
					j = bs(idx, arr[i]);
					dp[j] = arr[i];
				}
			}
			sb.append(idx+1).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	static int bs(int end, int key) {
		int left = 0;
		int right = end;
		
		while(left < right) {
			int mid = (left+right)/2;
			
			if(dp[mid] < key)
				left = mid+1;
			else
				right = mid;
		}
		
		return right;
	}
}
