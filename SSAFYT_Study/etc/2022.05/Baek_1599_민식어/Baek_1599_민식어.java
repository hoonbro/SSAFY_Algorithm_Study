package etc._2022_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Baek_1599_민식어 {
	static int N;
	static String[] arr;
	static Map<String, Integer> map = new HashMap<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new String[N];

		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().replace("k", "c").replace("ng", "n|");
		}
		Arrays.sort(arr);
		for (String s : arr) {
			s = s.replace("c", "k").replace("n|", "ng");
			System.out.println(s);
		}
	}
}
