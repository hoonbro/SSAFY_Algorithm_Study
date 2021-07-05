package week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_1644_소수의_연속합 {
	static int N, ans = 0;
	static boolean[] isPrime;
	static ArrayList<Integer> al = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		isPrime = new boolean[N+1];
		
		primeCheck();
		
		for(int i = 2; i <= N; i++) {
			if(isPrime[i])
				continue;
			
			al.add(i);
		}
		
		int start = 0, end = 0, sum = 0;
		while(true) {
			if(sum == N)
				ans++;
			
			if(sum > N)
				sum -= al.get(start++);
			else if(end == al.size())
				break;
			else
				sum += al.get(end++);
		}
		System.out.println(ans);
	}
	
	
	static void primeCheck() {
		for(int i = 2; i<= N; i++) {
			if(isPrime[i])
				continue;
			
			for(int j = i+i; j <= N; j+=i)
				isPrime[j] = true;
		}
	}
}
