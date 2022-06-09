# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [2469] 사다리타기
> https://www.acmicpc.net/problem/2469
## 알고리즘 분류
> 구현

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2469_사다리_타기 {
	static int M, N, Idx;
	static char[] arrTop, arrBottom;
	static char[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		M = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][M];
		arrTop = new char[M];
		arrBottom = br.readLine().toCharArray();
		
		for(int i = 0; i < M; i++)
			arrTop[i] = (char)('A'+i);
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			
			if(map[i][0] == '?') {
				Idx = i;
			}
		}
		
		for(int i = 0; i < Idx; i++) {
			for(int j = 0; j < M-1; j++) {
				if(map[i][j] == '*')
					continue;
				
				char temp = arrTop[j];
				arrTop[j] = arrTop[j+1];
				arrTop[j+1] = temp;
			}
		}
		
		for(int i = N-1; i > Idx; i--) {
			for(int j = 0; j < M-1; j++) {
				if(map[i][j] == '*')
					continue;
				
				char temp = arrBottom[j];
				arrBottom[j] = arrBottom[j+1];
				arrBottom[j+1] = temp;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < M-1; i++) {
			if(arrTop[i] == arrBottom[i])
				sb.append('*');
			else if(arrTop[i] == arrBottom[i+1] || arrBottom[i] == arrTop[i+1]) {
				sb.append('-');
				char temp = arrTop[i];
				arrTop[i] = arrTop[i+1];
				arrTop[i+1] = temp;
			}else {
				sb = new StringBuilder();
				
				for(int j = 0; j < M-1; j++) {
					sb.append('x');
				}
				break;
			}
		}
		
		System.out.println(sb.toString());
	}
}
```

## 문제 풀이
1. 입력값으로 들어오는 M 값이 최대 26이기 때문에 조합을 사용하면 시간초과가 날 것이다.
1. 모든 경우의 수를 확인하지 않고 사다리의 위부터 출발해 ???줄 전까지의 알파벳을 구하고 사다리의 아래부터 출발해 ???줄 전까지의 알파벳을 구한다.
1. 구해진 두 알파벳을 구분하며 같다면 `*`를 다르다면 각자의 앞 뒤와 비교해 같다면 `-`를 다르다면 불가능한 경우이기 때문에 모든 줄에 `x`를 넣어주면 된다.
