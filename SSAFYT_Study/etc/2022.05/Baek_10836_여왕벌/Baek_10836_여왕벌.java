import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[2*M-1];
		map = new int[M][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int idx = 0;
			for(int j = 0; j < 3; j++) {
				int n = Integer.parseInt(st.nextToken());
				
				for(int k = 0; k < n; k++) {
					arr[idx++] += j;
				}
			}
		}
		
		grow();
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(map[i][j]+1).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void grow() {
		int idx = 0;
		// 가장 왼쪽열
		for(int i = M-1; i >= 0; i--) {
			map[i][0] += arr[idx++];
		}
		
		// 가장 위쪽행
		for(int i = 1; i < M; i++) {
			map[0][i] += arr[idx++];
		}
		
		for(int i = 1; i < M; i++) {
			for(int j = 1; j < M; j++) {
				map[i][j] = Math.max(map[i-1][j-1], Math.max(map[i-1][j], map[i][j-1]));
			}
		}
	}
}