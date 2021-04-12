package week12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

//https://exponential-e.tistory.com/34
public class Baek_11437_LCA_dp {
	static int N, M, temp;
	static int[] depth;
	static int[][] parent;
	static boolean[] visited;
	static ArrayList<Integer>[] tree;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N+1];
		depth = new int[N+1];
		parent = new int[N+1][21];
		visited = new boolean[N+1];
		
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
		
		dfs(1,0);
		connection();
		
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
		//더 높은 노드를 b로 지정하기
		if(depth[a] > depth[b]) {
			temp = a;
			a = b;
			b = temp;
		}
		
		// 두 노드의 높이를 같게 해주자
		for(int i = 20; i >= 0; i--) {
			if(depth[b] - depth[a] >= (1<<i))
				b = parent[b][i];
		}
		
		if(a == b)
			return a;
		
		// 희소테이블을 사용해 부모 찾기
		for(int i = 20; i >= 0; i--) {
			if(parent[a][i] == parent[b][i])
				continue;
			
			a = parent[a][i];
			b = parent[b][i];
		}
			
		return parent[a][0];
	}
	
	static void connection() {
		for(int i = 1; i < 21; i++) {
			for(int now = 1; now <= N; now++) {
				parent[now][i] = parent[parent[now][i-1]][i-1];
			}
		}
	}
	
	static void dfs(int now, int d){
		visited[now] = true;
        depth[now] = d;
        
        for(int next : tree[now]) {
        	if(visited[next])
        		continue;
        	
        	parent[next][0] = now;
        	dfs(next, d+1);
        }
    }
}
