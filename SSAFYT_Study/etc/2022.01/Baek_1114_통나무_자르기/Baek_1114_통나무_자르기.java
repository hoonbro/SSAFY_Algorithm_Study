package etc._2022_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1114_통나무_자르기 {
	static int L, K, C;
	static int[] cut, block;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		if (K < C)
			C = K;

		cut = new int[K + 1];
		block = new int[K + 1];

		cut[K] = L;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			cut[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(cut);
		block[0] = cut[0];

		for (int i = 1; i <= K; i++) {
			block[i] = cut[i] - cut[i - 1];
		}

		binarySearch();
	}

	static int check(int size) {
		int cnt = 0;
		int sum = 0;

		for (int i = K; i >= 0; i--) {
			if (block[i] > size)
				return -1;

			sum += block[i];

			if (sum > size) {
				cnt++;
				sum = block[i];

				if (cnt == C) {
					if (cut[i] > size)
						return -1;
					return cut[i];
				}
			}
		}
		return cut[0];
	}

	static void binarySearch() {
		int left = 1;
		int right = L;
		int max = L;
		int minIdx = L;

		while (left <= right) {
			int mid = (left + right) / 2;
			int idx = check(mid);

			if (idx >= 0) {
				max = mid;
				minIdx = idx;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		System.out.println(max + " " + minIdx);
	}
}
