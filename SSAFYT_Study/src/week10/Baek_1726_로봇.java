package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_1726_로봇 {
	static int N, M;
	//동 서 남 북
	static int[] X = {0, 0,0,1,-1};
	static int[] Y = {0, 1,-1,0,0};
	static int[][] map;
	static boolean[][][] visited;
	static Robot start, end;
	static class Robot{
		int x, y, dir, cost;

		public Robot(int x, int y, int dir, int cost) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cost = cost;
		}
	}
	
	//visited 3차원배열을써서 방향에따른 visited를 체크해놓자!
	static void bfs() {
		Queue<Robot> q = new LinkedList<>();
		q.offer(start);
		map[start.x][start.y] = 1;
		Robot robot;
		int nx,ny;
		while(!q.isEmpty()) {
			robot = q.poll();
			if(robot.x == end.x && robot.y == end.y && robot.dir == end.dir) {
				System.out.println(robot.cost);
				return;
			}
			
			//앞으로 3번가지 움직이기
			for(int i = 1; i < 4; i++) {
				nx = robot.x + X[robot.dir]*i;
				ny = robot.y + Y[robot.dir]*i;
				if(nx <= 0 || ny <= 0 || nx > N || ny > M)
					continue;
				
				if(map[nx][ny] == 1)
				q.offer(new Robot(nx,ny,robot.dir, robot.cost+1));
			}
			
			//방향 바꾸기
			for(int d = 1; d <= 4; d++) {
				if(d == robot.dir || map[robot.x][robot.y] ==2)
					continue;
				
				nx = robot.x + X[d];
				ny = robot.y + Y[d];
				if(nx <= 0 || ny <= 0 || nx > N || ny > M || map[nx][ny] != 0) {
					if(robot.x != end.x && robot.y != end.y)
						continue;
				}
				if((robot.dir == 1 && d == 2) || (robot.dir == 2 && d == 1) || (robot.dir == 3 && d == 4) || (robot.dir == 4 && d == 3)) 
					q.offer(new Robot(robot.x, robot.y, d, robot.cost+2));
				else {
					q.offer(new Robot(robot.x, robot.y, d, robot.cost+1));
				}					
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1][5];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		start = new Robot(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()), 0);
		st = new StringTokenizer(br.readLine());
		end = new Robot(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()), 0);
		bfs();
	}
}
