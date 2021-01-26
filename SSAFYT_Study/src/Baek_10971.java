import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//외판원 순회2
public class Baek_10971 {
	static int[][] map;
	static boolean[] visited;
	static int n;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			dfs(i, i, map[i][i], 0);
		}
		System.out.println(ans);

	}
	
	//dfs사용
	static void dfs(int start, int y, int cost, int cnt) {
		if (cnt == n && start == y) {
			ans = Math.min(ans, cost);
			return;
		}

		for (int j = 0; j < n; j++) {
			if (!visited[j] && map[y][j] != 0) {
				visited[j] = true;
				dfs(start, j, cost + map[y][j], cnt + 1);
				visited[j] = false;
			}
		}
	}

}
