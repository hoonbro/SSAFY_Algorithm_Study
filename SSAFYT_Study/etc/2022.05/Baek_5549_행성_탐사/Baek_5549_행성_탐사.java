package etc._2022_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_5549_행성_탐사 {
	static int N, M, K;
	static int[][][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		map = new int[N+1][M+1][3];

		for (int i = 1; i <= N; i++) {
			String input = br.readLine();
			for (int j = 1; j <= M; j++) {
				int type = 0;
				if (input.charAt(j-1) == 'J') 
					type = 0;
				else if (input.charAt(j-1) == 'O')
					type = 1;
				else
					type = 2;
				
				for(int k = 0; k < 3; k++) {
					map[i][j][k] = map[i][j-1][k] + map[i-1][j][k] - map[i-1][j-1][k];
				}
				map[i][j][type]++;
			}
		}

		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			
			int jungle = map[x2][y2][0] + map[x1-1][y1-1][0] - map[x1-1][y2][0] - map[x2][y1-1][0];
			int ocean = map[x2][y2][1] + map[x1-1][y1-1][1] - map[x1-1][y2][1] - map[x2][y1-1][1];
			int ice = map[x2][y2][2] + map[x1-1][y1-1][2] - map[x1-1][y2][2] - map[x2][y1-1][2];
			
			sb.append(jungle).append(" ").append(ocean).append(" ").append(ice).append("\n"); 
		}
		System.out.println(sb.toString());
	}
}
