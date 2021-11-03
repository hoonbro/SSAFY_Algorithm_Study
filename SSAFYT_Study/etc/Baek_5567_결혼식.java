package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_5567_결혼식 {
	static int N, M, answer = 0;
	static boolean[] visited, isFriend;
	static ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		isFriend = new boolean[N+1];
		visited[1] = true;

		for (int i = 0; i <= N; i++) {
			al.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(a == 1 || b == 1) {
				isFriend[a] = true;
				isFriend[b] = true;
			}
			
			al.get(a).add(b);
			al.get(b).add(a);
		}
		dfs(1);
		System.out.println(answer);
	}
	
	static void dfs(int now) {
		for(int n : al.get(now)) {
			if(visited[n] || !isFriend[now])
				continue;
			visited[n] = true;
			answer++;
			dfs(n);
		}
	}
}
