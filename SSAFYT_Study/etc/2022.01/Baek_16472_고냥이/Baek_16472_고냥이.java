package etc._2022_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_16472_고냥이 {
	static int N;
	static int[] alpha = new int[26];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		char[] arr = br.readLine().toCharArray();
		
		int cnt = 1;
		int ans = 0;
		alpha[arr[0] - 'a']++;
		for(int i = 0, j = 1; j < arr.length;) {
			if(alpha[arr[j] - 'a'] == 0) {
				if(cnt < N) {
					alpha[arr[j] - 'a']++;
					j++;
					cnt++;
				}else {
					alpha[arr[i] - 'a']--;
					if(alpha[arr[i] - 'a']== 0) {
						cnt--;
					}
					i++;
				}
			}else {
				
				alpha[arr[j] - 'a']++;
				j++;
			}
			ans = Math.max(ans, j-i+1);
		}
		
		System.out.println(ans);
	}
}
