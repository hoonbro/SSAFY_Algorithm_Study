package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://minhamina.tistory.com/67
public class Baek_19236_청소년상어 {
	static int[] X = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] Y = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int max = 0;
	
	static class Pos{
		int x,y,num,dir;
		boolean alive;
		
		Pos(int x, int y, int num, int dir, boolean alive){
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
			this.alive = alive;
		}
	}
	
	static void eat(int[][] map, Pos[] arr, Pos now, int sum) {
		int[][] temp = new int[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		Pos[] fish = new Pos[17];
		for(int i = 1; i < fish.length; i++) {
			Pos p = arr[i];
			fish[i] = new Pos(p.x,p.y,p.num,p.dir,p.alive);
		}
		
		move(temp, fish);
		
		//상어가 물고기 먹음
		for(int i = 1; i <= 3; i++) {
			
			int d = now.dir;
			int nx = now.x + X[d]*i; 
			int ny = now.y + Y[d]*i; 
			
			if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || temp[nx][ny] == 0)
				continue;
			
			Pos shark = new Pos(nx, ny, temp[nx][ny], fish[temp[nx][ny]].dir, true);
			
			int eat = temp[nx][ny];
			
			fish[eat].alive = false;
			temp[nx][ny] = -1;
			temp[now.x][now.y] = 0;
			eat(temp, fish, shark, sum + eat);
			
			temp[nx][ny] = eat;
			temp[now.x][now.y] = -1;
			fish[eat].alive = true;
		}
		max = Math.max(max,  sum);
	}
	
	
	//물고기 이동
	static void move(int[][] map, Pos[] arr) {
		for(int i = 1; i < arr.length; i++) {
			Pos p = arr[i];
			
			if(!p.alive)
				continue;
			int d = p.dir;
			
			for(int j = 0; j < 8; j++) {
				d %=8;
				arr[i].dir = d;
				int nx = p.x + X[d];
				int ny = p.y + Y[d];
				
				if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || map[nx][ny] == -1) {
					d++;
					continue;
				}
				
				if(map[nx][ny] == 0) {
					map[p.x][p.y] = 0;
					p.x = nx;
					p.y = ny;
					map[nx][ny] = i;
				}else {
					int temp = arr[map[nx][ny]].num;
					arr[temp].x = arr[i].x;
					arr[temp].y = arr[i].y;
					map[arr[temp].x][arr[temp].y] = temp;
					
					p.x = nx;
					p.y = ny;
					map[nx][ny] = i;
				}
				
				break;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] map = new int[4][4];
		Pos[] fish = new Pos[17];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken())-1;
				map[i][j] = a;
				fish[a] = new Pos(i,j,a,b,true);
			}
		}
		
		Pos shark;
		shark = new Pos(0,0, map[0][0], fish[map[0][0]].dir, true);
		fish[map[0][0]].alive = false;
		map[0][0] = -1;
		
		eat(map, fish, shark, shark.num);
		System.out.println(max);
	}
}
