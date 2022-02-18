# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [2812] 크게 만들기
> https://www.acmicpc.net/problem/2812
## 알고리즘 분류
> 그리디, Stack

## 코드
```java
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

```

## 문제 풀이
1. 스택을 활용해 문제를 해결했다.
1. 반복문을 돌며 스택에 넣어둔 값이 현재 인덱스의 값보다 클 때까지 스택에서 pop을 해준다.
1. 현재 인덱스의 값을 스택에 push 해준다.
1. 출력.
