package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2023_신기한_소수 {
	static int N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for(int i = 2; i < 10; i++) {
			dfs(1, i);
		}
		System.out.println(sb.toString());
	}
	
	static void dfs(int len, int num) {
		if((num != 2 && num %2 == 0) ||!isPrime(num))
			return;
		
		if(len == N) {
			sb.append(num).append("\n");
		}
		
		for(int i = 1; i < 10; i+=2) {
			dfs(len+1, num*10 + i);
		}
	}
	
	static boolean isPrime(int num) {
		for(int i = 2; i*i <= num; i++) {
			if(num %i == 0)
				return false;
		}
		return true;
	}
}
