package etc._2022_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baek_17298_오큰수 {
	static int N;
	static int[] arr;
	static Stack<Node> stack = new Stack<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			Node now = new Node(i, n);

			while (!stack.isEmpty() && n > stack.peek().n) {
				arr[stack.pop().idx] = n;
			}
			stack.push(now);
		}

		for (int i : arr) {
			if(i == 0)
				sb.append(-1);
			else
				sb.append(i);
			sb.append(" ");
		}

		System.out.println(sb);
	}
	
	static class Node{
		int idx, n;

		public Node(int idx, int n) {
			this.idx = idx;
			this.n = n;
		}
	}
}
