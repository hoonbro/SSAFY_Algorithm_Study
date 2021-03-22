package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Baek_1302_베스트셀러 {
	static int N;

	static class Book{
		String name;
		int cnt;

		Book(String name, int cnt) {
			this.name = name;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ArrayList<Book> al = new ArrayList<>();

		loop:
		for (int i = 0; i < N; i++) {
			String s = br.readLine();

			if (al.size() == 0) {
				al.add(new Book(s, 1));
				continue;
			}

			
			for (int j = 0; j < al.size(); j++) {
				if (al.get(j).name.equals(s)) {
					al.get(j).cnt++;
					continue loop;
				}
			}
			al.add(new Book(s, 1));
		}
		
		Collections.sort(al, new Comparator<Book>() {

			@Override
			public int compare(Book o1, Book o2) {
				int diff = o2.cnt - o1.cnt;
				return diff == 0 ? o1.name.compareTo(o2.name) : diff;
			}
		});
		System.out.println(al.get(0).name);
	}
}
