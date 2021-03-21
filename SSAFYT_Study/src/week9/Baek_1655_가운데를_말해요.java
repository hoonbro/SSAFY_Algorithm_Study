package week9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Baek_1655_가운데를_말해요 {
	static int N;
	static ArrayList<Integer> al = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());

		while (N-- > 0) {
			bs(Integer.parseInt(br.readLine()));
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void bs(int n) {
		int left = 0;
		int right = al.size() - 1;
		int mid = (left + right) / 2;

		while (left <= right) {
			mid = (left + right) / 2;

			if (al.get(mid) > n) {
				right = mid - 1;
			} else {
				left = mid + 1;
				mid++;
			}
		}
		al.add(mid, n);
		if (al.size() % 2 == 0) 
			sb.append(al.get((al.size()/2)-1)).append("\n");
		else
			sb.append(al.get(al.size()/2)).append("\n");
	}
}
