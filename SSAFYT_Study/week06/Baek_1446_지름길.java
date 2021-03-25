package week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_1446_지름길 {
	static int N, D;
	static ArrayList<Route> al;
	static int ans = Integer.MAX_VALUE;

	static class Route{
		int from;
		int to;
		int len;

		Route(int from, int to, int len) {
			this.from = from;
			this.to = to;
			this.len = len;
		}
	}

	static void way(int now, int sum) {
		if(sum >= ans)
			return;
		
		if(now == D) {
			ans = Math.min(ans, sum);
			return;
		}
		
		for(int i = 0; i < al.size(); i++) {
			if(now == al.get(i).from) {
				way(al.get(i).to, sum+al.get(i).len);
			}
		}
		way(now+1, sum+1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		al = new ArrayList<Route>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());

			if (to > D || from > D || len >= to - from)
				continue;
			al.add(new Route(from, to, len));
		}
		
		way(0,0);
		System.out.println(ans);
	}
}
