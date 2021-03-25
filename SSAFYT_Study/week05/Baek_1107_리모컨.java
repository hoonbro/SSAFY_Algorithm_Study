package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1107_리모컨 {
	static int N, M;
	static boolean[] broken;
	static int min = 987654321;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		broken = new boolean[10];

		
		if (M != 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			while (M-- > 0) {
				broken[Integer.parseInt(st.nextToken())] = true;
			}
		}

		if (N == 100) {
			System.out.println(0);
			return;
		}

		min = Math.min(Math.abs(100 - N), min);
		for (int i = 0; i < 1000000; i++) {
			int len = search(i);
			if (len != 0) {
				len += Math.abs(N - i);
				min = Math.min(min, len);
			}
		}
		System.out.println(min);
	}

	static int search(int n) {
		if (n == 0)
			if (broken[0])
				return 0;
			else
				return 1;

		int len = 0;
		while (n > 0) {
			if (broken[n % 10])
				return 0;

			n /= 10;
			len++;
			if (len > min)
				return 0;
		}
		return len;
	}
}
