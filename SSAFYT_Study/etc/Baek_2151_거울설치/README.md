# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [2151] 거울 설치

> https://www.acmicpc.net/problem/2151

## 알고리즘 분류

> BFS

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Baek_2151_거울설치 {
	static int N, ans = 0;
	static Pos start;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static char[][] map;
	static int[][] visited;
	static PriorityQueue<Pos> pq = new PriorityQueue<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new int[N][N];

		for(int i = 0; i < N; i++) {
			Arrays.fill(visited[i], -1);
		}
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				map[i][j] = s.charAt(j);

				if (map[i][j] == '#') {
					start = new Pos(i, j, 0, 0);
				}
			}
		}
		map[start.x][start.y] = '*';
		
		//문에서 출발하는 방향
		for (int d = 0; d < 4; d++) {
			int nx = start.x + X[d];
			int ny = start.y + Y[d];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == '*')
				continue;
			pq.offer(new Pos(start.x, start.y, d, 0));
		}
		
		bfs();
		System.out.println(ans);
	}

	static void bfs() {
		map[start.x][start.y] = '*'; 
		pq.offer(start);
		visited[start.x][start.y]= 0; 
		while (!pq.isEmpty()) {
			Pos now = pq.poll();
			
			if(map[now.x][now.y] =='#') {
				ans = now.cnt;
				return;
			}
			
			
			int nx = now.x + X[now.dir];
			int ny = now.y + Y[now.dir];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == '*' || visited[nx][ny] >= now.cnt)
				continue;
			
			visited[now.x][now.y] = now.cnt;
			
			//거울을 놓을 수 있는 경우
			if(map[nx][ny] == '!') {
				pq.offer(new Pos(nx, ny, (now.dir+1)%4, now.cnt+1));
				pq.offer(new Pos(nx, ny, (now.dir+3)%4, now.cnt+1));
			}
            //거울이거나 아닐 경우 모두
			pq.offer(new Pos(nx, ny, now.dir, now.cnt));
		}
	}

	static class Pos implements Comparable<Pos> {
		int x, y, dir, cnt;

		public Pos(int x, int y, int dir, int cnt) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Pos o) {
			return this.cnt - o.cnt;
		}
	}
}
```

## 문제 풀이

1. 출발하는 문(#)을 저장해두고 출발할 수 있는 방향을 우선순위 큐에 넣어둔다.  
   1. 출발하는 문은 중복되지 않게 *으로 초기화해둔다.
2. 방문 체크 배열(visited)를  int형으로 선언해두고 -1로 초기화 시킨다(거울을 사용하지 않을 경우도 있기 때문에 -1로 초기화)
3. bfs 알고리즘을 동작 시키면서 거울을 놓을 수 있는 곳(!)일 경우, 방향을 현재방향에서 시계, 반시계 방향 90도로 돌리고 cnt를 하나 높이고 큐에 넣어준다.
4. ! or . 일 경우 모두 현재 방향 기준으로 전진시켜서 큐에 넣어준다.
5. 목적지(#)에 도착한 경우 cnt를 출력해주고 함수를 종료시킨다.
   
   > PriorityQueue를 사용했기 때문에 거울을 최소로 사용한 경우가 보장된다.
