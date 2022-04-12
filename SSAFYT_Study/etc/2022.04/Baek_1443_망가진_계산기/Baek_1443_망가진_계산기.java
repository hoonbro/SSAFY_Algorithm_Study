package etc._2022_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Baek_1443_망가진_계산기 {
	static int D, P;
	static long ans, max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		D = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		ans = 0L;
		max = (long) (Math.pow(10, D) - 1);

		dfs(1, 9, 0);
		System.out.println(ans == 0 ? -1 : ans);
	}

	static void dfs(long result, int num, int cnt) {
		if (result > max)
			return;
		
		if (cnt == P) {
			ans = Math.max(result, ans);
			return;
		}

		for (int i = num; i >= 2; i--) {
			dfs(result * i, i, cnt + 1);
		}
	}
}
