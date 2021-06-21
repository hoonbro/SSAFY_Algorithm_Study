package week20;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_1005_ACM_Craft {
	static int T, N, K, W, start;
	static int[] time, indegree, result;
	static Queue<Integer> q = new LinkedList<>();
	static ArrayList<ArrayList<Integer>> al;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			time = new int[N + 1];
			indegree = new int[N + 1];
			result = new int[N+1];
			al = new ArrayList<ArrayList<Integer>>();

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				time[i] = Integer.parseInt(st.nextToken());
				result[i] = time[i];
			}

			for (int i = 0; i <= N; i++) {
				al.add(new ArrayList<>());
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				indegree[to]++;
				al.get(from).add(to);
			}

			W = Integer.parseInt(br.readLine());
				
			for (int i = 1; i <= N; i++) {
				if (indegree[i] == 0) {
					q.offer(i);
				}
			}

			while(!q.isEmpty()) {
				int idx = q.poll();
				
				for(int i : al.get(idx)) {
					result[i] = Math.max(result[i], time[i] + result[idx]);
					indegree[i]--;
					if(indegree[i] == 0) 
						q.offer(i);
				}
			}
			System.out.println(result[W]);
		}
	}
}
