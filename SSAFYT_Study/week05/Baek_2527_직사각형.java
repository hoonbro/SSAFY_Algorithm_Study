package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2527_직사각형 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int input = 4;
		int[][] s = new int[4][2];
		while(input-- > 0) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				s[i][0] = Integer.parseInt(st.nextToken()); //x좌표
				s[i][1] = Integer.parseInt(st.nextToken()); //y좌표
			}

			if(s[1][0] < s[2][0] || s[1][1] < s[2][1] || s[3][0] < s[0][0] || s[3][1] < s[0][1])
				sb.append("d\n");
			else if(s[1][0] == s[2][0] && s[1][1] == s[2][1] || s[3][0] == s[0][0] && s[3][1] == s[0][1] ||
					s[1][0] == s[2][0] && s[0][1] == s[3][1] || s[3][0] == s[0][0] && s[1][1] == s[2][1])
				sb.append("c\n");
			else if(s[1][0] == s[2][0] && s[1][1] != s[2][1] || s[3][0] == s[0][0] && s[3][1] != s[0][1] ||
					s[1][0] != s[2][0] && s[0][1] == s[3][1] || s[3][0] != s[0][0] && s[1][1] == s[2][1])
				sb.append("b\n");
			else 
				sb.append("a\n");
		}
		System.out.println(sb);
	}
}
