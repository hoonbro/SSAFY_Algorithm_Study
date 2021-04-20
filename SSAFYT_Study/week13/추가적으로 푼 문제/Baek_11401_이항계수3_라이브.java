package week13.추가;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_11401_이항계수3_라이브 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N,M;
		long P;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = 1000000007L;
		
		System.out.println(nCr(N,M,P));
	}
	
	static long nCr(int n, int r, long p) {
		if(r==0)
			return 1L;
		
		long[] fac = new long[n+1];
		fac[0] =1;
		
		for(int i =1 ; i <= n; i++) {
			fac[i] = fac[i-1] * i % p;
		}
		
		return (fac[n] * power(fac[r], p-2, p) % p * power(fac[n-r], p-2, p) % p)% p; 
	}
	
	static long power(long x, long y, long p) {
		long res = 1L;
		
		while(y > 0) {
			if(y % 2 == 1)
				res = (res*x)%p;
			
			y = y >>1;
			x =(x*x )% p; 
		}
		return res;
	}
}
