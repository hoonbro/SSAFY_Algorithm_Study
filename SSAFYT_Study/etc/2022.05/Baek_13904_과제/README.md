# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [13904] 과제
> https://www.acmicpc.net/problem/13904
## 알고리즘 분류
> 그리디

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baek_13904_과제 {
	static int N;
	static List<Homework> list = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		int end = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int day = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			list.add(new Homework(day, cost));
			end = Math.max(end, day);
		}

		int sum = 0;
		for (int i = end; i >= 1; i--) {
			
			Homework hw = new Homework(0, 0);
			
			for(Homework h : list) {
				if(h.day >= i && h.cost > hw.cost ) {
					hw = h;
				}
			}
			
			sum += hw.cost;
			list.remove(hw);
		}
		
		System.out.println(sum);
	}

	static class Homework implements Comparable<Homework> {
		int day, cost;

		public Homework(int day, int cost) {
			this.day = day;
			this.cost = cost;
		}

		@Override
		public int compareTo(Homework o) {
			if(o.day == this.day)
				return o.cost - this.cost;
			return o.day - this.day;
		}
	}
}
```

## 문제 풀이
1. 그리디한 문제이고 마지막 날부터 넣을 수 있는 최대 cost의 homeword의 값을 넣어주면 해결할 수 있다.
