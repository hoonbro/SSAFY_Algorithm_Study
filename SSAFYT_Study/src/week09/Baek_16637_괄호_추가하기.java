package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_16637_괄호_추가하기 {
	static int N, max = Integer.MIN_VALUE;
	static int[] arr;
	static char[] cals;
	static String S;
	
	static void dfs(int idx, int calIdx, int val) {
		if(idx >= arr.length) {
			max = Math.max(max, val);
			return;
		}
		dfs(idx+1, calIdx+1, cal(val, arr[idx], calIdx));
		if(idx < arr.length-1 )
			dfs(idx+2, calIdx+2, cal(val, cal(arr[idx], arr[idx+1], calIdx+1), calIdx));
	}
	
	static int cal(int a, int b, int calIdx) {
		int result = 0;
		switch (cals[calIdx]) {
		case '+':
			result = a + b;
			break;
		case '-':
			result = a - b;
			break;
		case '*':
			result = a * b;
			break;
		}
		return result;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = br.readLine();
		arr = new int[(N/2)+1];
		cals = new char[N];
		int i = 0;
		for(int j = 0; j < S.length()-1; j++) {
			arr[i] = S.charAt(j++) - '0';
			cals[i++] = S.charAt(j);
		}
		arr[i] = S.charAt(N-1) - '0';
		dfs(1,0,arr[0]);
		System.out.println(max);
	}
}
