package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//암호 만들기(조합)
public class Baek_1759 {
	static int L, C;
	static char[] word;
	static char[] pw;
	static boolean[] Vowel;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		L = Integer.parseInt(st.nextToken()); //비밀번호 자릿수
		C = Integer.parseInt(st.nextToken()); //주어지는 문자의 수
		word = new char[C];
		pw = new char[L];
		Vowel = new boolean[C];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			word[i] = st.nextToken().charAt(0);
		}
		//주어지는 문자 정렬
		Arrays.sort(word);
		//문자가 모음인지 여부 확인
		for(int i = 0; i < C; i++) {
			if(isVowel(word[i]))
				Vowel[i] = true;
		}
		dfs(0,0,0,false);
		System.out.println(sb);
	}
	
	//조합
	static void dfs(int idx, int cnt, int sCnt, boolean flag) {
		if(cnt == L) {
			if(flag && sCnt >=2) { //모음이 있을때만 출력
				for(int i = 0; i < L; i++) {
					sb.append(pw[i]);
				}
				sb.append("\n");
			}
			return;
		}

		for(int i = idx; i < C; i++) {
			// 오름차순이 아닐때
			if(cnt!=0 && pw[cnt-1] > word[i]) { 
				dfs(i+1, cnt, sCnt, flag);
				continue;
			}
			//오름차순일때
			pw[cnt] = word[i];
			if(Vowel[i]) { //넣는 암호가 모음이라면 flag true
				dfs(i+1, cnt+1, sCnt, true);
				continue;
			}
			//자음 +1
			dfs(i+1, cnt+1, sCnt+1, flag);
		}
	}

	static boolean isVowel(char c) {
		if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
			return true;
		return false;
	}
}
