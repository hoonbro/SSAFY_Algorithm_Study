package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_15558_점프게임 {
	static int N, K;
	static int[][] map;
	static Queue<Pos> q;
	static boolean flag = false;

	static class Pos {
		int x, y, drop;

		Pos(int x, int y, int drop) {
			this.x = x;
			this.y = y;
			this.drop = drop;
		}
	}

	static void bfs() {
		int[] X = { 0, 0, 1 };
		int[] Y = { 1, -1, K };
		q.offer(new Pos(0, 0, 0));
		map[q.peek().x][q.peek().y] = 2;

		Pos pos = null;
		while (!q.isEmpty()) {
			pos = q.poll();
			
			// 앞, 뒤, 옆
			for (int i = 0; i < 3; i++) {
				int nx = pos.x + X[i];
				int ny = pos.y + Y[i];
				if (i == 2)
					nx %= 2;

				if (ny >= N) {
					flag = true;
					return;
				}

				if (ny < 0 || map[nx][ny] != 1 || ny <= pos.drop)
					continue;
				
				map[nx][ny] = 2;
				q.add(new Pos(nx, ny, pos.drop+1));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[2][N];
		q = new LinkedList<>();

		for (int i = 0; i < 2; i++) {
			String s = br.readLine();

			for (int j = 0; j < s.length(); j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		bfs();
		System.out.println(flag == true ? 1 : 0);
	}
}
