package week13.추가;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_16134_조합 {
	static long fac[];

	static long power(long x, long y, long p) {
		long ans = 1;

		while (y > 0) {
			if (y % 2 == 1)
				ans = (ans * x) % p;

			y = y >> 1;
			x = (x * x) % p;
		}
		return ans;
	}

	static long nCr(int n, int r, long p) {
		if (r == 0)
			return 1L;

		return (fac[n] * power(fac[r], p - 2, p) % p * power(fac[n - r], p - 2, p) % p) % p;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, R;
		long P = 1000000007L;
		fac = new long[1000001];
		fac[0] = 1;

		for (int i = 1; i <= 1000000; i++) {
			fac[i] = fac[i - 1] * i % P;
		}
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		long ans = nCr(N, R, P);
		System.out.println(ans);
	}
}
