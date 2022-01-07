# [Level3] 광고 삽입
## 문제 링크
> https://programmers.co.kr/learn/courses/30/lessons/72414
## 알고리즘 분류
> 투 포인터

## 코드
```java
public class Programmers_광고_삽입 {
	public String solution(String play_time, String adv_time, String[] logs) {
		int playTime = timeToSecond(play_time);
		int advTime = timeToSecond(adv_time);
		int[] total = new int[playTime + 1];

		for (String log : logs) {
			String[] arr = log.split("-");

			int start = timeToSecond(arr[0]);
			int end = timeToSecond(arr[1]);

             //종료 시점은 보지 않음으로 부등호(<)
			for (int j = start; j < end; j++) {
				total[j]++;
			}
		}
		
        //logs가 30만개이기 때문에 int를 사용할 시 범위 초과
		long sum = 0;
		for (int i = 0; i < advTime; i++) {
			sum += total[i];
		}

		long max = sum;
		int start = 0;
		for (int i = 1, j = advTime; j < playTime; i++, j++) {
			sum += total[j] - total[i - 1];

			if (max < sum) {
				max = sum;
				start = i;
			}
		}

		return secondToTime(start);
	}

	int timeToSecond(String time) {
		int second = 0;
		String[] arr = time.split(":");

		second += Integer.parseInt(arr[0]) * 3600;
		second += Integer.parseInt(arr[1]) * 60;
		second += Integer.parseInt(arr[2]);

		return second;
	}

	String secondToTime(int second) {
		StringBuilder sb = new StringBuilder();

		for (int i = 2; i >= 0; i--) {
			int t = second / (int) Math.pow(60, i);
			second %= (int) Math.pow(60, i);

			if (t < 10) {
				sb.append(0).append(t);
			} else {
				sb.append(t);
			}

			if (i != 0)
				sb.append(":");
		}

		return sb.toString();
	}
}
```
## 문제 풀이
- 시작전 : timeToSecond 메서드(string -> int)와 secondToTime(int -> string) 메서드를 만든다.

1. 입력 값(play_time, adv_time)을 초 단위로 변경한다.
2. 전체 play_time만큼의 `total`배열을 선언한다
3. 주어진 `logs`배열을 순회하면서 로그의 시작 시점부터 종료 시점까지 total 배열에 +1를 한다.
4. 0~advTime(초)까지의 sum을 구해둔다.
5. `투 포인터`를 활용해 sum에 시작 시간을 빼고 종료 시간을 더해가며 max값을 갱신한다.
   - max값이 갱신되었다면, 시작 시간인 start를 기억해둔다.
6. 초단위로 기록되어있는 start를 string 형태로 변경한 후 return 한다.
