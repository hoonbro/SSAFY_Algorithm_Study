package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_1013_Contact {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			String s = br.readLine();
			String pattern = "(100+1+|01)+";
			sb.append(s.matches(pattern) ? "YES" : "NO").append("\n");
		}
		System.out.println(sb.toString());
	}
}
