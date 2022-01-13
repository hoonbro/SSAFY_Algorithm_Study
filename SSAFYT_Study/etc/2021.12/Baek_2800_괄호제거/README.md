# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [2800] 괄호 제거
> https://www.acmicpc.net/problem/2800
## 알고리즘 분류
> 스택, DFS

## 코드
```java
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
```

## 문제 풀이
1. 스택과 재귀 함수를 활용해 문제를 해결했다.
1. 입력을 받으면서 스택을 사용해 괄호의 짝을 지어준다.
1. 짝 배열인 bracket과 방문체크 visited배열을 사용해 확인을 하며 출력해준다.
