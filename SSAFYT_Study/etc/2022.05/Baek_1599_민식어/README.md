# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [1599] 민식어
> https://www.acmicpc.net/problem/1599
## 알고리즘 분류
> 정렬, 문자열

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Baek_1599_민식어 {
	static int N;
	static String[] arr;
	static Map<String, Integer> map = new HashMap<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new String[N];

		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().replace("k", "c").replace("ng", "n|");
		}
		Arrays.sort(arr);
		for (String s : arr) {
			s = s.replace("c", "k").replace("n|", "ng");
			System.out.println(s);
		}
	}
}
```

## 문제 풀이
1. 영어의 알파벳과 민식어의 알파벳의 순서에 차이가 있는 알파벳은 k와 ng이다.
1. k는 c로 바꾸면 되지만 ng는 n과 o사이에 있어 알파벳으로 바꿀 수없다.
1. ng를 n| 혹은 n~(~과|는 알파벳보다 아스키코드가 큼)으로 바꿔준다.
1. replace를 한 후 배열을 정렬해주고 c와 n|을 다시 k와 ng로 바꿔준 후 출력한다.
