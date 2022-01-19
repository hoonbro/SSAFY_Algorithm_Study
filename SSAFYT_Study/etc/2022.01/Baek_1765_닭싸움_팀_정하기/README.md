# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/15.svg" width="30"> [1765] 닭싸움 팀 정하기
> https://www.acmicpc.net/problem/1765
## 알고리즘 분류
> 그래프 탐색, DFS

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_1765_닭싸움_팀_정하기 {
	static int N, M;
	static boolean[] visited;
    //친구 관계 인접 리스트
	static ArrayList<ArrayList<Integer>> friends = new ArrayList<ArrayList<Integer>>();
    //원수 관계 인접 리스트
	static ArrayList<ArrayList<Integer>> enemies = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for(int i = 0; i <= N; i++) {
			friends.add(new ArrayList<>());
			enemies.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			char type = st.nextToken().charAt(0);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(type == 'F') {
				friends.get(a).add(b);
				friends.get(b).add(a);
			}else {
				enemies.get(a).add(b);
				enemies.get(b).add(a);
			}
		}
		
		for(int i = 1; i <= N; i++) {
			visited = new boolean[N+1];
			findFriends(i, i, 0);
		}
		
		visited = new boolean[N+1];
		int cnt = 0;
		for(int i = 1; i <= N; i++) {
			if(visited[i])
				continue;
			
			cnt++;
			makeTeam(i);
		}
		
		System.out.println(cnt);
	}
	
    //팀 갯수 세는 메서드
	static void makeTeam(int idx) {
		if(visited[idx])
			return;
		
		visited[idx] = true;
		for(int friend : friends.get(idx)) {
			makeTeam(friend);
		}
	}
	
    //원수 관계로부터 친구사이를 찾는 메서드
	static void findFriends(int start, int idx, int depth) {
		if(visited[idx])
			return;
		
		if(depth == 2) {
			friends.get(start).add(idx);
			return;
		}
		
		visited[idx] = true;
		for(int enemy : enemies.get(idx)) {
			findFriends(start, enemy, depth+1);
		}
	}
}

```

## 문제 풀이
1. 두개의 인접 리스트를 사용해 친구 사이, 원수 사이를 저장해 놓는다.
2. findFriends 메서드를 재귀로 사용해 원수 관계로부터 친구 사이를 찾고 친구사이 인접리스트에 저장한다.
3. makeTeam 메서드를 재귀로 돌며 팀의 갯수를 센다.
