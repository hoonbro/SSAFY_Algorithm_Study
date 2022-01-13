package etc._2021_12;

import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Baek_2866_문자열_잘라내기 {
	static int N, M, ans;
	static char[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		binarySearch(); 
		System.out.println(ans);
	}
	
	static void binarySearch() {
		int left = 0;
		int right = N-1;
		int mid = 0;
		
		while(left <= right) {
			mid = (left + right)/2;
			
			if(check(mid, N)) {
				ans = mid;
				left = mid +1;
			}else
				right = mid -1;
		}
		
	}
	
	static boolean check(int start, int end) {
		Stack<String> stack = new Stack<>();

		for(int i = 0; i < M; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j = start; j < end; j++) {
				sb.append(arr[j][i]);
			}
			
			if(stack.contains(sb.toString()))
				return false;
			else
				stack.add(sb.toString());
		}
		return true;
	}
}
