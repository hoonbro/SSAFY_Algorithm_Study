import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_2186_문자판{
	static int N, M, K, len, ans = 0;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static int[][][] dp;
	static char[][] map;
	static char[] target;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		target = br.readLine().toCharArray();
		len = target.length;
		dp = new int[N][M][len];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}

		char start = target[0];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != start)
					continue;

				ans += dfs(0, i, j);
			}
		}
		
		System.out.println(ans);
	}

	static int dfs(int cnt, int x, int y) {
		if (cnt == len-1) {
			return dp[x][y][cnt] = 1;
		}
		
		if(dp[x][y][cnt] != -1) {
			return dp[x][y][cnt];
		}
		
		dp[x][y][cnt] = 0;
		
		for (int k = 1; k <= K; k++) {
			for (int d = 0; d < 4; d++) {
				int nx = x + X[d] * k;
				int ny = y + Y[d] * k;

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;

				if (map[nx][ny] != target[cnt+1])
					continue;

				dp[x][y][cnt] += dfs(cnt + 1, nx, ny);
			}
		}
		
		return dp[x][y][cnt];
	}
}