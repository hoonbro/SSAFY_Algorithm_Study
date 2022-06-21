# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [17298] 오큰수
> https://www.acmicpc.net/problem/17298
## 알고리즘 분류
> 스택

## 코드
```java
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
```

## 문제 풀이
1. 스택을 사용해 문제를 해결할 수 있다.
1. 입력값을 `stack`에 넣을 때 `stack`의 peek값을 확인해가며 peek값이 현재 값인 n보다 반복적으로 pop을 하며 해당 하는 index의 값에 n을 넣어준다.
