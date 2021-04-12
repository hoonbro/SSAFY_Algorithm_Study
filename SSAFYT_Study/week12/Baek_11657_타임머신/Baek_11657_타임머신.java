package week12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_11657_타임머신 {
	static int N, M, INF = 987654321;
	static long[] dis;
	static ArrayList<ArrayList<Node>> al;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dis = new long[N+1];
		al = new ArrayList<ArrayList<Node>>();
		for(int i = 0; i <= N; i++) {
			al.add(new ArrayList<>());
		}
		
		int a,b,c;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			al.get(a).add(new Node(b,c));
		}
		
		bellmanFord();
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void bellmanFord() {
		Arrays.fill(dis, INF);
		dis[1] = 0;
		boolean flag = false;
		for(int i = 1; i < N; i++) { // N-1만큼 반복
			flag = false;
			for(int j = 1; j <= N; j++) {
				
				for(Node n : al.get(j)) {
					if(dis[j] == INF)
						break;
					if (dis[n.to] > dis[j] + n.weight) {
						dis[n.to] = dis[j] + n.weight;
						flag = true;
					}
				}
			}
			if(!flag) {
				break;
			}
		}
		
		if(flag) {
			for(int j = 1; j <= N; j++) {
				for(Node n : al.get(j)) {
					if(dis[j] == INF)
						break;
					if(dis[n.to] > dis[j] + n.weight) {
						sb.append(-1).append("\n");
						return;
					}
				}
			}
		}
		
		for(int i = 2; i <= N; i++) {
			if(dis[i] == INF)
				sb.append(-1).append("\n");
			else
				sb.append(dis[i]).append("\n");
		}
	}
	
	static class Node{
		int to, weight;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
}


