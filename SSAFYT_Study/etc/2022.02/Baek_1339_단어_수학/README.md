# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [1339] 단어 수학
> https://www.acmicpc.net/problem/1339
## 알고리즘 분류
> 그리디 알고리즘

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek_1339_단어_수학 {
	static int N;
	static int[] alpha;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		alpha = new int[27];
		for(int i = 0; i < N; i++) {
            String s = br.readLine();
            int idx = 0;
            for(int j = s.length()-1; j >=0 ; j--) {
                int num = (int) Math.pow(10, idx);
                int c = s.charAt(j)-'A';
                alpha[c] += num;
                idx++;
            }
        }

        Arrays.sort(alpha);
        
        int answer = 0;
        int start = 9;
        for(int i = 26; i >= 0; i--) {
            if(alpha[i] == 0) break;
            answer += alpha[i] * start;
            start--;
        }

        System.out.println(answer);
	}
	
}
```

## 문제 풀이
1. 주어진 조건을 맞추면 문제를 해결할 수 있다.
