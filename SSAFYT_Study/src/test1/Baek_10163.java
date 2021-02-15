package test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//색종이
public class Baek_10163 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[101][101];

		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			for (int i = a; i < a+x; i++) {
				for (int j = b; j < b+y; j++) {
					map[i][j] = n;
				}
			}
		}
		
		for (int n = 1; n <= N; n++) {
			int cnt = 0;
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					if(map[i][j] == n) {
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}
}
