package week13.추가;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_1941_소문난_칠공주 {
	static int size;
	static int[] temp;
	static char[][] map = new char[5][5];
	static ArrayList<Pos> al = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 5; i++) {
			String s = br.readLine();
			for(int j = 0; j < 5; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'S')
					al.add(new Pos(i, j));
			}
		}
		size = al.size();
		temp = new int[4];
	}
	
	static void check() {
		boolean[] visited = new boolean[size];
		
		
	}
	
	static void combi(int idx, int cnt) {
		if(cnt == 4) { //4명이 S파이면
			return;
		}
		
		for(int i = idx; i < size; i++) {
			temp[cnt] = i;
			combi(i+1, cnt+1);
		}
	}
	
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
