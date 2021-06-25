package week21.추가;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_12904_A와B {
	static String S, T;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		S = br.readLine();
		T = br.readLine();
		
		while(true) {
			if(T.equals(S)) {
				System.out.println(1);
				break;
			}else if(T.length() < S.length()) {
				System.out.println(0);
				break;
			}
			
			if(T.charAt(T.length()-1) == 'A'){
				T = T.substring(0, T.length()-1);
			}
			else {
				sb.append(T.subSequence(0, T.length()-1));
				sb.reverse();
				T = sb.toString();
				sb.setLength(0);
			}
		}
	}
}
