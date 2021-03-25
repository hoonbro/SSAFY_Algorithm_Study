package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3282_0_1Knapsack {

	static class Product{
		int weight, value;

		public Product(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int w, v, N, K, T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int[] dp = new int[K+1];
			Product[] p = new Product[N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				w = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				p[i] = new Product(w, v);
				for(int j = K; j-p[i].weight >= 0; j--) {
					if(dp[j] < dp[j-p[i].weight] + p[i].value)
						dp[j] = dp[j-p[i].weight] + p[i].value;
				}
			}
			sb.append("#").append(t).append(" ").append(dp[K]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
