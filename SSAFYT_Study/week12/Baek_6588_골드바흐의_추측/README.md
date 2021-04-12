# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/10.svg" width="30"> [6588] 골드바흐의 추측
## 문제 링크
> https://www.acmicpc.net/problem/6588
## 알고리즘 분류
> 소수

## 코드
```java
package week12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baek_6588_골드바흐의_추측 {
	static int N;
	static boolean[] isPrime;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		prime();
		while(true) {
			N =	Integer.parseInt(br.readLine());
			if(N == 0)
				break;
			
			boolean flag = false;
			for(int i = 3; i < N; i+=2) {
				if(isPrime[i] || isPrime[N-i])
					continue;
				
				sb.append(N).append(" = ").append(i).append(" + ").append(N-i).append("\n");
				flag = true;
				break;
			}
			
			if(!flag)
				sb.append("Goldbach's conjecture is wrong.\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void prime() {
		isPrime = new boolean[1000001];
		
		for(int i = 2; i < Math.sqrt(1000001); i++) {
			if(isPrime[i])
				continue;
			
			for(int j = i*2; j < 1000001; j+=i) {
				isPrime[j] = true;
			}
		}
	}
}
```

## 문제 풀이
* 에라토스테네스의 체를 사용해 문제를 해결했다.
* 에라토스테네스의 체를 사용해 최대입력까지의 소수를 판별하고 들어오는 입력에 따라 골드바흐의 추측이 맞는지 아닌지를 판별해준다.