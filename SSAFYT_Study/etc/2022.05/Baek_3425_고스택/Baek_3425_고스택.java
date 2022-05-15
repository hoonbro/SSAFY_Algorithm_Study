package etc._2022_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baek_3425_고스택 {
	static int N;
	static ArrayList<String> commands;
	static Stack<Long> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		loop: while (true) {
			commands = new ArrayList<>();
			

			while (true) {
				String s = br.readLine();

				if (s.equals("QUIT")) {
					break loop;
				}

				if (s.equals("END")) {
					break;
				}

				commands.add(s);
			}

			N = Integer.parseInt(br.readLine());

			for (int i = 0; i < N; i++) {
				long input = Integer.parseInt(br.readLine());
				stack = new Stack<>();
				stack.add(input);

				if (!goStack() || stack.size() != 1)
					sb.append("ERROR").append("\n");
				else {
					if (Math.abs(stack.peek())> 1000000000) {
						stack.pop();
						sb.append("ERROR").append("\n");
					} else {
						sb.append(stack.pop()).append("\n");
					}
				}
			}
			sb.append("\n");
			br.readLine();
		}

		System.out.println(sb.toString());
	}

	static boolean goStack() {
		long a, b;
		for (String command : commands) {
			switch (command) {
			case "POP":
				if(stack.isEmpty())
					return false;
				stack.pop();
				break;
			case "INV":
				if(stack.isEmpty())
					return false;
				stack.push(-stack.pop());
				break;
			case "DUP":
				if(stack.isEmpty())
					return false;
				stack.push(stack.peek());
				break;
			case "SWP":
				if(stack.size() < 2)
					return false;
				a = stack.pop();
				b = stack.pop();
				stack.push(a);
				stack.push(b);
				break;
			case "ADD":
				if(stack.size() < 2)
					return false;
				stack.push(stack.pop() + stack.pop());
				break;
			case "SUB":
				if(stack.size() < 2)
					return false;
				a = stack.pop();
				b = stack.pop();
				stack.push(b - a);
				break;
			case "MUL":
				if(stack.size() < 2)
					return false;
				stack.push(stack.pop() * stack.pop());
				break;
			case "DIV":
				if(stack.size() < 2)
					return false;
				a = stack.pop();
				if(a == 0) 
					return false;
				b = stack.pop();
				
				stack.push(b / a);
				break;
			case "MOD":
				if(stack.size() < 2)
					return false;
				a = stack.pop();
				if(a == 0) 
					return false;
				b = stack.pop();
				stack.push(b % a);
				break;
			default:
				String[] s = command.split(" ");
				stack.push(Long.parseLong(s[1]));
				break;
			}
		}
		
		return true;
	}
}
