# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [2528] 사다리
> https://www.acmicpc.net/problem/2528
## 알고리즘 분류
> 구현

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2528_사다리 {
	static int N, M, idx;
	static Stick[] sticks;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sticks = new Stick[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int len = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());

			if (dir == 0) {
				sticks[i] = new Stick(0, len, dir);
			} else {
				sticks[i] = new Stick(M - len, M, dir);
			}
		}

		idx = 0;
		int time = 0;
		
		while (true) {
			if (up())
				break;

			moveStick();
			time++;
		}
		
		System.out.println(time);
	}

	static boolean up() {
		while (true) {
			if (idx == N - 1)
				return true;

			if (sticks[idx].left > sticks[idx+1].right || sticks[idx].right < sticks[idx+1].left) {
				break;
			}
			else
				idx++;
		}

		return false;
	}

	static void moveStick() {
		for (int i = 0; i < N; i++) {
			if (sticks[i].dir == 0) {
				sticks[i].left++;
				sticks[i].right++;

				if (sticks[i].right == M)
					sticks[i].dir = 1;
			} else {
				sticks[i].left--;
				sticks[i].right--;

				if (sticks[i].left == 0)
					sticks[i].dir = 0;
			}
		}
	}

	static class Stick {
		int left, right, dir;

		public Stick(int left, int right, int dir) {
			this.left = left;
			this.right = right;
			this.dir = dir;
		}
	}
}
```

## 문제 풀이
1. 막대기의 `left`와 `right`인덱스를 저장해두고 움직이며 철수가 올라가는 코드를 작성했다.
1. 인덱스를 저장할 때 한칸씩 dir에 따라 좌/우에 한 칸씩 더 저장해 가장자리에서도 올라갈 수 있도록 함.
