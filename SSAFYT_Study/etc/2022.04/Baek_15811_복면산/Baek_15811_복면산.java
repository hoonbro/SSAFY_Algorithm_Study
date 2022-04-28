package etc._2022_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_15811_복면산 {
	static int N;
	static int[] num, alpha;
	static boolean flag;
	static boolean[] used;
	static String[] input;
	static ArrayList<Character> al;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		num = new int[3];
		alpha = new int[26];
		used = new boolean[10];
		input = new String[3];
		al = new ArrayList<>();

		input = br.readLine().split(" ");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < input[i].length(); j++) {
				if (al.contains(input[i].charAt(j)))
					continue;

				al.add(input[i].charAt(j));
			}
		}
		N = al.size();

		dfs(0);

		System.out.println(flag ? "YES" : "NO");
	}

	static void dfs(int idx) {
		if (flag)
			return;

		if (idx == N) {
			flag = sum();
			return;
		}

		for (int i = 0; i < 10; i++) {
			if (used[i])
				continue;

			used[i] = true;
			alpha[al.get(idx) - 'A'] = i;
			dfs(idx + 1);
			used[i] = false;
		}
	}

	static boolean sum() {
		for (int i = 0; i < 3; i++) {
			int len = input[i].length();
			num[i] = 0;
			for (int j = 0; j < len; j++) {
				num[i] = num[i] * 10 + alpha[input[i].charAt(j)- 'A'];
			}
		}

		if (num[0] + num[1] == num[2]) {
			return true;
		}

		return false;
	}
}
