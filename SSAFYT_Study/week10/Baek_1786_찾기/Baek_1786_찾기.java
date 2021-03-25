package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//KMP알고리즘
public class Baek_1786_찾기 {
	static int total;
	static String T, P;
	static int[] fail;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = br.readLine();
		P = br.readLine();
		fail = new int[P.length()];

		// 실패함수로 실패 배열 만들기
		failFunc();
		compare();

		System.out.println(total);
		System.out.println(sb.toString());
	}

	static void compare() {
		int j = 0;
		for (int i = 0; i < T.length(); i++) {
			while (j > 0 && T.charAt(i) != P.charAt(j)) {
				j = fail[j - 1];
			}

			if (T.charAt(i) == P.charAt(j)) {
				if (j == P.length() - 1) {
					sb.append(i - P.length() + 2).append(" ");
					j = fail[j];
					total++;
				} else {
					j++;
				}
			}
		}
	}

	static void failFunc() {
		int j = 0;
		for (int i = 1; i < P.length(); i++) {
			while(j > 0 && P.charAt(i) != P.charAt(j)) {
				j = fail[j-1];
			}
			
			if(P.charAt(i) == P.charAt(j)) {
				fail[i] = ++j;
			}
		}
	}

}
