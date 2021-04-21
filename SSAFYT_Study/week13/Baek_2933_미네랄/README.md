# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [2933] 미네랄
## 문제 링크
> https://www.acmicpc.net/problem/2933
## 알고리즘 분류
> 구현 시뮬레이션

## 코드
```java
package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_2933_미네랄 {
	static int N, M, K, total, nx, ny;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	static char[][] map;
	static boolean[][] visited;
	static Queue<Pos> q = new LinkedList<>();
	
	static void down(int x, int y) {
		int cnt = 1;
		loop:
		while(x < N-1) {
			for(int i = x; i >= 0; i--) {
				for(int j = 0; j < M; j++) {
					//땅 위에있는 클러스터의 일부
					if(map[i][j] == 'x' && !visited[i][j]) {
						//바로 밑칸에 땅과 연결된 클러스터가 있다면 break
						if(visited[i+cnt][j]) {
							break loop;
						}
					}
				}
			}
			cnt++;
			x++;
		}
		if(cnt == 1)
			return;
		cnt--;
		x -= cnt;
		//cnt만큼 내린다
		for(int i = x; i >= 0; i--) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 'x' && !visited[i][j]) {
					map[i+cnt][j] = 'x';
					map[i][j] = '.';
				}
			}
		}
	}
	
	static boolean bfs(int x, int y) {
		//밑에 닿아있지 않은 클러스터
		if(x != N-1) {
			down(x, y);
			return false;
		}
		
		q.offer(new Pos(x, y));
		visited[x][y] = true;
		Pos pos;
		
		while(!q.isEmpty()) {
			pos = q.poll();
			
			for(int d = 0; d < 4; d++) {
				nx = pos.x + X[d];
				ny = pos.y + Y[d];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || map[nx][ny] == '.')
					continue;
				
				visited[nx][ny] = true;
				q.offer(new Pos(nx, ny));
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j); 
				if(map[i][j] == 'x')
					total++;
			}
		}
		
		K = Integer.parseInt(br.readLine());
		int h;
		boolean flag;
		st = new StringTokenizer(br.readLine());
		for(int k = 0; k < K; k++) {
			h = Integer.parseInt(st.nextToken());
			flag = false;
			if(k%2 == 0) { //왼쪽
				for(int j = 0; j < M; j++) {
					if(map[N-h][j] == 'x') {
						map[N-h][j] = '.';
						flag = true;
						break;
					}
				}
			}else { //오른쪽
				for(int j = M-1; j >= 0; j--) {
					if(map[N-h][j] == 'x') {
						map[N-h][j] = '.';
						flag=true;
						break;
					}
				}
			}
			//건드리는게 없으면 수행 x
			if(!flag)
				continue;
			
			visited = new boolean[N][M];
			loop:
			for(int i = N-1; i >=  0; i--) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 'x' && !visited[i][j])
						if(!bfs(i, j)) 
							break loop;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static class Pos{
		int x,y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
```

## 문제 풀이
* 좋은 시뮬레이션 문제이다.
* 창영이와 상근이가 던지는 막대기를 순서에 맞게 왼쪽과 오른쪽에서 미네랄을 제거해준다.
* 막대기가 미네랄에 닿지 않는다면 밑 반복문을 실행하지 않고 다시 막대기를 던진다.
* 1.미네랄에 닿는다면 가장 밑부분부터 확인하며 바닥과 닿지 않는 클러스터를 찾는다.
* 2.바닥과 닿지 않는 클러스터는 바닥과 닿아있는(visited = true)인 미네랄을 만날때까지 몇번 내릴 수 있는지 cnt해준다.
* 3.cnt변수를 통해서 클러스터를 밑으로 내려준다.
* 막대기를 던진 횟수만큼 1,2,3을 반복해준다.