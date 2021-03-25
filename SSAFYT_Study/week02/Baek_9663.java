package week02;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//왜 Bfs로 풀면 안대지?
//N-Queen
public class Baek_9663 {
	static int n;
	static int answer = 0;
	static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n];
		dfs(0);
		System.out.println(answer);
	}

	static void dfs(int cnt) {
		if(cnt == n) {
			answer++;
			return;
		}
		
		for(int i = 0; i < n; i++) {
			map[cnt] = i;
			if(check(cnt)) {
				dfs(cnt+1);
			}
		}
	}
	
	static boolean check(int cnt) {
		for(int i = 0; i < cnt; i++) {
			if(map[i] == map[cnt])
				return false;
			
			//대각선
			if(Math.abs(i - cnt) == Math.abs(map[i] - map[cnt]))
				return false;
		}
		return true;
	}
}
