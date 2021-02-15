package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//곱셈(분할정복), 다른코드 참고
//A의 1제곱부터 B제곱까지를 C로 나누고 순열이 발생하면 
//순열이 아닌부분을 B에서 빼고 B와 순열의 mod를 사용했지만 실패
public class Baek_1629 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		long C = Long.parseLong(st.nextToken());
		
		System.out.println(calc(A,B,C)%C);
	}

	static long calc(long a, long b, long c) {
		if (b == 0) {
			return 1;
		} 
		else if (b == 1) {
			return a;
		} 
		else if (b % 2 == 0) { //지수가 짝수일때
			long n = calc(a, b / 2, c) % c;
			return (n * n) % c;
		} 
		else { // 지수가 홀수일때
			long n = calc(a, b / 2, c) % c;
			return (((n * n) % c) * a) % c;
		}
	}

}
