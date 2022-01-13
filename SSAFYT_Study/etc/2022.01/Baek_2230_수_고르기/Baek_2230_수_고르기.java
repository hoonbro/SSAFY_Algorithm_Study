package etc._2022_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_2230_수_고르기 {
	static int N, M;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		
		int ans = Integer.MAX_VALUE;
		for(int i = 0, j = 0; i < N;) {
			if(arr[j] - arr[i] < M) {
				i++;
			}
			else if(arr[i] - arr[j] == M){
				ans = M;
				break;
			}
			else {
				ans = Math.min(arr[i]-arr[j], ans);
				j++;
			}
		}
		
		System.out.println(ans);
	}
}
