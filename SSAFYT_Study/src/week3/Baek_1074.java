package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Z
public class Baek_1074 {
	static int N, R, C, X;
	static int answer = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		X = (int)Math.pow(2, N);
		dfs(X);
	}
	
	static void dfs(int n) {
		int size = n;
		if(n == 1) {
			System.out.println(answer);
			return;
		}
		n/=2;
		//1사분면
		if(R < n && C < n) {
			dfs(n);
		}
		
		//2사분면
		else if(R < n && C >= n) {
			answer += size*size/4;
			C-=n;
			dfs(n);
		}
		
		//3사분면
		else if(R>=n && C < n) {
			R-=n;
			answer += size*size/2;
			dfs(n);
		}
		
		//4사분면
		else if(R>=n && C >= n) {
			R-=n;
			C-=n;
			answer += size*size/4*3;
			dfs(n);
		}
	}
}

