package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_15685_드래곤_커브 {
	static int N;
	static int[][] cmd,map;
	static int[] X = { 0, -1, 0, 1 };
	static int[] Y = { 1, 0, -1, 0 };
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		cmd = new int[N][4];
		map = new int[101][101];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				cmd[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i< cmd.length; i++) {
			dragonCurve(i);
		}
		System.out.println(square());
	}
	
	static int square() {
		int cnt = 0;
		
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(map[i][j] == 1 && map[i+1][j] == 1 && map[i][j+1] ==1 && map[i+1][j+1] ==1) 
					cnt++;
			}
		}
		
		return cnt;
	}
	
	//방향 (dir+1)%4
	static void dragonCurve(int idx) {
		ArrayList<Integer> al = new ArrayList<>();
		int y = cmd[idx][0];
		int x = cmd[idx][1];
		int dir = cmd[idx][2];
		int g = cmd[idx][3];

		map[x][y] = 1;
		x += X[dir];
		y += Y[dir];
		map[x][y] = 1;
		al.add((dir+1)%4);
		while(g-- > 0) {
			for(int i = al.size()-1; i >= 0; i--) {
				x += X[al.get(i)];
				y += Y[al.get(i)];
				map[x][y] = 1;
				al.add((al.get(i)+1)%4);
			}
		}
	}
}
