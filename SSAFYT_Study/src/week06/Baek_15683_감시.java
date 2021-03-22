package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_15683_감시 {
	static int N, M, total, min = Integer.MAX_VALUE;
	static int[][] map;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static ArrayList<Pos> al;

	static class Pos {
		int x;
		int y;

		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void dfs(int idx, int sum) {
		if (idx == al.size()) {
			min = Math.min(min, total - sum);
			return;
		}

		int x = al.get(idx).x;
		int y = al.get(idx).y;
		int cnt = 0;
		switch (map[x][y]) {
		case 1:
			for (int i = 0; i < 4; i++) {
				cnt = watch(x, y, i);
//				System.out.println(cnt);
				dfs(idx + 1, sum + cnt);
				recovery(x, y, i);
			}
			break;
		case 2: // 일자
			for (int i = 0; i < 2; i++) {
				cnt = watch(x, y, i);
				cnt += watch(x, y, i + 2);
				dfs(idx + 1, sum + cnt);
				recovery(x, y, i);
				recovery(x, y, i + 2);
			}
			break;
		case 3: // 직각
			for (int i = 0; i < 4; i++) {
				cnt = watch(x, y, i);
				cnt += watch(x, y, (i + 1) % 4);
				dfs(idx + 1, sum + cnt);
				recovery(x, y, i);
				recovery(x, y, (i + 1) % 4);
			}
			break;
		case 4: // ㅗ
			for (int i = 0; i < 4; i++) {
				cnt = watch(x, y, i);
				cnt += watch(x, y, (i + 1) % 4);
				cnt += watch(x, y, (i + 2) % 4);
				dfs(idx + 1, sum + cnt);
				recovery(x, y, i);
				recovery(x, y, (i + 1) % 4);
				recovery(x, y, (i + 2) % 4);
			}
			break;
		case 5:
			for (int i = 0; i < 4; i++) {
				cnt += watch(x, y, i);
			}
			dfs(idx + 1, sum + cnt);
			for (int i = 0; i < 4; i++) {
				recovery(x, y, i);
			}
			break;
		}
	}

	static int watch(int x, int y, int dir) {
		int nx, ny;
		int cnt = 0;
		int k = 1;

		while (true) {
			nx = x + X[dir] * k;
			ny = y + Y[dir] * k;
			if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 6)
				break;
			//보고있는 방향은 8
			if (map[nx][ny] == 0) {
				cnt++;
				map[nx][ny] = 8;
			}
			//겹치는 친구들은 ++;
			else if(map[nx][ny] >= 8) {
				map[nx][ny] += 1;
			}
			k++;
		}
		return cnt;
	}

	static void recovery(int x, int y, int dir) {
		int nx, ny;

		int k = 1;
		while (true) {
			nx = x + X[dir] * k;
			ny = y + Y[dir] * k;

			if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 6)
				break;
			//보고있는 방향이면 되돌림
			if (map[nx][ny] == 8) {
				map[nx][ny] = 0;
			}
			//다른애도 보고있으면 1--
			else if(map[nx][ny] > 7) {
				map[nx][ny] -= 1;
			}
			k++;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		al = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6)
					al.add(new Pos(i, j));
				else if (map[i][j] == 0)
					total++;
			}
		}
		dfs(0, 0);
		System.out.println(min);
	}
}
