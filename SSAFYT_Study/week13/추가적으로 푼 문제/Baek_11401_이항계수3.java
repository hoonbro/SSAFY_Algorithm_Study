package week13.추가;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_11401_이항계수3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long  N,R, P;
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		P = 1000000007L;
		
		long ans = 1;
		long t1 = 1;
		long t2 = 1;
		long t3;
		for(long i = 1; i <= N; i++) {
			t1 *= i;
			t1 %= P;
		}
		for(long i = 1; i <= R; i++) {
			t2 *= i;
			t2 %= P;
		}
		for(int i = 1; i <= N-R; i++) {
			t2 *= i;
			t2 %= P;
		}
		t3 = power(t2, P-2, P)%P;
		ans = (t1*t3)%P;
		System.out.println(ans);
	}
	
	static long power(long x, long y, long p) {
		long ans = 1;
		
		while(y>0) {
			if(y%2 != 0) {
				ans *= x;
				ans %= p;
			}
			x *= x;
			x %= p;
			y/=2;
		}
		return ans;
	}
}

