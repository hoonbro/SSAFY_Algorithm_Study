package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_6593_상범빌딩 {
	static int H, N, M;
	static char[][][] building;
	static int[] X = { -1, 0, 1, 0, 0, 0 };
	static int[] Y = { 0, 1, 0, -1, 0, 0 };
	static int[] Z = { 0, 0, 0, 0, 1, -1 };
	static Queue<Pos> q;
	static StringBuilder sb;
	static int cnt;
	
	static class Pos{
		int x,y,z,time;
		
		Pos(int x, int y, int z, int time){
			this.x = x;
			this.y = y;
			this.z  =z;
			this.time = time;
		}
	}
	
	static boolean move() {
		Pos pos;
		while(!q.isEmpty()) {
			pos = q.poll();
			
			for(int i = 0; i < 6; i++) {
				int nx = pos.x + X[i];
				int ny = pos.y + Y[i];
				int nz = pos.z + Z[i];
				
				if(nx < 0 || ny < 0 || nz < 0 || nx>=N || ny >= M || nz >=H)
					continue;
				
				if(building[nz][nx][ny] == 'E') {
					cnt = (pos.time +1);
					q.clear();
					return true;
				}
				
				if(building[nz][nx][ny] == '.') {
					building[nz][nx][ny] = 'S'; // 지나왔던길 체크
					q.offer(new Pos(nx,ny,nz, pos.time+1));
				}
				
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		while (true) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken()); // 건물 높이
			N = Integer.parseInt(st.nextToken()); // row
			M = Integer.parseInt(st.nextToken()); // col

			if (H == 0 && N == 0 && M == 0) {
				break;
			}
			
			cnt = 0;
			q = new LinkedList<>();
			building = new char[H][N][M];
			for (int h = 0; h < H; h++) {
				for (int i = 0; i < N; i++) {
					String s = br.readLine();
					for (int j = 0; j < M; j++) {
						building[h][i][j] = s.charAt(j);
						if(building[h][i][j] == 'S')
							q.offer(new Pos(i, j, h, 0));
					}
				}
				br.readLine();
			}

			if(move()) {
				sb.append("Escaped in "+ cnt +" minute(s).");
				continue;
			}
			sb.append("Trapped!\n");
			
		}
		System.out.println(sb);
	}
}
