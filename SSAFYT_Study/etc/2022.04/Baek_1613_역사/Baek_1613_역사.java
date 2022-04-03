package etc._2022_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1613_역사 {
	static int N, K, S, INF = 987654321;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			Arrays.fill(arr[i], INF);
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = 1;
		}
		
		floyd();
		
		S = Integer.parseInt(br.readLine());
		for(int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int result = 0;
			if(arr[a][b] != INF)
				result = -1;
			else if(arr[b][a] != INF)
				result = 1;
			
			sb.append(result).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void floyd() {
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					arr[i][j] = Math.min(arr[i][j], (arr[i][k] + arr[k][j]));
				}
			}
		}
	}
}
