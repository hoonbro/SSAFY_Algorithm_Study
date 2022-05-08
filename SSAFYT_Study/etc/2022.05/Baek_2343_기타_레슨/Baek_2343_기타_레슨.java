package etc._2022_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_2343_기타_레슨 {
	static int N, M, ans = Integer.MAX_VALUE;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int left = 0;
		int right = 0;

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());

			left = Math.max(arr[i], left);
			right += arr[i];
		}

		binarySearch(left, right);
		System.out.println(ans);

	}

	static void binarySearch(int left, int right) {
		while (left <= right) {
			int mid = (left + right) / 2;

			int cnt = check(mid);
			
			if (cnt <= M)
				right = mid - 1;
			else
				left = mid + 1;

		}

		ans = left;
	}

	static int check(int mid) {
		int cnt = 0;
		int sum = 0;
		for (int i : arr) {
			if (sum + i > mid) {
				sum = 0;
				cnt++;
			}
			sum += i;
		}
		if (sum != 0)
			cnt++;

		return cnt;
	}
}
