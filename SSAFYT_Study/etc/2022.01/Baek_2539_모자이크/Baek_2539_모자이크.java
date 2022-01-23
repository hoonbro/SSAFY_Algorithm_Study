package etc._2022_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Baek_2539_모자이크 {
	static int N, M, total, fault;
	static int max, ans = 1;
	static ArrayList<Pos> al = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken().trim());
		M = Integer.parseInt(st.nextToken().trim());
		total = Integer.parseInt(br.readLine().trim());
		fault = Integer.parseInt(br.readLine().trim());

		for(int i = 0; i < fault; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			al.add(new Pos(a, b));
		}
		Collections.sort(al);
		
		binarySearch();
		System.out.println(ans);
	}
	
	static void binarySearch() {
		int left = 0;
		int right = N > M ? N : M; 
		
		while(left<=right) {
			int mid = (left + right)/2;
			
			if(check(mid)) {
				ans = mid;
				right = mid-1;
			}else {
				left = mid+1;
			}
		}
	}
	
	static boolean check(int size) {
		int cnt = 0;
		int prev = 0;
		
		for(int i = 0; i < al.size(); i++) {
			Pos now = al.get(i);
			
			if(now.x > size)
				return false;
			
			if(prev == 0 || prev + size <= now.y) {
				prev = now.y;
				cnt++;
				
				if(cnt > total)
					return false;
			}
		}
		
		return true;
	}
	
	static class Pos implements Comparable<Pos>{
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pos o) {
			return this.y - o.y;
		}
	}
}
