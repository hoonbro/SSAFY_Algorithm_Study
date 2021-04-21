# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [17281] 야구
## 문제 링크
> https://www.acmicpc.net/problem/17281
## 알고리즘 분류
> 구현, 시뮬레이션

## 코드
```java
package week13;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Baek_17281_야구 {
	static int N, M, max = 0;
	static int[] hitter, base;
	static int[][] innings;
	static boolean[] select;

	static int playball() {
		int score = 0;
		int now = 1;
		int out;
		base = new int[5];
		for(int i = 0; i < N; i++) { // 이닝
			out = 0;
			
			while(out < 3) {
				if(innings[i][hitter[now]] == 0)
					out++;
				else {
					for (int j = 0; j < innings[i][hitter[now]]; j++) {
						base[4] += base[3];
						base[3] = base[2];
						base[2] = base[1];
						base[1] = 0;
					}
					base[innings[i][hitter[now]]]++;
				}
				
				now = now+1 == 10 ? 1 : now+1;
			}
			score += base[4];
			Arrays.fill(base, 0);
		}
		return score;
	}
	
	static void perm(int cnt) {
		if (cnt == 10) {
			max = Math.max(max, playball());
			return;
		}

		for (int i = 1; i <= 9; i++) {
			if (select[i])
				continue;

			select[i] = true;
			hitter[i] = cnt;
			perm(cnt + 1);
			select[i] = false;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = 10;
		innings = new int[N][M];
		select = new boolean[M];
		hitter = new int[M];
		hitter[4] = 1; // 첫번째 플레이어 4번타자
		select[4] = true;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < M; j++) {
				innings[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		perm(2);
		System.out.println(max);
	}
}
```

## 문제 풀이
* 순열을 사용한 시뮬레이션 문제이다.
* 우선 순열을 통해서 타자의 순서를 정해준다.
* 정해진 타자의 순서에 맞게 각 이닝을 진행해준다(playball 메서드)
* now변수를 통해서 타자의 번호를 기억해 각 이닝에서 now에 맞는 타자가 타석에 오르게 한다.
* hitter의 값이 0이 아니라면 반복문을 통해서 선수들을 이동시켜줬다.
> if문을 여러개 만들기 싫어서 반복문을 사용했는데 반복문을 사용해서 시간이 더 오래 걸렸다.
* playball메서드가 끝나면 max와 비교하며 max를 갱신해준다. 