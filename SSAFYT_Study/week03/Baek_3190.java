package week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//뱀
public class Baek_3190 {
	static int N, K;
	static int[][] map;
	static Queue<Command> q;
	static Deque<Pos> snake;
	
	static class Command{
		int time;
		char dir;
		public Command(int time, char dir) {
			this.time = time;
			this.dir = dir;
		}
	}
	
	static class Pos{
		int x;
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static void play() {
		//0상, 1좌, 2하, 3우
		int[] X = {-1, 0, 1, 0};
		int[] Y = {0, -1, 0, 1};
		int dir = 3;
		int t = 0;
		Command cmd = q.poll();
		
		while(true) {
			t++;
			Pos head = snake.peekFirst();
			int dx = head.x + X[dir];
			int dy = head.y + Y[dir];
			
			if(dx < 1 || dy < 1 || dx >= N+1 || dy >= N+1)
				break;
			
			if(map[dx][dy] == 1)
				break;
			
			if(map[dx][dy] != -1) {
				Pos tail = snake.pollLast();
				map[tail.x][tail.y] = 0;
			}

			map[dx][dy] = 1;
			snake.addFirst(new Pos(dx, dy));
			
			if(cmd.time == t) {
				if(cmd.dir == 'L') {
					dir = (dir + 1)%4;
				}else
					dir = (dir + 3)%4;
				
				if(!q.isEmpty())
					cmd = q.poll();
			}
		}
		
		System.out.println(t);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new int [N+1][N+1];
		q = new LinkedList<>();
		snake = new ArrayDeque<>();
		
		//사과의 위치
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			map[row][col] = -1; //사과는 -1로 표시
		}
		//뱀 초기 위치
		snake.addFirst(new Pos(1, 1));
		map[1][1] = 1;
		
		//커맨드 입력		
		int l = Integer.parseInt(br.readLine());
		for(int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			q.add(new Command(x, c));
		}
		play();
	}
}
