# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [6987] 월드컵
> https://www.acmicpc.net/problem/6987
## 알고리즘 분류
> 백트래킹

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baek_6987_월드컵 {
	static int[][] team, match;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 0; t < 4; t++) {
			st = new StringTokenizer(br.readLine());

			team = new int[6][3];
			match = new int[15][2];
			flag = false; 
			
			int total = 0;
			
			int idx = 0;
			for(int i = 0; i < 5; i++) {
				for(int j = i+1; j < 6; j++) {
					match[idx][0] = i;
					match[idx++][1] = j;
				}
			}
			
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					team[i][j] = Integer.parseInt(st.nextToken());
					total += team[i][j];
				}
			}
			
			if (total != 30) {
				sb.append(0).append(" ");
				continue;
			}
			
			dfs(0);
			if(flag) {
				sb.append(1).append(" ");
			}else {
				sb.append(0).append(" ");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
	}

	static void dfs(int idx) {
		if(flag) 
			return;
		
		if (idx == 15) {
			flag = true;
			return;
		}

		int a = match[idx][0];
		int b = match[idx][1];
		
		//a승
		if(team[a][0] > 0 && team[b][2] > 0) {
			team[a][0]--;
			team[b][2]--;
			dfs(idx+1);
			team[a][0]++;
			team[b][2]++;
		}
		
		//무승부
		if(team[a][1] > 0 && team[b][1] > 0) {
			team[a][1]--;
			team[b][1]--;
			dfs(idx+1);
			team[a][1]++;
			team[b][1]++;
		}
		
		//b승
		if(team[a][2] > 0 && team[b][0] > 0) {
			team[a][2]--;
			team[b][0]--;
			dfs(idx+1);
			team[a][2]++;
			team[b][0]++;
		}
	}
}
```

## 문제 풀이
1. 2차원 배열 match에 경기의 경우의 수를 저장한다.
1. 2차원 배열 team에 각 팀의 승/무/패를 저장한다.
1. 총 게임 횟수가 30회가 아니라면 불가능한 경우의 수이므로 0을 출력
1. 재귀 함수를 돌며 2팀 a,b가 각각 승/무/패를 할 경우를 모두 재귀함수로 다시 돌린다.
1. match가 15회가 된다면 가능한 경우의 수이기 때문에 flag를 true로 바꾼다.
1. 재귀 함수 종료 후 flag를 사용해 1 혹은 0 을 출력해준다.
