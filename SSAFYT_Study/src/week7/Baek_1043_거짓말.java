package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1043_거짓말 {
	static int N, M, ans = 0;
	static boolean[] know;
	static int[][] party;
	static boolean[][] people;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사람의 수
		M = Integer.parseInt(st.nextToken()); // 파티의 수
		know = new boolean[N + 1];
		people = new boolean[N+1][N+1];
		
		st = new StringTokenizer(br.readLine());
		int true_people = Integer.parseInt(st.nextToken());
		for (int i = 0; i < true_people; i++) { // 진실을 아는 사람들
			know[Integer.parseInt(st.nextToken())] = true;
		}

		party = new int[M][];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int party_num = Integer.parseInt(st.nextToken());
			party[i] = new int[party_num];
			for(int j = 0; j < party_num; j++) {
				party[i][j] = Integer.parseInt(st.nextToken());

			}
			for(int j = 0; j < party_num-1; j++) {
				people[party[i][j+1]][party[i][j]] = true;
				people[party[i][j]][party[i][j+1]] = true;
			}
		}
		
		for(int i = 1; i <= N; i++) {
			if(know[i])
				dfs(i);
		}
		
		
		for(int i = 0; i <M; i++) {
			if(!know[party[i][0]])
				ans++;
		}
		System.out.println(ans);
	}

	static void dfs(int idx) {
		for(int i = 1; i <= N; i++) {
			if(people[idx][i] && !know[i]) {
				know[i] = true;
				dfs(i);
			}
		}
	}
}
