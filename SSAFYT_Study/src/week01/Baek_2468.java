package week1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//안전 영역
public class Baek_2468 {

	static int[][] map;
	static boolean[][] visited;
	static int[] X = { -1, 0, 1, 0 };
	static int[] Y = { 0, 1, 0, -1 };
	static int n;
	static int max = -1;
	static int min = 100;
	static int safeArea = 1; // 아무곳도 잠기지 않을경우 1로 표시

	static class XY {
		int x;
		int y;

		public XY(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	//bfs
	static void bfs(int x, int y, int height) {
		Queue<XY> q = new LinkedList<>();
		XY xy = new XY(x, y);
		q.add(xy);
		visited[x][y] = true;

		//큐가 빌때까지 반복문
		while (!q.isEmpty()) {
			xy = q.poll();
			
			//상하좌우 체크
			for(int i = 0; i < 4; i++) {
				int dx = xy.x + X[i];
				int dy = xy.y + Y[i];
				
				//map크기 벗어나면 continue;
				if(dx < 0 || dy < 0 || dx >= n || dy >= n)
					continue;
				//잠긴 곳이거나 이미 갔던 곳이면 continue;
				if(map[dx][dy]-height <=0 || visited[dx][dy])
					continue;
				
				//if문에 걸리지 않으면 큐에넣고 갔던곳으로 체크
				q.add(new XY(dx,dy));
				visited[dx][dy] = true;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 영역 크기 입력
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];

		// map 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				//입력값중 min과 max 저장
				max = Math.max(max, map[i][j]);
				min = Math.min(min, map[i][j]);
			}
		}

		//min부터 max까지 반복
		for (int k = min; k <= max; k++) {
			int cnt = 0;
			visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					//좌표에서 침수높이를 뺀값이 0보다 크거나 이전에 갔던곳이 아니면 bfs 실행
					if (map[i][j] - k > 0 && !visited[i][j]) {
						bfs(i, j, k);
						cnt++;
					}
				}
			}
			//cnt와 safeArea비교해 큰것을 safeArea로
			safeArea = Math.max(safeArea, cnt);

		}
		
		System.out.println(safeArea);
	}
}
