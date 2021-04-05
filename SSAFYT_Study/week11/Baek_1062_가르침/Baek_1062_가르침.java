package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1062_가르침 {
	static int N, K, ans = 0;
	static String[] teach;
	static boolean[] alpha;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		teach = new String[N];
		alpha = new boolean[26];

		alpha['a'-'a'] = true;
		alpha['n'-'a'] = true;
		alpha['t'-'a'] = true;
		alpha['i'-'a'] = true;
		alpha['c'-'a'] = true;
		
		for (int i = 0; i < N; i++) {
			teach[i] = br.readLine();
			teach[i] = teach[i].substring(4, teach[i].length() - 4);
		}
		if (K < 5)
			System.out.println(0);
		else if (K == 26)
			System.out.println(N);
		else {
			K-=5;
			combi(0, 0);
			System.out.println(ans);
		}
	}

	static void combi(int idx, int cnt) {
		if (cnt == K) {
			int total = 0;
			for (int i = 0; i < N; i++) {
				boolean flag = false;
				for (int j = 0; j < teach[i].length(); j++) {
					if (!alpha[teach[i].charAt(j)- 'a'] ) {
						flag = true;
						break;
					}
				}
				if (!flag)
					total++;
			}
			ans = Math.max(total, ans);
			return;
		}

		for (int i = idx; i < 26; i++) {
			if (alpha[i])
				continue;

			alpha[i] = true;
			combi(i + 1, cnt + 1);
			alpha[i] = false;
		}
	}
}
