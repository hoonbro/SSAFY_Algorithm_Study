package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_12025_장난꾸러기_영훈 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] pw = br.readLine().toCharArray();
		long k = Long.parseLong(br.readLine()) - 1;
		long bit = 1;
		for (int i = pw.length - 1; i >= 0; i--) {
			if (pw[i] == '6' || pw[i] == '1') {
				pw[i] = '1';
				bit <<= 1;
			} else if (pw[i] == '2' || pw[i] == '7') {
				pw[i] = '2';
				bit <<= 1;
			}
		}

		if(k > bit) {
			System.out.println(-1);
			return;
		}		
		bit |= k;
		long cnt = 1;
		for (int i = pw.length - 1; i >= 0; i--) {
			if (pw[i] == '1') {
				if ((bit & cnt) != 0) {
					pw[i] = '6';
				}
				cnt <<=1;
			} else if (pw[i] == '2') {
				if ((bit & cnt) != 0) {
					pw[i] = '7';
				}
				cnt <<=1;
			}
			sb.append(pw[i]);
		}
		System.out.println(sb.reverse());
	}
}
