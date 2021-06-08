package week16;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Programmers_경주로_건설 {
	PriorityQueue<Pos> pq = new PriorityQueue<>();
	int[] X = {-1,0,1,0};
	int[] Y = {0,1,0,-1};
	int visited[][];
	int N,answer = 0;
    public int solution(int[][] board) {
        N = board.length;
        visited = new int[N][N];
        for(int i = 0; i < N; i++) {
        	Arrays.fill(visited[i], 987654321);
        }
        
        bfs(board);

        return answer;
    }
    
    public void bfs(int[][] board) {
    	pq.offer(new Pos(0,0,1,0));
    	pq.offer(new Pos(0,0,2,0));
    	visited[0][0] = 0;
    	int nx, ny;
    	Pos pos;
    	
    	while(!pq.isEmpty()) {
    		pos = pq.poll();
 
    		if(pos.x == N-1 && pos.y == N-1) {
    			answer = pos.cost;
    			return;
    		}
    		
    		for(int d = 0; d < 4; d++) {
    			nx = pos.x + X[d];
    			ny = pos.y + Y[d];
    			
    			if(nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == 1)
    				continue;
    			
    			if(pos.dir == d && visited[nx][ny] + 500>= pos.cost){
                    visited[nx][ny] = pos.cost+100;
    				pq.offer(new Pos(nx,ny,d, pos.cost+100));
                }
    			else if(pos.dir != d && visited[nx][ny] >= pos.cost + 600){
                    visited[nx][ny] = pos.cost+600;
    				pq.offer(new Pos(nx,ny,d,pos.cost+600));
                }
    		}
    	}
    }
    
    class Pos implements Comparable<Pos>{
    	int x, y,dir,cost;

		public Pos(int x, int y, int dir, int cost) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cost = cost;
		}

		@Override
		public int compareTo(Pos o) {
			return this.cost - o.cost;
		}
    }
}
