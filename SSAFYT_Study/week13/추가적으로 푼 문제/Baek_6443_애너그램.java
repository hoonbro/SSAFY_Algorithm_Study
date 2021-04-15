package week13.추가;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baek_6443_애너그램 {
	static int N, len;
	static char[] str, arr;
	static int[] alpha;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			str = br.readLine().toCharArray();
			Arrays.sort(str);
			
			len = str.length;
			arr = new char[len];
			alpha = new int[26];
			for(int j = 0; j < len; j++) {
				alpha[str[j]-'a']++;
			}
			
			perm(0);
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void perm(int cnt) {
		if(cnt == len) {
			for(int i = 0; i < len; i++) {
				sb.append(arr[i]);
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 0; i < 26; i++) {
			if(alpha[i] == 0)
				continue;
			
			alpha[i]--;
			arr[cnt] = (char) (i + 'a');
			perm(cnt + 1);
			alpha[i]++;
		}
	}
}
