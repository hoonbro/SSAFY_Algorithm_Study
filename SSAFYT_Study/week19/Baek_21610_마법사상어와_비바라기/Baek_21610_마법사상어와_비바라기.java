package week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_21610_마법사상어와_비바라기 {
	static int N, M;
	static int[][] map;
	static int[] X = {0,-1,-1,-1,0,1,1,1};
	static int[] Y = {-1,-1,0,1,1,1,0,-1};
	static boolean[][] check;
	static ArrayList<Pos> cloud = new ArrayList<>();
	static ArrayList<Pos> up = new ArrayList<>();
	
	static void rainMagic(int d, int s) {
		for(int i = 0; i < cloud.size(); i++) {
			Pos p = cloud.get(i);
			
			int nx = (p.x + X[d]*s)%N;
			int ny = (p.y + Y[d]*s)%N;
			
			if(nx < 0)
				nx +=N;
			if(ny < 0)
				ny += N;
			
			map[nx][ny]++;
			up.add(new Pos(nx, ny));
			check[nx][ny] = true;
		}
		cloud.clear();
		
		
		for(int i = 0; i < up.size(); i++) {
			Pos p = up.get(i);
			
			for(int j = 1; j < 8; j+=2) {
				int nx = p.x + X[j];
				int ny = p.y + Y[j];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				
				if(map[nx][ny] != 0)
					map[p.x][p.y]++; 
			}
		}
		
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] < 2 || check[i][j])
					continue;
				
				cloud.add(new Pos(i, j));
				map[i][j] -= 2;
			}
		}
		up.clear();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cloud.add(new Pos(N-1, 0));
		cloud.add(new Pos(N-1, 1));
		cloud.add(new Pos(N-2, 0));
		cloud.add(new Pos(N-2, 1));
		
		for(int i = 0; i < M; i++) {
			check = new boolean[N][N];
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			
			rainMagic(d, s%N);
		}
		
		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				ans += map[i][j];
			}
		}
		System.out.println(ans);
	}
	
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
