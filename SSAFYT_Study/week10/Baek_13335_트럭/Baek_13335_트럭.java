package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_13335_트럭 {
	static int N, W, L, ans = 0;
	static int[] truck, bridge;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		truck = new int[N];
		bridge = new int[W];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			truck[i] = Integer.parseInt(st.nextToken());
		}
		
		int i = 0, bridgeWeight = 0, cnt = 0;
		while(i < N) {
			cnt++;
			int a = bridge[0];
			for(int j = 1; j < W; j++) {
				bridge[j-1] = bridge[j]; //다리위 트럭 움직임
			}
			bridge[W-1] = 0;
			
			bridgeWeight -= a;
			
			if(bridgeWeight + truck[i] <= L) {
				bridge[W-1] = truck[i];
				bridgeWeight += truck[i];
				i++;
			}
		}
		System.out.println(W + cnt);
	}
}
