package etc._2022_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_13397_구간_나누기2 {
	static int N, M, ans = 0, INF = 987654321;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		int left = 0;
		int right = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, arr[i]);
		}

		binarySearch(left, right);
		System.out.println(ans);

	}

	static void binarySearch(int left, int right) {
		while (left <= right) {
			int mid = (left + right) / 2;

			if (check(mid)) {
				ans = mid;
				right = mid - 1;
			} else
				left = mid + 1;
		}
	}

	static boolean check(int mid) {
		int min = INF;
		int max = 0;
		int cnt = 1;

		for (int i : arr) {
			min = Math.min(min, i);
			max = Math.max(max, i);

			if (max - min > mid) {
				min = i;
				max = i;
				cnt++;
			}
		}

		return cnt <= M;
	}
}
