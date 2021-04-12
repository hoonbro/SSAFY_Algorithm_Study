# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [11437] LCA
## 문제 링크
> https://www.acmicpc.net/problem/11437
## 알고리즘 분류
> 요세푸스 문제

## 코드
```java
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
```

## 문제 풀이
* LCA를 직관적으로 푼 코드이다. O(mlogn)의 시간복잡도
* 우선, 1차원 parent배열과 depth배열을 dfs 메서드를 통해서 초기화해준다.
* 그리고 LCA를 진행하는데 두 노드의 depth를 맞춰준 이후에 부모가 같을때까지 각 노드의 parent를 확인해준다.