import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_16948_데스나이트 {
	static int N, ans = -1;
	static int[] X = {-2, -2, 0, 0, 2, 2};
	static int[] Y = {-1, 1, -2, 2, -1, 1};
	static int[][] map;
	static Pos start, end;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
	
		map = new int[N][N];
		
		st = new StringTokenizer(br.readLine());
		
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		
		start = new Pos(r1, c1, 0);
		end = new Pos(r2, c2, 0);
		
		bfs();
		
		System.out.println(ans);
	}
	
	static void bfs() {
		Queue<Pos> pq = new LinkedList<>();
		
		pq.offer(start);
		map[start.x][start.y] = 1; 
		
		while(!pq.isEmpty()) {
			Pos p = pq.poll();
			
			if(p.x == end.x && p.y == end.y) {
				ans = p.cnt;
				break;
			}
			
			for(int i = 0; i < 6; i++) {
				int nx = p.x + X[i];
				int ny = p.y + Y[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] != 0)
					continue;
				
				map[nx][ny] = 1;
				
				pq.offer(new Pos(nx, ny, p.cnt+1));
			}
		}
	}
	
	static class Pos implements Comparable<Pos>{
		int x, y, cnt;
		
		public Pos(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}



		@Override
		public int compareTo(Pos o) {
			// TODO Auto-generated method stub
			return this.cnt - o.cnt;
		}
		
	}
}
