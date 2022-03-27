package etc._2022_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1593_문자_해독 {
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		String W = br.readLine();
		String S = br.readLine();
		
		int[] wUsed = new int[58];
		int[] sUsed = new int[58];
		
		for(int i = 0; i < N; i++) {
			int a = W.charAt(i) - 'A';
			wUsed[a]++;
			
			int b = S.charAt(i) - 'A';
			sUsed[b]++;
		}
		
		int left = 0, right = N, ans = 0;
		while(true) {
			if(Arrays.equals(wUsed, sUsed))
				ans++;
			
			if(right >= M)
				break;
			
			sUsed[S.charAt(left++) - 'A']--;
			sUsed[S.charAt(right++) - 'A']++;
		}
		
		System.out.println(ans);
	}
}
