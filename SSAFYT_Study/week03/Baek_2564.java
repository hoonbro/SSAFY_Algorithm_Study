package week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//경비원
public class Baek_2564 {
	static class pos {
		int dir;
		int x;
		int y;

		pos(int dir, int x, int y) {
			this.dir = dir;
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());

		int n = Integer.parseInt(br.readLine());
		int ans = 0;
		ArrayList<pos> al = new ArrayList<>();
//		ArrayList<Integer> al = new ArrayList<>();

		for (int i = 0; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());

			if(dir == 1) {//북
				al.add(new pos(dir, 0, val));
			}
			else if(dir == 2) {//남
				al.add(new pos(dir, height, val));
			}
			else if(dir == 3) {//서
				al.add(new pos(dir, val, 0));
			}
			else if(dir == 4)//동
				al.add(new pos(dir, val, width));
		}

		pos now = al.get(n);
		for (int i = 0; i < n; i++) {
			if(now.dir == 1 && al.get(i).dir == 2 || now.dir == 2 && al.get(i).dir == 1) {
				int a = height + now.y + al.get(i).y;
				int b = height + width - now.y + width - al.get(i).y;
				ans += (a < b) ? a : b;
				continue;
			}
			
			if(now.dir == 3 && al.get(i).dir == 4 || now.dir == 4 && al.get(i).dir == 3) {
				int a = height + now.x + al.get(i).x;
				int b = height + width - now.x + width - al.get(i).x;
				ans += (a < b) ? a : b;
				continue;
			}
			
			ans += Math.abs(now.x - al.get(i).x) + Math.abs(now.y - al.get(i).y);
		}
		System.out.println(ans);
	}
}
