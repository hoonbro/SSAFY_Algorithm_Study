package week12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_11437_LCA {
	static int N, M;
	static int[] depth, parent;
	static ArrayList<Integer>[] tree;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N+1];
		depth = new int[N+1];
		parent = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		int a, b;
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}
		dfs(1,1,0);

		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			sb.append(LCA(a,b)).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int LCA(int a, int b) {
		int aDepth = depth[a];
		int bDepth = depth[b];
		
		//a의 depth가 b의 depth보다 클경우 맞춰준다.
		while(aDepth > bDepth) {
			a = parent[a];
			aDepth--;
		}
		
		//b의 depth가 a의 depth보다 클경우 맞춰준다.
		while(bDepth > aDepth) {
			b = parent[b];
			bDepth--;
		}
		
		// depth가 같은데 부모가 다를경우
		while(a!=b) { 
			a = parent[a];
			b = parent[b];
		}
			
		return a;
	}
	
	static void dfs(int now, int d, int p){
        depth[now] = d;
        parent[now] = p;
        for(int i : tree[now]){
            if(i != p){
                dfs(i,d+1,now);
            }
        }
    }
}
