# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [11780] 플로이드2
## 문제 링크
> https://www.acmicpc.net/problem/11780
## 알고리즘 분류
> DP

## 코드
```java
package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_11780_플로이드2 {
	static int N, M, INF = 123456789, cnt = 2;
	static int[][] map, path;
	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb2 = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		path = new int[N+1][N+1];
		for(int i = 1; i <= N; i ++) {
			for(int j = 1; j <= N; j++) {
				if(i == j)
					continue;
				map[i][j] = INF;
			}
		}
		
		int from, to, cost;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			map[from][to] = Math.min(cost, map[from][to]);
		}
		floyd();
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) {
					sb.append(0).append("\n");
					continue;
				}
				cnt = 2;
				findPath(i, j);
				sb.append(cnt).append(" ").append(i).append(" ").append(sb2.toString());
				sb.append(j).append(" \n");
				sb2.delete(0, sb2.length());
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static void findPath(int from, int to) {
		if(path[from][to] == 0)
			return;
		cnt++;
		findPath(from, path[from][to]);
		sb2.append(path[from][to]).append(" ");
		findPath(path[from][to], to);
	}
	
	static void floyd() {
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
						path[i][j] = k;
					}
				}
			}
		}
	}
}
```

## 문제 풀이
* 기본적으로 플로이드와샬 알고리즘을 사용해서 각 정점의 최단경로를 구해준다.
* 플로이드 와샬 알고리즘을 수행할때 2차원path 배열도 같이 초기화 해주며 경로를 저장해준다.
* path배열을 확인하며 각 경로를 출력해주면 끝난다.
