package week7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_1707_이분그래프 {
	static int V, E;
	static Queue<Integer> q;
	static ArrayList<ArrayList<Integer>> al;
	static int[] color;
	static StringBuilder sb;

	static boolean bfs(int idx) {
		q.offer(idx);
		color[idx] = 1; // 첫번째친구 색 초기화
		
		while(!q.isEmpty()) {
			int v = q.poll();
			for(int i = 0; i < al.get(v).size(); i++) {
				if(color[v] == color[al.get(v).get(i)]) {
					return false;
				}
				if(color[al.get(v).get(i)] == 0) {
					color[al.get(v).get(i)] = color[v] * -1;
					q.offer(al.get(v).get(i));
					continue;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		loop:
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); //정점
			E = Integer.parseInt(st.nextToken()); //간선
			al = new ArrayList<ArrayList<Integer>>();
			q = new LinkedList<>();
			color = new int[V+1];
			
			for(int i = 0; i <= V; i++) {
				al.add(new ArrayList<Integer>());
				color[i] = 0;
			}

			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				al.get(a).add(b);
				al.get(b).add(a);
			}
			
			//모든 정점에서 시작할 경우를 고려해야댐
			for(int i = 1; i <= V; i++) {
				if(color[i] == 0)
					if(!bfs(i)) {
						sb.append("NO").append("\n");
						continue loop;
					}
				
			}
			sb.append("YES").append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}

