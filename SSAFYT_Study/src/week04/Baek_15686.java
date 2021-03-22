package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//치킨 배달(조합)
public class Baek_15686 {
	static int N, M;
	static int[][] map;
	static ArrayList<Pos> chicken;
	static ArrayList<Pos> house;
	static Pos[] res;
	static int ans = 987654321;
	
	static class Pos {
		int x;
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // map 크기
		M = Integer.parseInt(st.nextToken()); // 치킨집 최대수
		map = new int[N][N]; 
		res = new Pos[M]; //M개의 치킨집 조합
		house = new ArrayList<>(); //집의 좌표 모음
		chicken = new ArrayList<>(); // 치킨 좌표 모음
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					house.add(new Pos(i, j));
				}
				else if(map[i][j] == 2) {
					chicken.add(new Pos(i, j));
				}
			}
		}
		dfs(0,0);
		System.out.println(ans);
	}
	
	static int Distance() { //최소 치킨거리 계산
		int sum = 0;
		int nDis = 0;
		for(int i = 0; i < house.size(); i++) {
			int dis = 987654321;
			for(int j = 0; j < res.length; j++) {
				nDis = Math.abs(house.get(i).x - res[j].x) + Math.abs(house.get(i).y - res[j].y);
				if(nDis < dis) {
					dis = nDis;
				}
			}
			sum += dis;
		}
		return sum;
	}
	
	static void dfs(int idx, int cnt) { // 조합
		if(cnt == M) {
			ans = Math.min(ans, Distance());
			return;
		}
		
		for(int i = idx; i <chicken.size(); i++) {
			res[cnt] = chicken.get(i);
			dfs(i + 1, cnt + 1);
		}
	}
}
