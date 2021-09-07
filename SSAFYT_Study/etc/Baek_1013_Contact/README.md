# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [1013] Contact
> https://www.acmicpc.net/problem/1013
## 알고리즘 분류
> 정규 표현식

## 코드
```java
package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_1013_Contact {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			String s = br.readLine();
			String pattern = "(100+1+|01)+";
			sb.append(s.matches(pattern) ? "YES" : "NO").append("\n");
		}
		System.out.println(sb.toString());
	}
}
```

## 문제 풀이
> 정규 표현식 문제이다.
> 처음 풀어보는 형식의 문제(사용 방법을 숙지해야 할 것 같다.)
