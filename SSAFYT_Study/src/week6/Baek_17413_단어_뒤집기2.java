package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

//구현 stack
public class Baek_17413_단어_뒤집기2 {
	static String s;
	static ArrayList<String> al;
	static Stack<Character> stack;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		stack = new Stack<>();
	
		String s = br.readLine();
		boolean flag = false;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '<')
				flag = true;
			else if(s.charAt(i) == '>') {
				flag = false;
				sb.append(s.charAt(i));
				continue;
			}
			
			if(flag || s.charAt(i) == ' ') {
				while(!stack.empty())
					sb.append(stack.pop());
				sb.append(s.charAt(i));
			}
			else if(i == s.length()-1) {
				stack.push(s.charAt(i));
				while(!stack.empty())
					sb.append(stack.pop());
			}
			else
				stack.push(s.charAt(i));
		}
		System.out.println(sb.toString());
	}
}
