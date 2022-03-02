package etc._2022_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2056_작업 {
	static int N, ans = 0;
	static int[] result, cost, indegree;
	static ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		result = new int[N+1];
		cost = new int[N+1];
		indegree = new int[N+1];
		
		for(int i = 0; i <= N; i++) {
			al.add(new ArrayList<>());
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			cost[i] = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < num; j++) {
				int prev = Integer.parseInt(st.nextToken());
				indegree[i]++;
				al.get(prev).add(i);
			}
		}
		
		topologicalSort();
		
		for(int i = 1; i <= N; i++)
			ans = Math.max(result[i], ans);
		
		System.out.println(ans);
	}

	static void topologicalSort() {
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 1; i <= N; i++) {
			result[i] = cost[i];
			if(indegree[i] != 0)
				continue;
			
			q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int next : al.get(now)) {
				result[next] = Math.max(result[next], result[now] + cost[next]);
				
				if(--indegree[next] == 0) {
					q.offer(next);
				}
			}
		}
	}
}
