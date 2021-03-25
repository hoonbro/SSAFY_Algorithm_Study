package week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek_1744_수묶기 {
	static int N, minus = 0, ans = 0;
	static int[] arr;
	static boolean Zero = false;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if (arr[i] < 0)
				minus++;
			else if(arr[i] == 0)
				Zero = true;
			}
		Arrays.sort(arr);

		calc();
		System.out.println(ans);
	}

	static void calc() {
		int temp = 0;
		for (int i = 0; i < minus; i += 2) {
			if (i + 1 >= minus) {
				temp = arr[i];
				break;
			}
			ans += arr[i] * arr[i + 1];
		}
		if (Zero) {// 0이 있는지 확인
			minus++;
		} else {
			ans += temp;
		}
		temp = 0;
		for (int i = N - 1; i >= minus; i -= 2) {
			if (i - 1 < minus) {
				temp = arr[i];
				break;
			}
			
			if (arr[i - 1] == 1 || arr[i-1] == 0) {
				ans += arr[i] + arr[i-1];
			}
			else
				ans += arr[i] * arr[i - 1];
		}
		ans += temp;
	}
}
