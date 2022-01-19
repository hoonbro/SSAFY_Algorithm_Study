package week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1915_가장_큰_정사각형 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int ans = 0;
		
		int[][] dp = new int[N+1][M+1];
		
		for(int i = 1; i <= N; i++) {
			String s = br.readLine();
			for(int j = 1; j <= M; j++) {
				int input = s.charAt(j-1) - '0';
				if(i == 1 && j == 1)
					dp[i][j] = input;
				//1이고 내 왼쪽위, 왼쪽, 위쪽이 모두 같을 경우
				else {
					if(input != 1)
						continue;
					dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
					ans = Math.max(dp[i][j], ans);
				}
			}
		}
		
		System.out.println(ans*ans);
	}
}
