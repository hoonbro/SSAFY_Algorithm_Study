package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_17143_낚시왕 {
	static int N, M, S, total = 0, sharkCnt = 0;
	static int[][] map, temp;
	static int[] X = { 0, -1, 1, 0, 0 };
	static int[] Y = { 0, 0, 0, 1, -1 };
	static Queue<Shark> q;
	static ArrayList<Shark> list;

	static class Shark {
		int x, y, s, d, z;

		Shark(int x, int y, int s, int d, int z) {
			this.x = x;
			this.y = y;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

	static void fishing(int man) {
		// 해당열에서 가장 가까운 상어 잡기
		int fish = 0;
		for (int i = 1; i <= N; i++) {
			if (map[i][man] != 0) {
				// 상어 없애기
				total += map[i][man];
				fish = map[i][man];
				map[i][man] = 0;
				sharkCnt--;
				break;
			}
		}
		move(fish);
	}

	static void move(int fish) {
		int size = q.size();
		while (size-- > 0) {
			Shark shark = q.poll();
			if (shark.z == fish)
				continue;

			int nx = shark.x;
			int ny = shark.y;

			map[nx][ny] = 0;
			for (int d = 0; d < shark.s; d++) {
				nx += X[shark.d];
				ny += Y[shark.d];
				
				if (nx < 1 || ny < 1 || nx > N || ny > M) {
					if (ny == M + 1) {
						shark.d = 4;
					} else if (ny == 0) {
						shark.d = 3;
					} else if (nx == N + 1) {
						shark.d = 1;
					} else if (nx == 0) {
						shark.d = 2;
					}

					nx += X[shark.d] * 2;
					ny += Y[shark.d] * 2;
				}
			}
			if (temp[nx][ny] < shark.z) {
				list.add(new Shark(nx, ny, shark.s, shark.d, shark.z));
			} else
				sharkCnt--;
		}
		
		Collections.sort(list, new Comparator<Shark>() {
			@Override
			public int compare(Shark o1, Shark o2) {
				// TODO Auto-generated method stub
				return o1.z - o2.z;
			}
		});
		
		for(int i = list.size()-1; i >= 0; i--) {
			if(temp[list.get(i).x][list.get(i).y] == 0) {
				temp[list.get(i).x][list.get(i).y] = list.get(i).z;
				q.add(list.get(i));
			}
		}
		
		list.clear();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (temp[i][j] != 0) {
					map[i][j] = temp[i][j];
					temp[i][j] = 0;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken()); // 상어 수
		map = new int[N + 1][M + 1]; // 상어 번호
		temp = new int[N + 1][M + 1];

		q = new LinkedList<>();
		list = new ArrayList<>();
		int r, c, s, d, z;
		for (int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			//s를 다 할필요 없음
			if (d == 1 || d == 2)
				s = s % ((N - 1) * 2);
			else {
				s = s % ((M - 1) * 2);
			}
			map[r][c] = z;
			sharkCnt++;
			q.add(new Shark(r, c, s, d, z));
		}

		for (int i = 1; i <= M; i++) {
			if (sharkCnt == 0)
				break;
			fishing(i);
		}
		System.out.println(total);
	}
}
