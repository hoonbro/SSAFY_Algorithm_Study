package week03;

import java.util.Scanner;

//오르막 수
public class Baek_11057 {
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		System.out.println(dp());
	}

	static int dp() {
		int sum = 0;
		int[][] arr = new int[N+1][10];
		
		for (int i = 0; i < 10; i++) {
			arr[1][i] = 1;
		}
		
		for(int i = 2; i <= N; i++) {
			for(int j = 0; j < 10; j++) {
				for(int k = j; k < 10; k++) {
					arr[i][j] += arr[i-1][k] % 10007;
				}
			}
		}
		
		for(int i = 0; i < 10; i++)
			sum += arr[N][i];
		return sum % 10007;
	}
}
