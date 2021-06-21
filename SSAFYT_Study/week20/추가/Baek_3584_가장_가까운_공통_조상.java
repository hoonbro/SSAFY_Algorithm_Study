package week20.추가;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_3584_가장_가까운_공통_조상 {
	static int T, N;
	static int[] depth, parent;
	static boolean[] isRoot;
	static ArrayList<Integer>[] tree;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			N = Integer.parseInt(br.readLine());
			depth = new int[N+1];
			parent = new int[N+1];
			isRoot = new boolean[N+1];
			tree = new ArrayList[N+1];
			
			for(int i = 0; i <= N; i++) {
				tree[i] = new ArrayList<>();
			}
			
			int a, b;
			for(int i = 0; i < N-1; i++) {
				st = new StringTokenizer(br.readLine());
				
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				tree[a].add(b);
				tree[b].add(a);
				isRoot[b] = true;
			}
			
			for(int i = 1; i <= N; i++) {
				if(isRoot[i])
					continue;
				
				dfs(i, 1, 0);
				break;
			}
			
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			sb.append(LCA(A, B)).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	static int LCA(int a, int b) {
		int aDepth = depth[a];
		int bDepth = depth[b];
		
		while(aDepth > bDepth) {
			a = parent[a];
			aDepth--;
		}
		
		while(bDepth > aDepth) {
			b = parent[b];
			bDepth--;
		}
		
		while(a!=b) {
			a = parent[a];
			b = parent[b];
		}
		
		return a;
	}

	static void dfs(int now, int d, int p) {
		depth[now] = d;
		parent[now] = p;
		
		for(int i : tree[now]) {
			if(i != p) 
				dfs(i, d+1, now);
		}
	}
}
