package week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2166_다각형의_면적 {
	static int N;
	static Pos[] pos;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		N = Integer.parseInt(br.readLine());
		pos = new Pos[N+1];
		long sum1=0, sum2=0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			pos[i] = new Pos(x, y);
		}
		pos[N] = new Pos(pos[0].x, pos[0].y);
		
		for(int i = 0; i < N; i++) {
			sum1 += pos[i].x * pos[i+1].y;
			sum2 += pos[i+1].x * pos[i].y;
		}
		System.out.printf("%.1f", (Math.abs(sum1 - sum2) / 2.0));
	}
	
	static class Pos{
		long x, y;

		public Pos(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
}
