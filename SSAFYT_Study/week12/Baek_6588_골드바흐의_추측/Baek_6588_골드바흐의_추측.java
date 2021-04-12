package week12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baek_6588_골드바흐의_추측 {
	static int N;
	static boolean[] isPrime;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		prime();
		while(true) {
			N =	Integer.parseInt(br.readLine());
			if(N == 0)
				break;
			
			boolean flag = false;
			for(int i = 3; i < N; i+=2) {
				if(isPrime[i] || isPrime[N-i])
					continue;
				
				sb.append(N).append(" = ").append(i).append(" + ").append(N-i).append("\n");
				flag = true;
				break;
			}
			
			if(!flag)
				sb.append("Goldbach's conjecture is wrong.\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void prime() {
		isPrime = new boolean[1000001];
		
		for(int i = 2; i < Math.sqrt(1000001); i++) {
			if(isPrime[i])
				continue;
			
			for(int j = i*2; j < 1000001; j+=i) {
				isPrime[j] = true;
			}
		}
	}
}
