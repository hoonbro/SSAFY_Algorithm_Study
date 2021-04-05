package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_9935_문자열폭발 {
	static int slen, blen;
	static char[] s, boom, result;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		s = br.readLine().toCharArray();
		boom = br.readLine().toCharArray();
		result = new char[s.length];
		
		slen = s.length;
		blen = boom.length;
		
		check();
		if(sb.length() == 0)
			sb.append("FRULA");
		System.out.println(sb.toString());
	}
	
	static void check() {
		int idx = 0;
		
		for(int i = 0; i < slen; i++) {
			result[idx] = s[i];
			if(isboom(idx)) {
				idx -= blen;
			}
			idx++;
		}
		for(int i = 0; i < idx; i++) {
			sb.append(result[i]);
		}
	}
	
	static boolean isboom(int idx) {
		if(idx < blen-1)
			return false;
		
		for(int i = 0; i < blen; i++) {
			if(boom[i] != result[idx - blen + 1 + i])
				return false;
		}
		return true;
	}
}
