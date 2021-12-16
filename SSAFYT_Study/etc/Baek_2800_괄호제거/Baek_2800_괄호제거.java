package etc._2021_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Baek_2800_괄호제거 {
	static int[] bracket;
	static char[] str;
	static boolean[] visited; 
	static Stack<Integer> stack = new Stack<>();
	static Set<String> set = new HashSet<>();
	
	static void dfs(int idx, int len, StringBuilder sb) {
		if(idx == len) {
			set.add(sb.toString());
			return;
		}
		
		if(str[idx] == '(') {
			visited[idx] = true;
			dfs(idx+1, len, sb);
			visited[idx] = false;
		}
		
		if(str[idx] == ')' && visited[bracket[idx]]) {
			visited[idx] = true;
			dfs(idx+1, len, sb);
			visited[idx] = false;
		}else {
			sb.append(str[idx]);
			dfs(idx+1, len, sb);
			sb.deleteCharAt(sb.length()-1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input = br.readLine();
		str = input.toCharArray();
		bracket = new int[str.length];
		visited = new boolean[str.length];
		
		for(int i = 0; i < str.length; i++) {
			if(str[i] == '(') {
				stack.push(i);
			}else if(str[i] == ')') {
				bracket[i] = stack.peek();
				bracket[stack.peek()] = i;
				stack.pop();
			}
		}
		
		dfs(0, str.length, new StringBuilder());
		set.remove(input);
		
		List<String> list = new ArrayList<>(set);
		Collections.sort(list);
		for(String s : list) {
			sb.append(s).append("\n");
		}
		System.out.println(sb.toString());
	}
}
