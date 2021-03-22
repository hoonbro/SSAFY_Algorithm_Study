package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//순열 사이클
public class Baek_10451 {
	static int[] arr;
	static boolean visited[];
	static boolean isCycle[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			int answer = 0;
			int n = Integer.parseInt(br.readLine());
			arr = new int[n+1];
			visited = new boolean[n+1];
			
			String[] s = br.readLine().split(" ");
			for(int i = 1; i <= n; i++) {
				arr[i] = Integer.parseInt(s[i-1]);
			}
			for(int i = 1; i <= n; i++) {
				if(!visited[i]) {
					dfs(i);
					answer++;
				}
			}
			System.out.println(answer);
		}
	}
	
	static void dfs(int now){
		if(visited[now]) {
			return;
		}
		visited[now] = true;
		
		dfs(arr[now]);
	}
}
