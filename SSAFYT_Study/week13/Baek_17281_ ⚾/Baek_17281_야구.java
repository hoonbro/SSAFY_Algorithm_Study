package week13;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Baek_17281_야구 {
	static int N, M, max = 0;
	static int[] hitter, base;
	static int[][] innings;
	static boolean[] select;

	static int playball() {
		int score = 0;
		int now = 1;
		int out;
		base = new int[5];
		for(int i = 0; i < N; i++) { // 이닝
			out = 0;
			
			while(out < 3) {
				if(innings[i][hitter[now]] == 0)
					out++;
				else {
					for (int j = 0; j < innings[i][hitter[now]]; j++) {
						base[4] += base[3];
						base[3] = base[2];
						base[2] = base[1];
						base[1] = 0;
					}
					base[innings[i][hitter[now]]]++;
				}
				
				now = now+1 == 10 ? 1 : now+1;
			}
			score += base[4];
			Arrays.fill(base, 0);
		}
		return score;
	}
	
	static void perm(int cnt) {
		if (cnt == 10) {
			max = Math.max(max, playball());
			return;
		}

		for (int i = 1; i <= 9; i++) {
			if (select[i])
				continue;

			select[i] = true;
			hitter[i] = cnt;
			perm(cnt + 1);
			select[i] = false;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = 10;
		innings = new int[N][M];
		select = new boolean[M];
		hitter = new int[M];
		hitter[4] = 1; // 첫번째 플레이어 4번타자
		select[4] = true;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < M; j++) {
				innings[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		perm(2);
		System.out.println(max);
	}
}