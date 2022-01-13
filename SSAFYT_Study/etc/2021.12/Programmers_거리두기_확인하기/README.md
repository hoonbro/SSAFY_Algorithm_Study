# [Level2] 괄호 회전하기
## 문제 링크
> https://programmers.co.kr/learn/courses/30/lessons/81302
## 알고리즘 분류
> BFS

## 코드
```java
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ex2 {
	static int N, M;
	static char[][] map;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	static Queue<Pos> q = new LinkedList<>();
	static boolean[][] visited;
	static boolean bfs(int x, int y) {
		q.clear();
		q.offer(new Pos(x, y, 0));
		int nx,ny;
		Pos pos;
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			pos = q.poll();
			for(int d = 0; d < 4; d++) {
				nx = pos.x + X[d];
				ny = pos.y + Y[d];
				
				if(nx < 0 || ny < 0 || nx >= N || ny>= M || visited[nx][ny] || map[nx][ny] == 'X')
					continue;
				
				visited[nx][ny] = true;
				if(map[nx][ny] == 'P') {
					return false;
				}
				
				if(pos.len < 1) {
					q.offer(new Pos(nx, ny, pos.len+1));
				}
			}
		}
		return true;
	}
	
    public static int[] solution(String[][] places) {
        N = places.length;
        M = places[0].length;
        int[] answer = new int[N];
        Arrays.fill(answer, 1);
        map = new char[N][N];
        
        for(int k = 0; k < N; k++) {
	        for(int i = 0; i < N; i++) {
	        	for(int j = 0; j < M; j++) {
	        		map[i][j] = places[k][i].charAt(j);
	        	}
	        }
	        
	        visited = new boolean[N][N];
	        
	        loop:
	        for(int i = 0; i < N; i++) {
	        	for(int j = 0; j < M; j++) {
	        		if(map[i][j] == 'P') {
	        			if(bfs(i, j)) {
	        				answer[k] = 1;
	        			}else {
	        				answer[k] = 0;
	        				break loop;
	        			}
	        		}
	        	}
	        }
        }
        for(int i = 0; i < N; i++) {
        	System.out.println(answer[i]);
		}
        return answer;
    }
    
    static class Pos{
    	int x, y, len;

		public Pos(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}
    }
}

```

