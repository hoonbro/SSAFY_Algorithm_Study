package etc._2021_12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baek_6987_월드컵 {
	static int[][] team, match;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 0; t < 4; t++) {
			st = new StringTokenizer(br.readLine());

			team = new int[6][3];
			match = new int[15][2];
			flag = false; 
			
			int total = 0;
			
			int idx = 0;
			for(int i = 0; i < 5; i++) {
				for(int j = i+1; j < 6; j++) {
					match[idx][0] = i;
					match[idx++][1] = j;
				}
			}
			
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					team[i][j] = Integer.parseInt(st.nextToken());
					total += team[i][j];
				}
			}
			
			if (total != 30) {
				sb.append(0).append(" ");
				continue;
			}
			
			dfs(0);
			if(flag) {
				sb.append(1).append(" ");
			}else {
				sb.append(0).append(" ");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
	}

	static void dfs(int idx) {
		if(flag) 
			return;
		
		if (idx == 15) {
			flag = true;
			return;
		}

		int a = match[idx][0];
		int b = match[idx][1];
		
		//a승
		if(team[a][0] > 0 && team[b][2] > 0) {
			team[a][0]--;
			team[b][2]--;
			dfs(idx+1);
			team[a][0]++;
			team[b][2]++;
		}
		
		//무승부
		if(team[a][1] > 0 && team[b][1] > 0) {
			team[a][1]--;
			team[b][1]--;
			dfs(idx+1);
			team[a][1]++;
			team[b][1]++;
		}
		
		//b승
		if(team[a][2] > 0 && team[b][0] > 0) {
			team[a][2]--;
			team[b][0]--;
			dfs(idx+1);
			team[a][2]++;
			team[b][0]++;
		}
	}
}
