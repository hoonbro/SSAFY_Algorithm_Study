package test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//연산자 끼워넣기
public class Baek_14888 {
	static int N;
	static int[] arr, oper, calc;
	static int min = 1000000001;
	static int max = -1000000001;
	static boolean[] check;
	
	//순열
	static void dfs(int cnt) {
		if(cnt == N-1) {
			int t = result();
			max = Math.max(max, t);
			min = Math.min(min, t);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(oper[i] != 0) {
				calc[cnt] = i;
				oper[i]--;
				dfs(cnt+1);
				oper[i]++;
			}
		}
	}
	
	static int result() {
		int res = 0;
		int[] temp = new int[arr.length];
		for(int i = 0; i < arr.length; i++) {
			temp[i] = arr[i];
		}

		for(int i = 0; i < calc.length; i++) {
			switch(calc[i]) {
				case 0: //더하기
					res = temp[i] + temp[i+1];
					temp[i+1] = res;
					break;
				case 1: //빼기
					res = temp[i] - temp[i+1];
					temp[i+1] = res;
					break;
				case 2: //곱하기
					res = temp[i] * temp[i+1];
					temp[i+1] = res;
					break;
				case 3: //나누기
					res = temp[i] / temp[i+1];
					temp[i+1] = res;
					break;
			}
		}
		return res;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		oper = new int[4];
		calc = new int[N-1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0);
		System.out.println(max);
		System.out.println(min);
	}

}
