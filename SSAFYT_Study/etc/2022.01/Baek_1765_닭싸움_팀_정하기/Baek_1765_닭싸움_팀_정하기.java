package etc._2022_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_1765_닭싸움_팀_정하기 {
	static int N, M;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> enemies = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		
		for(int i = 0; i <= N; i++) {
			al.add(new ArrayList<>());
			enemies.add(new ArrayList<>());
		}
		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			char type = st.nextToken().charAt(0);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(type == 'F') {
				al.get(a).add(b);
				al.get(b).add(a);
			}else {
				enemies.get(a).add(b);
				enemies.get(b).add(a);
			}
		}
		
		for(int i = 1; i <= N; i++) {
			visited = new boolean[N+1];
			dfs(i, i, 0);
		}
		
		visited = new boolean[N+1];
		int cnt = 0;
		for(int i = 1; i <= N; i++) {
			if(visited[i])
				continue;
			
			cnt++;
			makeTeam(i);
		}
		
		System.out.println(cnt);
	}
	
	static void makeTeam(int idx) {
		if(visited[idx])
			return;
		
		visited[idx] = true;
		for(int friend : al.get(idx)) {
			makeTeam(friend);
		}
	}
	
	static void dfs(int start, int idx, int depth) {
		if(visited[idx])
			return;
		
		if(depth == 2) {
			al.get(start).add(idx);
			return;
		}
		
		visited[idx] = true;
		for(int enemy : enemies.get(idx)) {
			dfs(start, enemy, depth+1);
		}
	}
}
