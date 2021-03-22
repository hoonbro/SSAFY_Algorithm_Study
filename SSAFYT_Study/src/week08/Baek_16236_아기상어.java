package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_16236_아기상어 {
	static int N, shark = 2, fish = 0;
	static int nowX, nowY, minX, minY;
	static int[][] map, dis;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	static Queue<Pos> q = new LinkedList<>();
	
	static class Pos{
		int x, y;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static void bfs() {
		int minDis = Integer.MAX_VALUE;
		
		while(!q.isEmpty()) {
			Pos pos = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = pos.x + X[i];
				int ny = pos.y + Y[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				//갔던곳이거나 물고기가 상어보다 크면
				if(dis[nx][ny] != 0 || map[nx][ny] > shark) 
					continue;
				
				dis[nx][ny] = dis[pos.x][pos.y]+1;

				//먹을 수 있는경우
				if(map[nx][ny] > 0 && map[nx][ny] < shark) {
					if(minDis > dis[nx][ny]) {
						minDis = dis[nx][ny];
						minX=nx;
						minY=ny;
					}
					//먹을 수 있는 생선이 또있음
					else if(minDis == dis[nx][ny]) {
						if(minX == nx) {
							if(minY > ny) { // 위부터
								minX = nx;
								minY = ny;
							}
						}else if(minX> nx) { // 같은 row라면 왼쪽부터
							minX = nx;
							minY = ny;
						}
					}
				}
				q.add(new Pos(nx, ny));
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					nowX=i;
					nowY=j;
					map[i][j] = 0;
				}else if(map[i][j] > 0) {
					fish++;
				}
			}
		}
		
		int ans = 0;
		int eat = 0;
		//물고기를 다먹었을때
		while(fish!=0) {
			dis = new int[N][N];
			minX = -1;
			minY = -1;
			
			q.offer(new Pos(nowX, nowY));
			bfs();
			
			//먹을수 있는게 없을때
			if((minX == -1 && minY== -1))
				break;
			
			ans += dis[minX][minY];
			fish--;
			map[minX][minY] = 0;
			nowX = minX;
			nowY = minY;
			
			if(++eat == shark) {
				shark++;
				eat=0;
			}
		}
		System.out.println(ans);
	}
}
