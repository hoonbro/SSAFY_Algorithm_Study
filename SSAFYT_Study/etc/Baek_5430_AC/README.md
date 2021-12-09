# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [5430] AC
> https://www.acmicpc.net/problem/5430
## 알고리즘 분류
> 문자열, 덱

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Baek_5430_AC {
	static int T, N;
	static String P, str;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		
		loop:
		while (T-- > 0) {
			P = br.readLine();
			N = Integer.parseInt(br.readLine());
			str = br.readLine().replace("[", "").replace("]", "");
			int len = P.length();
			
			// Deque에 입력받은 배열 담기
			String[] arr = str.split(",");
			Deque<String> dq = new ArrayDeque<>();
			for (int i = 0; i < arr.length; i++) {
				if(arr[i].equals(""))
					continue;
				dq.offer(arr[i]);
			}
			// 함수 실행
			boolean flag = true;
			for (int i = 0; i < len; i++) {
				char cmd = P.charAt(i);

				// 뒤집기
				if (cmd == 'R') {
					flag = !flag;
				}
				// 삭제
				else {
					if (dq.isEmpty()) {
						result.append("error\n");
						continue loop;
					}
					// 정방향
					if (flag) {
						dq.removeFirst();
					}
					// 역방향
					else {
						dq.removeLast();
					}
				}
			}

			StringBuilder sb = new StringBuilder();
			while (!dq.isEmpty()) {
				if(flag)
					sb.append(dq.pollFirst());
				else 
					sb.append(dq.pollLast());
				if(!dq.isEmpty())
					sb.append(",");
			}

			result.append("[").append(sb).append("]\n");
		}
		System.out.println(result.toString());
	}

}

```

## 문제 풀이

1. 자료구조 덱을 활용해 문제를 해결했다.
2. R 입력의 경우 flag를 반전만 시켜준다.
3. D 입력의 경우 flag에 따라 덱의 앞과 뒤의 값을 삭제해준다.
4. 출력 형식에 맞춰서 출력을 하면 해결

