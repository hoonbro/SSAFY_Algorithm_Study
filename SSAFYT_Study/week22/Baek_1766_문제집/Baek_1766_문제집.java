package week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1766_문제집 {
	static int N, M;
	static int[] indegree;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	static ArrayList<ArrayList<Integer>> al;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		indegree = new int[N+1];
		al = new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0; i <= N; i++)
			al.add(new ArrayList<Integer>());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			al.get(from).add(to);
			indegree[to]++;
		}
		
		for(int i = 1; i<= N; i++) {
			if(indegree[i] == 0) 
				pq.offer(i);
		}
		
		int idx;
		while(!pq.isEmpty()) {
			idx = pq.poll();
			sb.append(idx).append(" ");
			
			for(int i : al.get(idx)) {
				indegree[i]--;
				
				if(indegree[i] == 0)
					pq.offer(i);
			}
		}
		
		System.out.println(sb.toString());
	}
}	
