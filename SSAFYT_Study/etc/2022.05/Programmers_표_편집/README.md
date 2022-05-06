# [Level3]  표 편집
## 문제 링크
> https://programmers.co.kr/learn/courses/30/lessons/81303
## 알고리즘 분류
> 스택

## 코드
```java
import java.util.Stack;

public class Programmers_표_편집 {

	public static void main(String[] args) {

	}

	public String solution(int n, int k, String[] cmd) {
		StringBuilder sb = new StringBuilder();

		Stack<Integer> st = new Stack<>();
		int[] isDelete = new int[n];
		int size = n;
		for (int i = 0; i < cmd.length; i++) {
			char key = cmd[i].charAt(0);
			switch (key) {
			case 'U':
				k -= Integer.parseInt(cmd[i].substring(2));
				break;
			case 'D':
				k += Integer.parseInt(cmd[i].substring(2));
				break;
			case 'C':
				st.add(k);
				size--;

				if (k == size)
					k--;
				break;
			case 'Z':
				int rollback = st.pop();
				if (rollback <= k)
					k++;	
				size++;
				break;
			}
		}

		while (!st.isEmpty()) {
			int idx = st.pop();
			isDelete[idx]++;
			
		}

		for (int i = 0; i < n; i++) {
			if (isDelete[i] != 0) {
				int cnt = isDelete[i];
				for(int j = 0; j < cnt; j++) 
					sb.append("X");
				
				i += cnt-1;
			}
			else
				sb.append("O");
		}
		return sb.toString();
	}
}
```
