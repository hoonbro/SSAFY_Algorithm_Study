package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2661_좋은수열 {
	static int N, len;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dfs("");
	}
	
	
	static void dfs(String s) {
		if(s.length() == N) {
			System.out.println(s);
			System.exit(0);
		}
		
		for(int i = 1; i <= 3; i++) {
			if(!check(s+i))
				continue;
			dfs(s+i);
		}
	}
	
	static boolean check(String s) {
		len = s.length();
		for(int i = 1; i <= len/2; i++) {
			if(s.substring(len-i*2, len-i).equals(s.substring(len-i, len)))
				return false;
		}
		
		return true;
	}
}