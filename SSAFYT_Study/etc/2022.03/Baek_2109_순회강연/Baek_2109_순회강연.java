package etc._2022_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_2109_순회강연 {
	static int N;
	static Lecture[] lectures;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		lectures = new Lecture[N];

		int max = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int pay = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());

			lectures[i] = new Lecture(day, pay);
			max = Math.max(max, day);
		}
		
		Arrays.sort(lectures);

		int ans = 0;
		boolean[] check = new boolean[max + 1];
		for (int i = 0; i < N; i++) {
			for (int j = lectures[i].day; j >= 1; j--) {
				if (check[j])
					continue;

				check[j] = true;
				ans += lectures[i].pay;
				break;
			}
		}

		System.out.println(ans);
	}

	static class Lecture implements Comparable<Lecture> {
		int day, pay;

		public Lecture(int day, int pay) {
			this.day = day;
			this.pay = pay;
		}

		@Override
		public int compareTo(Lecture o) {
			if (o.pay == this.pay)
				return this.day - o.day;
			return o.pay - this.pay;
		}
	}
}
