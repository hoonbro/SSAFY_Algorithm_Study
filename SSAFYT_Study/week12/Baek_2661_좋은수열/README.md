# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [2661] 좋은수열
## 문제 링크
> https://www.acmicpc.net/problem/2661
## 알고리즘 분류
> 백트래킹

## 코드
```java
package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2661_좋은수열 {
	static int N, len;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dfs("");
	}
	
	
	static void dfs(String s) {
		if(s.length() == N) {
			System.out.println(s);
			System.exit(0);
		}
		
		for(int i = 1; i <= 3; i++) {
			if(!check(s+i))
				continue;
			dfs(s+i);
		}
	}
	
	static boolean check(String s) {
		len = s.length();
		for(int i = 1; i <= len/2; i++) {
			if(s.substring(len-i*2, len-i).equals(s.substring(len-i, len)))
				return false;
		}
		
		return true;
	}
}

```

## 문제 풀이
* 백트래킹을 사용해 문제를 해결했따.
* 문자열에 뒤에 1,2,3이 순서대로 들어갈 수 있는지 check메서드를 통해 확인을하고 들어갈 수 있다면 문자열을 새로 만들어줬다.
* 1,2,3을 순서대로 넣어줘 가장 작은 수열을 만들어 낸다.