package etc._2021_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_16938_캠프_준비 {
	static int N, L, R, X, ans = 0;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		dfs(0,0,0,Integer.MAX_VALUE, 0, 0);
		System.out.println(ans);
	}
	
	static void dfs(int idx, int cnt, int sum, int min, int max, int dif) {
		if(cnt >= 2 && sum>= L && sum <= R && max - min >= X) {
			ans++;
		}
		
		for(int i = idx; i < N; i++) {
			int a = Math.min(min, arr[i]);
			int b = Math.max(max, arr[i]);
			dfs(i+1, cnt+1, sum+arr[i], a, b, b-a);
		}
	}
}
