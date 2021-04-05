package week11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Baek_9935_문자열폭발2 {
	static int slen, blen;
	static char[] s, boom;
	static Stack<Character> st = new Stack<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		s = br.readLine().toCharArray();
		boom = br.readLine().toCharArray();
		
		slen = s.length;
		blen = boom.length;
		
		loop:
		for(int i = 0; i < slen; i++) {
			st.push(s[i]);
			
			if(blen > st.size())
				continue;
			
			for(int j = 0; j < blen; j++) {
				if(boom[j] != st.get(st.size()-blen+j))
					continue loop;
			}
			
			for(int j = 0; j < blen; j++) 
				st.pop();
		}
		
		for(Character c : st)
			sb.append(c);
		
		if(sb.length() == 0)
			sb.append("FRULA");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
}
