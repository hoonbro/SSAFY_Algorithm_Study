package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_7579_앱 {
	static int N, M, ans = 0;
	static int[] dp;
	static App[] apps;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringTokenizer st2;
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		apps = new App[N];
		dp = new int[10001];
		
		st = new StringTokenizer(br.readLine());
		st2 = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int act = Integer.parseInt(st.nextToken());
			int unAct = Integer.parseInt(st2.nextToken());
			apps[i] = new App(act, unAct);
			
			for(int j = 10000; j - apps[i].unAct>= 0; j--) {
				dp[j] = Math.max(dp[j], dp[j-apps[i].unAct] + apps[i].act);
			}
			
		}
		
		for(int i = 0; i <= 10000; i++) {
			if(dp[i] >= M) {
				ans = i;
				break;
			}
		}
		System.out.println(ans);
	}

	static class App {
		int act, unAct;

		public App(int act, int unAct) {
			this.act = act;
			this.unAct = unAct;
		}
	}
}
