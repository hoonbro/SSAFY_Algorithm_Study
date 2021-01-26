import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_4963 {
	static int map[][];
	static boolean visited[][];
	static int[] X = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] Y = { -1, -1, 0, 1, 1, 0, 1, -1 };

	static class xy {
		int x;
		int y;

		public xy(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void bfs(int w, int h, int dx, int dy) {
		Queue<xy> q = new LinkedList<>();
		xy val = new xy(dx, dy);
		q.add(val);
		visited[dx][dy] = true;
		
		while(!q.isEmpty()) {
			val = q.poll();
			
			for(int i = 0; i < 8; i++) {
				int nx = val.x + X[i];
				int ny = val.y + Y[i];
				
				if(nx<0 || ny <0 || nx >= h || ny >=w) 
					continue;
				if(map[nx][ny] == 0 || visited[nx][ny])
					continue;
				
				visited[nx][ny] = true;
				q.add(new xy(nx, ny));
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			if (w == 0 && h == 0)
				break;

			map = new int[h][w];
			visited = new boolean[h][w];
			int cnt = 0;

			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1 && !visited[i][j]) {
						bfs(w, h, i, j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}
}
