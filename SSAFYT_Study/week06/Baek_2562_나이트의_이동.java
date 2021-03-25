package week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//bfs
public class Baek_2562_나이트의_이동 {
	static Queue<Pos> q;
	static Pos target;
	static int size, N, M;
	static int[][] map;
	static int[] X = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] Y = { 1, 2, 2, 1, -1, -2, -2, -1 };

	static class Pos {
		int x;
		int y;

		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void bfs(){
		while(!q.isEmpty()) {
			Pos pos = q.poll();
			
			if(target.x == pos.x && target.y == pos.y) {
				System.out.println(map[pos.x][pos.y]);
				return;
			}
			
			for(int i = 0; i < 8; i++) {
				int nx = pos.x + X[i];
				int ny = pos.y + Y[i];
				
				if(nx < 0 || ny < 0 || nx >= size || ny >= size)
					continue;
				
				if(map[nx][ny] == 0) {
					map[nx][ny] = map[pos.x][pos.y] + 1;
					q.offer(new Pos(nx, ny));
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			q = new LinkedList<>();
			size = Integer.parseInt(br.readLine());
			map = new int[size][size];
			
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			q.offer(new Pos(x, y));

			st = new StringTokenizer(br.readLine());
			target = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			bfs();
		}
	}
}
