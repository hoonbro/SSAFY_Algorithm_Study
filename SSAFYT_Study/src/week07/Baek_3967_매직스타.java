package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//구현
public class Baek_3967_매직스타 {
	static char[][] map;
	static boolean[] visited;
	static ArrayList<Pos> al;
	
	static class Pos{
		int x;
		int y;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	static void dfs(int idx) {
		if(idx == al.size()) {
			if(isAnswer()) {
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 9; j++) {
						sb.append(map[i][j]);
					}
					sb.append("\n");
				}
				System.out.println(sb.toString());
				System.exit(0);
			}
			return;
		}
		
		for(int i = 0; i < 12; i++) {
			Pos pos = al.get(idx);

			if(visited[i])
				continue;
			
			visited[i] = true;
			map[pos.x][pos.y] = (char) (65+i);
			dfs(idx+1);
			visited[i] = false;
			map[pos.x][pos.y] = 'x';
		}
	}
	
	static boolean isAnswer() {
		if(map[0][4] + map[1][3] + map[2][2] + map[3][1] -256 != 26) {
			return false;
		}
		else if(map[3][1] + map[3][3] + map[3][5] + map[3][7] - 256 != 26) {
			return false;
		}
		else if(map[0][4] + map[1][5] + map[2][6] + map[3][7] - 256 != 26)
			return false;
		else if(map[1][1] + map[1][3] + map[1][5] + map[1][7] - 256 != 26)
			return false;
		else if(map[1][1] + map[2][2] + map[3][3] + map[4][4] - 256 != 26)
			return false;
		else if(map[4][4] + map[3][5] + map[2][6] + map[1][7] - 256 != 26) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[5][9];
		visited = new boolean[12];
		al = new ArrayList<>();
		
		for(int i = 0; i < 5; i++) {
			String s = br.readLine();
			for(int j = 0; j < 9; j++) {
				map[i][j] = s.charAt(j);
				
				if(map[i][j] == 'x')
					al.add(new Pos(i, j));
				else if(map[i][j] != '.')
					visited[map[i][j] -'A'] = true;
			}
		}
		
		dfs(0);
	}
}
