package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//톱니바퀴
public class Baek_14891 {
	static int[][] wheel;
	static boolean isRotate[];
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		wheel = new int[5][8];
		
		
		for (int i = 1; i < 5; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				wheel[i][j] = s.charAt(j) - '0';
			}
		}

		n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			isRotate = new boolean[5];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			check(w, dir);
			rotate(w, dir);
		}
		
		System.out.println(score());
	}
	
	static int score() {
		int sum = 0;
		for(int i = 1; i < 5; i++) {
			int num = wheel[i][0];
			
			if(num==1) 
				sum += Math.pow(2, i-1);
			
		}
		return sum;
	}

	static void check(int w, int dir) {
		dir *= -1;
		isRotate[w] = true;
		if(w == 1) {
			if(wheel[w][2] != wheel[w+1][6] && !isRotate[w+1]) {
				check(w+1, dir);
				rotate(w+1, dir);
			}
		}
		else if(w == 4) {
			if(wheel[w][6] != wheel[w-1][2] && !isRotate[w-1]) {
				check(w-1, dir);
				rotate(w-1, dir);
			}
		}
		else {
			if(wheel[w][2] != wheel[w+1][6] && !isRotate[w+1]) {
				check(w+1, dir);
				rotate(w+1, dir);
			}
			if(wheel[w][6] != wheel[w-1][2] && !isRotate[w-1]) {
				check(w-1, dir);
				rotate(w-1, dir);
			}
		}
	}
	
	static void rotate(int w, int dir) {
		int temp = wheel[w][7];
		if (dir == 1) {
			for (int i = 7; i > 0; i--) {
				wheel[w][i] = wheel[w][i - 1];
			}
			wheel[w][0] = temp;
			return;
		}
		
		temp = wheel[w][0];
		for(int i = 0; i < 7; i++) {
			wheel[w][i] = wheel[w][i+1];
		}
		wheel[w][7] = temp;
	}
}
