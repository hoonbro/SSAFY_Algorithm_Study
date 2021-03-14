package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_19237_어른상어 {
	static int N, M, K, time = 0;
	static int[][][] map, sharkPrior;
	static int[] X = { 0, -1, 1, 0, 0 };// 위 아래 왼쪽 오른쪽
	static int[] Y = { 0, 0, 0, -1, 1 };
	static int[] priority;
	static Queue<Shark> q = new LinkedList<>();

	static class Shark {
		int x, y, size, dir;

		Shark(int x, int y, int size, int dir) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.dir = dir;
		}
	}

	static void bfs() {
		Shark s;
		boolean flag;
		LinkedList<Shark> list = new LinkedList<>();
		while (!q.isEmpty()) {
			flag = false;
			s = q.poll();
			
			if (s.dir == 0) { // 상어 초기방향
				s.dir = priority[s.size];
			}

			for (int i = 0; i < 4; i++) { // 현재방향별 우선순위 체크
				int d = sharkPrior[s.size][s.dir][i]; // 상어번호, 바라보는방향,우선순위

				int nx = s.x + X[d];
				int ny = s.y + Y[d];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				//갈수있는 곳이면 list에 추가
				if (map[nx][ny][0] == 0) {
					list.add(new Shark(nx, ny, s.size, d));
					flag = true;
					break;
				}
			}


			// 갈곳이 없으면 본인의 영역으로 돌아갈수 있는지 체크
			if (!flag) {
				for (int i = 0; i < 4; i++) {
					int d = sharkPrior[s.size][s.dir][i];

					int nx = s.x + X[d];
					int ny = s.y + Y[d];

					if (nx < 0 || ny < 0 || nx >= N || ny >= N)
						continue;

					if (map[nx][ny][0] == s.size) {
						list.add(new Shark(nx, ny, s.size, d));
						break;
					}
				}
			}

			if (q.isEmpty()) {
				time++;
				
				//상어들이 동시에 움직인 후에 겹치는 상어 없애기
				while(list.size()!=0) {
					Shark t = list.get(0);
					if(map[t.x][t.y][0] == 0 || map[t.x][t.y][0] == t.size) {
						map[t.x][t.y][0] = t.size;
						map[t.x][t.y][1] = K+1;

						q.add(new Shark(t.x, t.y, t.size, t.dir));
						
						list.removeFirst();
					}else {
						list.removeFirst();
					}
				}
				
				//상어 냄새 --
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j][1] != 0) {
							map[i][j][1]-=1;
							if (map[i][j][1] == 0) {
								map[i][j][0] = 0;
							}
						}
					}
				}
				
				if (q.size() == 1 || time > 1000)
					break;
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N][2]; // 0:상어냄새 1:남은카운트
		priority = new int[M + 1];
		sharkPrior = new int[M + 1][5][4];

		List<Shark> list = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
				if (map[i][j][0] != 0) {
					list.add(new Shark(i, j, map[i][j][0], 0));
					map[i][j][1] = K;
				}
			}
		}

		Collections.sort(list, new Comparator<Shark>() {
			@Override
			public int compare(Shark o1, Shark o2) {
				return o1.size - o2.size;
			}
		});
		
		// 상어별 우선순위, 큐에도 1번 상어부터 넣음
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			q.add(list.get(i-1));
			priority[i] = Integer.parseInt(st.nextToken());

		}

		// 상어별 현재방향 우선순위
		for (int k = 1; k <= M; k++) {
			for (int i = 1; i <= 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					sharkPrior[k][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}

		bfs();

		if (time > 1000)
			System.out.println(-1);
		else
			System.out.println(time);

	}
}
