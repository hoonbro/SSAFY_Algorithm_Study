# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [1865] 웜홀
## 문제 링크
> https://www.acmicpc.net/problem/1865
## 알고리즘 분류
> 벨만포드 알고리즘

## 코드
```java
package week10; 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1865_웜홀 {
	static int T, N, M, W, s, e, t, INF = 987654321;
	static int[] dis;
	static ArrayList<ArrayList<Node>> al;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 정점
			M = Integer.parseInt(st.nextToken()); // 도로
			W = Integer.parseInt(st.nextToken()); // 웜홀

			dis = new int[N + 1];
			al = new ArrayList<ArrayList<Node>>();

			for (int i = 0; i <= N; i++) {
				al.add(new ArrayList<>());
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				s = Integer.parseInt(st.nextToken());
				e = Integer.parseInt(st.nextToken());
				t = Integer.parseInt(st.nextToken());
				al.get(s).add(new Node(e, t));
				al.get(e).add(new Node(s, t));
			}
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				s = Integer.parseInt(st.nextToken());
				e = Integer.parseInt(st.nextToken());
				t = Integer.parseInt(st.nextToken());
				al.get(s).add(new Node(e, -t));
			}

			if (bellmanFord())
				sb.append("YES");
			else
				sb.append("NO");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	static boolean bellmanFord() {
		Arrays.fill(dis, INF);
		dis[1] = 0;
		boolean flag = false;
		for (int i = 1; i < N; i++) { // 정점수-1만큼 반복
			flag = false;
			for (int j = 1; j <= N; j++) { // 현재 정점
				for (Node n : al.get(j)) {
					if (dis[n.to] > dis[j] + n.weight) {
						dis[n.to] = dis[j] + n.weight;
						flag = true;
					}
				}
			}
			if (!flag) // 업데이트가 안되어있으면 다음에도 안되므로 break;
				break;
		}
		if(flag) { // 음수사이클 확인하기
			for (int j = 1; j <= N; j++) { // 현재 정점
				for (Node n : al.get(j)) {
					if (dis[n.to] > dis[j] + n.weight) {
						return true; //음수 사이클이 생긴경우
					}
				}
			}
		}
		return false;
	}

	static class Node {
		int to, weight;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
}
```

## 문제 풀이
> 처음 풀어보는 벨만포드 문제였고 다양한 알고리즘을 공부해야한다는걸 느끼게 되는 문제였다.
* 벨만 포드 알고리즘은 음수의 가중치가 있는 경우에도 사용할 수 있는 최단거리 알고리즘이다.
* 정점의 수만큼 반복하면서 각 정점의 가중치를 최소값으로 초기화해주는 작업을 하게된다.
* 가중치가 초기화되지않는 경우가 있다면 그 이후에도 초기화가 되지 않기 때문에 반복을 멈춰주게된다.
* 마지막(N)번의 경우에 update가 된다면 음수사이클이 발생함 즉, 현재 위치로 돌아왔을때 가중치가 더 작아지기 때문에 YES를 출력하게 된다.
