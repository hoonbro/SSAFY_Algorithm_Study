package etc._2022_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2571_색종이3 {
	static int N;
	static int[][] map = new int[101][101];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		for (int t = 0; t < N; t++) {
			st = new StringTokenizer(br.readLine());

			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			for (int i = x; i < x + 10; i++) {
				for (int j = y; j < y + 10; j++)
					map[i][j] = 1;
			}
		}

		// 현재 좌표에서 가장 긴 세로 길이 구하기
		for (int i = 1; i < 101; i++) {
			for (int j = 1; j < 101; j++) {
				if (map[i][j] == 0)
					continue;

				map[i][j] += map[i - 1][j];
			}
		}

		int ans = 0;
		for (int i = 1; i < 101; i++) {
			for (int j = 1; j < 101; j++) {
				int h = 100;
				int w = 1;

				for (int k = j; k < 101; k++) {
					if(map[i][k] == 0)
						break;
					
					h = Math.min(h, map[i][k]);
					
					ans = Math.max(ans, h*w++);
				}
			}
		}
		
		System.out.println(ans);
	}

}
