package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_7512_연속하는_소수의_합 {
	static int N, M;
	static int[] prime, arr, sum, start;
	static boolean[] isPrime = new boolean[10000000];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		
		primeCheck();

		N = Integer.parseInt(br.readLine());
		for(int i = 1; i <= N; i++) {
			sb.append("Scenario ").append(i).append(":\n");
			
			M = Integer.parseInt(br.readLine());
			arr = new int[M]; // 연속되는 수 
			sum = new int[M]; // 연속 합
			start = new int[M]; // 시작 idx
			
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			loop:
			while(true) {
				for(int j = M-1; j >= 0; j--) {
					if(j == M-1) {
						sum[j] = consecutiveSum(j, start[j], arr[j]);
						continue;
					}
					
					while(true) {
						sum[j] = consecutiveSum(j, ++start[j], arr[j]);
						if(sum[j] > sum[j+1]) {
							start[j+1]++;
							start[j]--;
							continue loop;
						}else if(sum[j] == sum[j+1]) {
							break;
						}
					}
				}
				break;
			}
			sb.append(sum[M-1]).append("\n\n");
		}
		System.out.println(sb.toString());
	}
	
	static int consecutiveSum(int idx, int left, int size) {
		while(true) {
			int sum = 0;
			for(int i = left; i < left+size; i++) {
				sum += prime[i];
			}
			
			if(!isPrime[sum]) {
				start[idx] = left;
				return sum;
			}
			else {
				left++;
			}
		}
	}
	
	//소수 판별 메서드, isPrime = false일 경우 소수
	static void primeCheck() {
		int size = 0;
		for(int i = 2; i*i< 10000000; i++) {
			if(isPrime[i])
				continue;
			for(int j = i*2; j < 10000000; j+=i) {
				if(isPrime[j])
					continue;
				
				size++;
				isPrime[j] = true;
			}
		}

		prime = new int[size];
		int idx = 0;
		for(int i = 2; i < 10000000; i++) {
			if(!isPrime[i])
				prime[idx++] = i;
		}
	}
}
