package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//dfs
public class Baek_16953_AB {
	static long A, B, ans = 987654321;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		dfs(A, 0);

		if(ans == 987654321) {
			System.out.println(-1);
		}
		else
			System.out.println(ans+1);
			
	}
	
	static void dfs(long a, long cnt) {
		if(a == B) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		if(a > B)
			return;
			
		dfs(a*2, cnt+1);
		dfs((a*10)+1, cnt+1);
	}
}
