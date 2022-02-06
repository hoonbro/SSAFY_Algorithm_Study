# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [10800] 컬러볼
> https://www.acmicpc.net/problem/10800
## 알고리즘 분류
> 그래프 탐색, BFS

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_10800_컬러볼 {
	static int N;
	static Ball[] balls;
	static int[] ans, sum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N= Integer.parseInt(br.readLine());
		balls = new Ball[N];
		ans = new int[N];
		sum = new int[N+1];
		int total = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int color = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			
			balls[i] = new Ball(i, color, size);
		}
		Arrays.sort(balls);
		
		int idx = 0;
		int cnt = 0;
		for(int i = 1; i < N; i++) {
			while(balls[idx].size < balls[i].size) {
				total += balls[idx].size;
				sum[balls[idx].color] += balls[idx].size;
				idx++;
			}
			ans[balls[i].idx] = total - sum[balls[i].color];
		}
		
		for(int i = 0 ; i < N; i++) {
			sb.append(ans[i]).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
	}
	
	static class Ball implements Comparable<Ball>{
		int idx, color, size;

		public Ball(int idx, int color, int size) {
			this.idx = idx;
			this.color = color;
			this.size = size;
		}

		@Override
		public int compareTo(Ball o) {
			if(this.size == o.size)
				return this.color - o.color;
			return this.size - o.size;
		}
	}
}

```

## 문제 풀이
1. 
