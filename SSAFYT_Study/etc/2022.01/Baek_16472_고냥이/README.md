# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [16472] 고냥이
> https://www.acmicpc.net/problem/16472
## 알고리즘 분류
> 투 포인터

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_16472_고냥이 {
	static int N;
	static int[] alpha = new int[26];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		char[] arr = br.readLine().toCharArray();
		
		int cnt = 1;
		int ans = 0;
		alpha[arr[0] - 'a']++;
		for(int i = 0, j = 1; j < arr.length;) {
			if(alpha[arr[j] - 'a'] == 0) {
				if(cnt < N) {
					alpha[arr[j] - 'a']++;
					j++;
					cnt++;
				}else {
					alpha[arr[i] - 'a']--;
					if(alpha[arr[i] - 'a']== 0) {
						cnt--;
					}
					i++;
				}
			}else {
				alpha[arr[j] - 'a']++;
				j++;
			}
			ans = Math.max(ans, j-i);
		}
		
		System.out.println(ans);
	}
}
```

## 문제 풀이
1. 문자열을 인덱스로 쉽게 접근하기 위해 toCharArray로 char배열로 만들어준다.
2. i = 0, j = 1를 기본값으로 잡고, 알파벳 사용 갯수는 세는 alpha[0-'a']을 1늘려주 후 알파벳 종류를 세는 cnt = 1로 초기화한다. 
3. j번째 인덱스의 알파벳이 사용된 적이 없을 경우
   1. 알파벳 종류가 N보다 작다면 j번째 알파벳을 사용하고(alpha[arr[j] - 'a']++), cnt와 j를 1씩 증가 시킨다.
   2. 알파벳 종류가 N과 같다면 i번째 알파벳의 사용을 줄이고(alpha[arr[i] - 'a']--), 만약 0이라면 알파벳 사용 갯수(cnt)도 하나 줄인 후 인덱스 i를 1 증가 시킨다.
4. j번째 인덱스의 알파벳이 사용되었을 경우
   1. 해당 알파벳의 갯수(alpha[arr[j] - 'a'])를 1증가 시켜주고 인덱스 j를 1 증가시킨다.
