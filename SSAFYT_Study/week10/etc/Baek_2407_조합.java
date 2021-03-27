package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Baek_2407_조합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		BigInteger result = BigInteger.ONE;
		for(int i = N; i >= N-M+1; i--) {
			result = result.multiply(new BigInteger(String.valueOf(i)));
		}
		for(int i = M; i > 1; i--) {
			result = result.divide(new BigInteger(String.valueOf(i)));
		}
		System.out.println(result);
	}
}
