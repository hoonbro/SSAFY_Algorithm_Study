package etc._2021_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1956_운동 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int V, E, INF = 987654321;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		map = new int[V+1][V+1];
		
		for(int i = 1; i <= V; i++) {
			Arrays.fill(map[i], INF);
		}
		
		for(int i = 0 ; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			map[from][to] = cost;
		}
		
		floyd();
		
		int result = INF;
		for(int i = 1; i <= V; i++) {
			result = Math.min(map[i][i], result);
		}
		
	
		System.out.println(result == INF ? -1 : result);
	}
	
	static void floyd() {
		for(int k = 1; k <= V; k++) {
			for(int i = 1; i <= V; i++){
				for(int j = 1; j <= V; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
			
	}
}
