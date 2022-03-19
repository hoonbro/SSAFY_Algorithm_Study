# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [2109] 순회강연
> https://www.acmicpc.net/problem/2109
## 알고리즘 분류
> 그리디, 정렬

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_2109_순회강연 {
	static int N;
	static Lecture[] lectures;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		lectures = new Lecture[N];

		int max = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int pay = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());

			lectures[i] = new Lecture(day, pay);
			max = Math.max(max, day);
		}
		
		Arrays.sort(lectures);

		int ans = 0;
		boolean[] check = new boolean[max + 1];
		for (int i = 0; i < N; i++) {
			for (int j = lectures[i].day; j >= 1; j--) {
				if (check[j])
					continue;

				check[j] = true;
				ans += lectures[i].pay;
				break;
			}
		}

		System.out.println(ans);
	}

	static class Lecture implements Comparable<Lecture> {
		int day, pay;

		public Lecture(int day, int pay) {
			this.day = day;
			this.pay = pay;
		}

		@Override
		public int compareTo(Lecture o) {
			if (o.pay == this.pay)
				return this.day - o.day;
			return o.pay - this.pay;
		}
	}
}
```

## 문제 풀이
1. pay가 많은 순으로 pay가 같다면 날짜 순으로 입력 값을 정렬해준다.
1. 정렬된 배열을 순차 탐색하며 check  방문체크 배열을 통해 강연할 수 있는 날짜에 넣고 total pay값을 늘려주는 과정을 반복한다.
