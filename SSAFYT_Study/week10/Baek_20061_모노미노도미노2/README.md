# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/14.svg" width="30"> [20061] 모노미노도미노2
## 문제 링크
> https://www.acmicpc.net/problem/20061
## 알고리즘 분류
> KMP알고리즘

## 코드
```java
package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_20061_모노미노도미노2 {
	static int N, score = 0, total = 0;
	static int[][] green, blue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		green = new int[6][4];
		blue = new int[4][6];
		
		int t, x, y;
		for(int k = 0; k < N; k++) {
			st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			moveGreen(t,x,y);
			moveBlue(t,x,y);
		}
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 4; j++) {
				if(green[i][j] == 1)
					total++;
				if(blue[j][i] == 1)
					total++;
			}
		}
		System.out.println(score);
		System.out.println(total);
	}
	
	static void moveBlue(int t, int x, int y) {
		int x1,x2, ny = 0;
		switch (t) {
		case 1: //크기 1
			for(ny = 2; ny < 6; ny++) {
				if(blue[x][ny] == 1) {
					break;
				}
			}
			blue[x][ny-1] = 1;
			break;
		case 2: // 가로2
			for(ny = 2; ny < 6; ny++) {
				if(blue[x][ny] == 1) {
					break;
				}
			}
			blue[x][ny-1] = 1;
			blue[x][ny-2] = 1;

			break;
		case 3: // 세로 2
			x1 = x;
			x2 = x+1;
			for(ny = 2; ny < 6; ny++) {
				if(blue[x1][ny] == 1 || blue[x2][ny] == 1) {
					break;
				}
			}
			blue[x1][ny-1] = 1;
			blue[x2][ny-1] =1;
			break;
		}
		checkBlue();
	}
	
	static void checkBlue() {
		//그냥 blue
		for(int i = 5; i >= 2; i--) {
			int len = 0;
			for(int j = 0; j < 4; j++) {
				if(blue[j][i] == 1)
					len++;
			}
			if(len != 4) 
				continue;
			
			score++;
			for(int k = i; k >= 1; k--) {
				for(int j = 0; j < 4; j++) {
					blue[j][k] = 0;
					blue[j][k] = blue[j][k-1];
					blue[j][k-1] = 0;
				}
			}
			i++;
		}
		
		//lightblue
		int cnt= 0;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 4; j++) {
				if(blue[j][i] == 1) {
					cnt++;
					break;
				}
			}
		}
		if(cnt != 0)
			lightBlue(cnt);
	}
	
	static void lightBlue(int cnt) {
		for(int i = 5; i > 1; i--) {
			for(int j = 0; j < 4; j++) { // 비워주고
				blue[j][i] = 0;
				blue[j][i] = blue[j][i-cnt];
				blue[j][i-cnt] = 0;
			}
		}
	}
	
	static void moveGreen(int t, int x, int y) {
		int y1,y2, nx = 0;
		switch (t) {
		case 1: //크기 1
			for(nx = 2; nx < 6; nx++) {
				if(green[nx][y] == 1) {
					break;
				}
			}
			green[nx-1][y] = 1;
			break;
		case 2: // 가로2
			y1 = y;
			y2 = y+1;
			for(nx = 2; nx < 6; nx++) {
				if(green[nx][y1] == 1 || green[nx][y2] == 1) {
					break;
				}
			}
			green[nx-1][y1] = 1;
			green[nx-1][y2] =1;
			break;
		case 3: // 세로 2
			for(nx = 2; nx < 6; nx++) {
				if(green[nx][y] == 1) {
					break;
				}
			}
			green[nx-1][y] = 1;
			green[nx-2][y] = 1;
			break;
		}
		checkGreen();
	}
	
	static void checkGreen() {
		//그냥 green
		for(int i = 5; i >= 2; i--) {
			int len = 0;
			for(int j = 0; j < 4; j++) {
				if(green[i][j] == 1)
					len++;
			}
			if(len != 4) 
				continue;
			
			score++;
			for(int k = i; k >= 1; k--) {
				for(int j = 0; j < 4; j++) {
					green[k][j] = 0;
					green[k][j] = green[k-1][j];
					green[k-1][j] = 0;
				}
			}
			i++;
		}
		
		//lightgreen
		int cnt= 0;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 4; j++) {
				if(green[i][j] == 1) {
					cnt++;
					break;
				}
			}
		}
		if(cnt != 0)
			lightGreen(cnt);
	}
	
	static void lightGreen(int cnt) {
		for(int i = 5; i > 1; i--) {
			for(int j = 0; j < 4; j++) { // 비워주고
				green[i][j] = 0;
				green[i][j] = green[i-cnt][j];
				green[i-cnt][j] = 0;
			}
		}
	}
}
```

## 문제 풀이
* 단순히 구현을 하는 문제이다.
* moveGreen 메서드를 이용해서 배열을 채우고 checkGreen 메서드를 이용해 점수계산 및 배열 삭제를 한다.
* 마지막으로 lightGreen 메서드를 이용해서 배열의 0,1번 행의 특별한 계산을 해준다.
