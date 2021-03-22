package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//이분 탐색
public class Baek_1920_수찾기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int M = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int x = Integer.parseInt(st.nextToken());
			int start = 0;
			int end = arr.length - 1;
			int mid = 0;
			int same = 0;

			while (start <= end) {
				mid = start + (end - start) / 2;

				if (arr[mid] > x) {
					end = mid - 1;
				} else if (arr[mid] < x) {
					start = mid + 1;
				}else {
					same = 1;
					break;
				}
			}
			sb.append(same).append("\n");
		}
		System.out.println(sb.toString());
	}
}
