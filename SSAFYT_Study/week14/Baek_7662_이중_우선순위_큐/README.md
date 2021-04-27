# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [7662] 이중 우선순위 큐
> https://www.acmicpc.net/problem/7662
## 알고리즘 분류
> 트리를 사용한 집합과 맵

## 코드
```java
package week14;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Baek_7662_이중_우선순위_큐 {
	static int T, N, num, n;
	static char oper;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> treeMap = new TreeMap<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				oper = st.nextToken().charAt(0);
				num = Integer.parseInt(st.nextToken());

//                지정된 키의 값을 반환하며 찾지 못하면 기본값(defaultValue)로 지정된 객체를 반환한다
				if (oper == 'I')
					treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
				else {
					if (treeMap.isEmpty())
						continue;
					n = (num == 1) ? treeMap.lastKey() : treeMap.firstKey();
					if (treeMap.put(n, treeMap.get(n) - 1) == 1)
						treeMap.remove(n);
				}
			}

			if (treeMap.isEmpty()) {
				sb.append("EMPTY").append("\n");
			} else {
				sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
```

## 문제 풀이
* 처음에는 우선순위 큐와 linkedlist를 사용했지만 모두 시간 초과가 발생했다.
* treeMap을 사용하면 자동적으로 정렬이 되고 제일 앞 인덱스와 마지막 인덱스를 찾기 쉽고 
* key value쌍으로 저장되기 때문에 중복되는 값이 들어오면 +1을 하면 되기 때문에 시간 복잡도적인 측면에서 유리하다는걸 알게 되었다.