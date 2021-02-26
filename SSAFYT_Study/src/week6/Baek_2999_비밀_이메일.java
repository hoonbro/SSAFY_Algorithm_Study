package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2999_비밀_이메일 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String s = br.readLine();
		int R=1, C=1;
		
		for(int i = 1; i < s.length(); i++) {
			if(s.length()%i == 0 && i <= s.length()/i) {
				R = i;
				C = s.length()/i;
			}
		}
		
		char[][] arr = new char[R][C];
		
		int idx = 0;
		for (int i = 0; i < C; i++) {
            for (int j = 0; j < R; j++) {
                arr[j][i] = s.charAt(idx++);
            }
        }
        
 
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(arr[i][j]);
            }
        }
		System.out.println(sb.toString());
	}
}
