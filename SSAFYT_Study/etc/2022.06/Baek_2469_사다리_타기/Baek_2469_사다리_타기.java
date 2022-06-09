package etc._2022_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2469_사다리_타기 {
	static int M, N, Idx;
	static char[] arrTop, arrBottom;
	static char[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		M = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][M];
		arrTop = new char[M];
		arrBottom = br.readLine().toCharArray();
		
		for(int i = 0; i < M; i++)
			arrTop[i] = (char)('A'+i);
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			
			if(map[i][0] == '?') {
				Idx = i;
			}
		}
		
		for(int i = 0; i < Idx; i++) {
			for(int j = 0; j < M-1; j++) {
				if(map[i][j] == '*')
					continue;
				
				char temp = arrTop[j];
				arrTop[j] = arrTop[j+1];
				arrTop[j+1] = temp;
			}
		}
		
		for(int i = N-1; i > Idx; i--) {
			for(int j = 0; j < M-1; j++) {
				if(map[i][j] == '*')
					continue;
				
				char temp = arrBottom[j];
				arrBottom[j] = arrBottom[j+1];
				arrBottom[j+1] = temp;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < M-1; i++) {
			if(arrTop[i] == arrBottom[i])
				sb.append('*');
			else if(arrTop[i] == arrBottom[i+1] || arrBottom[i] == arrTop[i+1]) {
				sb.append('-');
				char temp = arrTop[i];
				arrTop[i] = arrTop[i+1];
				arrTop[i+1] = temp;
			}else {
				sb = new StringBuilder();
				
				for(int j = 0; j < M-1; j++) {
					sb.append('x');
				}
				break;
			}
		}
		
		System.out.println(sb.toString());
	}

}
