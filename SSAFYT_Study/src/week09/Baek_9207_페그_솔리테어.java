package week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_9207_페그_솔리테어 {
	static int N, M, minCnt, minMove, totalPin;
	static char[][] map;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	static ArrayList<Pos> pin;
	
	static class Pos{
		int x, y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static void dfs(int idx, int cnt, int move) {
		if(cnt < minCnt) {
			minCnt = cnt;
			minMove = move;
		}
		
		for(int i = 0; i < pin.size(); i++) {
			Pos p = new Pos(pin.get(i).x, pin.get(i).y);
			if(map[p.x][p.y] != 'o')
				continue;
			
			for(int d = 0; d < 4; d++) {
				int nx = p.x + X[d];
				int ny = p.y + Y[d];

				if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny]!='o')
					continue;
				
				if((nx + X[d])< 0 || (ny + Y[d])< 0 || (nx + X[d])>= N || (ny + Y[d])>= M || map[nx + X[d]][ny + Y[d]]!='.') {
					continue;
				}
				map[p.x][p.y] = '.';
				map[nx][ny] = '.';
				map[nx+X[d]][ny+Y[d]] = 'o';
				pin.get(i).x = nx+X[d];
				pin.get(i).y = ny+Y[d];
				dfs(i +1, cnt-1, move + 1);
				pin.get(i).x = p.x;
				pin.get(i).y = p.y;
				map[p.x][p.y] = 'o';
				map[nx][ny] = 'o';
				map[nx+X[d]][ny+Y[d]] = '.';
			}
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		N = 5; M = 9;
		map = new char[N][M];
		while(t-- > 0) {
			pin = new ArrayList<>();
			minCnt = Integer.MAX_VALUE;
			minMove = Integer.MAX_VALUE;
			totalPin = 0;
			for(int i = 0; i < N; i++) {
				String s = br.readLine();
				for(int j = 0; j < s.length(); j++) {
					map[i][j] = s.charAt(j);
					
					if(map[i][j] == 'o') {
						pin.add(new Pos(i, j));
					}
				}
			}
			dfs(0,pin.size(),0);
			sb.append(minCnt).append(" ").append(minMove).append("\n");
			br.readLine();
		}
		System.out.println(sb.toString());
	}
}
