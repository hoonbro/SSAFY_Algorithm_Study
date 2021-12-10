package etc._2021_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek_5052_전화번호_목록 {
	static int T, N;
	static String[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		loop:
		while(T-- > 0) {
			N = Integer.parseInt(br.readLine());
			arr = new String[N];
			
			for(int i = 0; i < N; i++) {
				arr[i] = br.readLine();
			}
			Arrays.sort(arr);
			
			sb.append(check()).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static String check() {
		for(int i = 0; i < arr.length - 1; i++) {
			if(arr[i+1].startsWith(arr[i])) {
				return "NO";
			}
		}
		return "YES";
	}
}
