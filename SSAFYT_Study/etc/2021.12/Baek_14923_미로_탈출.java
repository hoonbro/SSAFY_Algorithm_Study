package etc._2021_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_14923_미로_탈출 {
	static int N, M, x1, y1, x2, y2;
	static int[] X = {-1,0,1,0};
	static int[] Y = {0,1,0,-1};
	static int[][] map;
	static boolean[][][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		x1 = Integer.parseInt(st.nextToken())-1;
		y1 = Integer.parseInt(st.nextToken())-1;
		
		st = new StringTokenizer(br.readLine());
		x2 = Integer.parseInt(st.nextToken())-1;
		y2 = Integer.parseInt(st.nextToken())-1;
		
		map = new int[N][M];
		visited = new boolean[N][M][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
	}
	
	static void bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(x1,y1,0, 1));
		int nx, ny;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
//			System.out.println("("+node.x + " " + node.y + ") " + node.cnt + " " + node.staff);
			
			if(node.x == x2 && node.y == y2) {
				System.out.println(node.cnt);
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				nx = node.x + X[i];
				ny = node.y + Y[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
					
				if(map[nx][ny] == 0 && !visited[nx][ny][node.staff]) {
					visited[nx][ny][node.staff] = true;
					pq.offer(new Node(nx, ny, node.cnt+1, node.staff));
				}else if(map[nx][ny] == 1 && node.staff == 1 && !visited[nx][ny][1]){
					visited[nx][ny][1] = true;
					pq.offer(new Node(nx, ny, node.cnt + 1, 0));
				}
			}
		}
		System.out.println(-1);
	}
	
	static class Node implements Comparable<Node>{
		int x, y, cnt, staff;
		
		public Node(int x, int y, int cnt, int staff) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.staff = staff;
		}

		@Override
		public int compareTo(Node o) {
//			if(this.cnt == o.cnt)
//				return o.staff - this.staff;
			return this.cnt - o.cnt;
		}
	}
}
