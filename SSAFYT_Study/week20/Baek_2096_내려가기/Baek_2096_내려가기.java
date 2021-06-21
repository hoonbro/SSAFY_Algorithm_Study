package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2096_내려가기 {
	static int N, max = 0, min = Integer.MAX_VALUE;
	static int[] maxDp, minDp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		maxDp = new int[3];
		minDp = new int[3];
		
		int a, b, c, t0, t1, t2;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(i == 0) {
				maxDp[0] = minDp[0] = a;
				maxDp[1] = minDp[1] = b;
				maxDp[2] = minDp[2] = c;
				continue;
			}
			t0 = maxDp[0]; t1 = maxDp[1]; t2 = maxDp[2];
			maxDp[0] = a + Math.max(t0, t1);
			maxDp[2] = c + Math.max(t2, t1);
			maxDp[1] = b + Math.max(Math.max(t0, t1), t2);
			
			t0 = minDp[0]; t1 = minDp[1]; t2 = minDp[2];
			minDp[0] = a + Math.min(t0, t1);
			minDp[2] = c + Math.min(t2, t1);
			minDp[1] = b + Math.min(Math.min(t0, t1), t2);
		}
		
		max = Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]);
		min = Math.min(Math.min(minDp[0], minDp[1]), minDp[2]);
		System.out.println(max + " " + min);
	}
}
