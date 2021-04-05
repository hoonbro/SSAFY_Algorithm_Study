package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_16197_두_동전 {
	static int N, M, ans = 0;
	static char[][] map;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	static Queue<Pos> q = new LinkedList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		String s;
		for(int i = 0; i < N; i++) {
			s = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'o') {
					q.offer(new Pos(i, j, 0));
				}
			}
		}
		if(!bfs())
			ans = -1;
		System.out.println(ans);
	}
	
	static boolean bfs() {
		Pos coin1, coin2;
		
		int nx1, nx2, ny1, ny2;
		while(!q.isEmpty()) {
			coin1 = q.poll();
			coin2 = q.poll();
			
			if(coin1.cnt >= 10)
				return false;
			
			for(int i = 0; i < 4; i++) {
				nx1 = coin1.x + X[i];
				ny1 = coin1.y + Y[i];
				nx2 = coin2.x + X[i];
				ny2 = coin2.y + Y[i];
				
				if(!check(nx1,ny1) && !check(nx2,ny2))
					continue;
				if((!check(nx1,ny1) && check(nx2,ny2)) || (check(nx1,ny1) && !check(nx2,ny2))) {
					ans = coin1.cnt+1;
					return true;
				}
				
				if(map[nx1][ny1] == '#' && map[nx2][ny2] == '#')
					continue;
				
				if(map[nx1][ny1] != '#') {
					q.offer(new Pos(nx1, ny1, coin1.cnt+1));
				}else {
					q.offer(new Pos(coin1.x, coin1.y, coin1.cnt+1));
				}
				
				if(map[nx2][ny2] != '#') {
					q.offer(new Pos(nx2, ny2, coin2.cnt+1));
				}else {
					q.offer(new Pos(coin2.x, coin2.y, coin2.cnt+1));
				}
			}
		}
		return false;
	}
	
	static boolean check(int x, int y) {
		if(x < 0 || y < 0 || x>=N || y>=M)
			return false;
		return true;
	}

	static class Pos{
		int x, y, cnt;

		public Pos(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}
