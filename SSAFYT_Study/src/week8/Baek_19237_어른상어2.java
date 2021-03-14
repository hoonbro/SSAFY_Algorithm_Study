package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_19237_어른상어2 {
	static int N,M,K,total;
	static int[][][] map, sharkPrior;
	static int[] X = {0,-1,1,0,0};//위 아래 왼쪽 오른쪽
	static int[] Y = {0,0,0,-1,1};
	static int[] priority;
	static ArrayList<Queue<Shark>> shark;
	static Queue<Shark> q = new LinkedList<>();
	static ArrayList<Shark> al = new ArrayList<>();
	
	static class Shark{
		int x, y, size, dir;
		
		Shark(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		Shark(int x, int y, int size, int dir){
			this.x = x;
			this.y = y;
			this.size = size;
			this.dir = dir;
		}
	}
	
	static void bfs() {
		Shark s;
		int cnt = 1;
		while(true) {
			for(int t = 1; t <= M; t++) { //이동
				if(shark.get(t).isEmpty())
					continue;
				
				//상어 우선순위별 방향체크 해줘야되나????
				s = shark.get(t).poll();
				
				for(int i = 0; i < 4; i++) { //현재 방향별 우선순위 체크
					int d = sharkPrior[s.size][s.dir][i];
					
					int nx = s.x + X[d];
					int ny = s.y + Y[d];
					
					if(nx < 0 || ny < 0 || nx >=N || ny >= N)
						continue;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N][2]; // 0:상어 1:남은카운트
		priority = new int[M+1];
		sharkPrior = new int[M+1][5][4];
		total = M;
		shark = new ArrayList<>();
		
		for(int i = 0; i <= M; i++) {
			shark.add(new LinkedList<>());
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
				map[i][j][1] = K;
				if(map[i][j][0]!=0) {
					q.offer(new Shark(i,j,map[i][j][0], 0));
					shark.get(map[i][j][0]).offer(new Shark(i,j,map[i][j][0], 0));
				}
			}
		}
		
		//상어별 우선순위
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= M; i++) {
			priority[i] = Integer.parseInt(st.nextToken());
			
			Shark s = shark.get(i).poll();
			// 상어 초기방향
			shark.get(i).add(new Shark(s.x, s.y, s.size, priority[i])); 
		}
		
		//상어별 현재방향 우선순위
		for(int k = 1; k <= M; k++) {
			for(int i = 1; i <= 4; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 4; j++) {
					sharkPrior[k][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
//		for(int i = 1; i <=M; i++) {
//			while(!shark.get(i).isEmpty()) {
//				System.out.println(shark.get(i).peek().x + " " + shark.get(i).peek().y + " " + shark.get(i).peek().size + " " + shark.get(i).poll().dir);
//			}
//		}
	}
}
