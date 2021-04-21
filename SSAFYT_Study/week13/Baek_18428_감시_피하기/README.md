# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/10.svg" width="30"> [18428] 감시 피하기
## 문제 링크
> https://www.acmicpc.net/problem/18428
## 알고리즘 분류
> 백트래킹, 조합

## 코드
```java
package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_18428_감시_피하기 {
	static int N, tCnt;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	static char[][] map;
	static ArrayList<Teacher> al;
//	static Teacher[]  teachers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
//		teachers = new Teacher[6];
		al = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if(map[i][j] == 'T')
					al.add(new Teacher(i, j));
//					teachers[tCnt++] = new Teacher(i, j);
			}
		}
		tCnt = al.size();
		makeWall(0,0,0);
		System.out.println("NO");
	}
	
	static boolean check() {
		Teacher t;
		int nx,ny;
		for(int i = 0; i < tCnt; i++) {
			t = al.get(i);
			
			for(int d = 0; d < 4; d++) {
				nx = t.x;
				ny = t.y;
				while(true) {
					nx += X[d];
					ny += Y[d];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 'O')
						break;
					if(map[nx][ny] == 'S')
						return false;
				}
			}
		}
		return true;
	}
	
	static void makeWall(int x, int y, int cnt) {
		if(cnt == 3) {
			if(check()) {
				System.out.println("YES");
				System.exit(0);
			}
			return;
		}
		
		for(int i = x; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] != 'X')
					continue;
				
				map[i][j] = 'O';
				makeWall(i, j, cnt+1);
				map[i][j] = 'X';
			}
		}
	}
	
	static class Teacher{
		int x, y;

		public Teacher(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

```

## 문제 풀이
* 조합을 이용한 백트래킹 문제이다.
* 조합을 통해 벽을 만든 후에 check메서드를 통해 선생님들의 시야에 학생이 들어오는지를 확인한다.
* 학생들이 모든 선생님들의 시야를 피한다면 YES를 출력하고 코드를 종료시킨다.

