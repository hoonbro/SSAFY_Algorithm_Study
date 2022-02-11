package etc._2022_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek_1339_단어_수학 {
	static int N;
	static int[] alpha;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		alpha = new int[27];
		for(int i = 0; i < N; i++) {
            String s = br.readLine();
            int idx = 0;
            for(int j = s.length()-1; j >=0 ; j--) {
                int num = (int) Math.pow(10, idx);
                int c = s.charAt(j)-'A';
                alpha[c] += num;
                idx++;
            }
        }

        Arrays.sort(alpha);
        
        int answer = 0;
        int start = 9;
        for(int i = 26; i >= 0; i--) {
            if(alpha[i] == 0) break;
            answer += alpha[i] * start;
            start--;
        }

        System.out.println(answer);
	}
	
}
