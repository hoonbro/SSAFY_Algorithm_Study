package etc._2022_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Baek_1715_카드_정렬하기 {
	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}

		int ans = 0;

		int sum = 0;
		while (pq.size() >= 2) {
			int a = pq.poll();
			int b = pq.poll();

			sum = a + b;
			ans += sum;
			pq.offer(sum);
		}

		System.out.println(ans);
	}
}
