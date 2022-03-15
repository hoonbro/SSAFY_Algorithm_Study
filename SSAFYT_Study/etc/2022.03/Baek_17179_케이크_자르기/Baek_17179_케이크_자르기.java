package etc._2022_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_17179_케이크_자르기 {
	static int N, M, L;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		arr = new int[M + 1];

		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		arr[M] = L;

		for (int i = 0; i < N; i++) {
			int cut = Integer.parseInt(br.readLine());
			sb.append(binarySearch(cut)).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	static int binarySearch(int cut) {
		int left = 0;
		int right = L;
		int mid, min = 0;

		while (left <= right) {
			mid = (left + right) / 2;

			int prev = 0;
			int cnt = 0;
			
			for (int i = 0; i <= M; i++) {
				if (arr[i] - prev >= mid) {
					cnt++;
					prev = arr[i];
				}
			}
			if (cnt > cut) {
				left = mid + 1;
				min = Math.max(min, mid);
			} else
				right = mid - 1;
			
		}

		return min;
	}
}
