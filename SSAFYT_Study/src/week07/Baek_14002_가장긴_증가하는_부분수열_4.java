package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baek_14002_가장긴_증가하는_부분수열_4 {
	static int N;
	static int[] arr;
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		dp = new int[1][N]; //parent;
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dp();
	}
	
	static void dp() {
		StringBuilder sb = new StringBuilder();
		//0 : 길이, 1 : 부모인덱스
		dp = new int[2][N];
		int max = 1; // 최대 길이
		int idx = 0; // 현재 인덱스
		
	
		for(int i = 0; i < N; i++) {
			dp[0][i] = 1;
			//dp배열의 0부터 i번째까지 확인한다.
			for(int j = 0; j < i; j++) {
				//arr[i]더 크고 더 긴 수열이라면 갱신
				if(arr[i] > arr[j] && dp[0][j] >= dp[0][i]) {
					dp[0][i] = dp[0][j] + 1; // 길이 갱신 
					dp[1][i] = j; //부모 인덱스 갱신
					if(max < dp[0][i]) {
						max = dp[0][i]; // 최대 길이 변경
						idx = i; // 최대길이 인덱스 저장
					}
				}
			}
		}
		sb.append(max).append("\n");
		Stack<Integer> st = new Stack<>(); 
		for(int i = 0; i < max; i++) {
			st.push(arr[idx]);
			idx = dp[1][idx];
		}
		
		while(!st.isEmpty()) {
			sb.append(st.pop()).append(" ");
		}
		System.out.println(sb.toString());
	}
}
