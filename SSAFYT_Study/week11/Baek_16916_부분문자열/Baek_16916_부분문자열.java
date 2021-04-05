package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_16916_부분문자열 {
	static char[] S, P;
	static int[] fail;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine().toCharArray();
		P = br.readLine().toCharArray();
		
		fail = new int[P.length+1];
		
		failFunc();
		System.out.println(KMP());
	}
	
	static int KMP() {
		int i = 0, j = 0;
		while(i < S.length) {
			if(S[i] == P[j]) {
				if(j == P.length-1) {
					return 1;
				}else {
					i++;
					j++;
				}
			}else {
				if(j == 0)
					i++;
				else
					j = fail[j-1];
			}
		}
		return 0;
	}
	
	//실패함수
	static void failFunc() {
		int i = 1, j = 0;
		while(i < P.length) {
			if(P[i] == P[j]) {
				fail[i++] = ++j;
			}else {
				if(j == 0)
					i++;
				else
					j = fail[j-1];
			}
		}
	}
}
