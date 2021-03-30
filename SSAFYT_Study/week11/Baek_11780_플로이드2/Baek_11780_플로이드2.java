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
