package etc._2022_01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bake_2623_음악_프로그램 {
	static int N, M;
	static int[] indegree;
	static Queue<Integer> q = new LinkedList<>();
	static ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		indegree = new int[N+1];
		
		for(int i = 0 ; i <= N; i++) {
			al.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			int prev = Integer.parseInt(st.nextToken());
			for(int j = 1; j < len; j++) {
				int singer = Integer.parseInt(st.nextToken());
				
				indegree[singer]++;
				al.get(prev).add(singer);
				prev = singer;
			}
		}
		
		
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}
		
		int cnt = 0;
		while(!q.isEmpty()) {
			int now = q.poll();
			sb.append(now).append("\n");
			cnt++;
			
			for(int next : al.get(now)) {
				indegree[next]--;
				
				if(indegree[next] == 0) {
					q.offer(next);
				}
			}
		}
		if(cnt == N) 
			bw.write(sb.toString());
		else
			bw.write(0);
		bw.flush();
	}
}
