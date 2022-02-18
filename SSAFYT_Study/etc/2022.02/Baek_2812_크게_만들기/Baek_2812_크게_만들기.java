package etc._2022_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baek_2812_크게_만들기 {
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Integer> stack = new Stack<>();

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		char[] num = br.readLine().toCharArray();

		for(int i = 0; i < N; i++) {
			while(K > 0 && !stack.isEmpty()) {
				if(stack.peek() < num[i]-'0') {
					stack.pop();
					K--;
				}else
					break;
			}
			
			stack.push(num[i] - '0');
		}
		
		while(K -- > 0)
			stack.pop();
		
		while(!stack.isEmpty())
			sb.append(stack.pop());

		System.out.println(sb.reverse());

	}
}
