package week14;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Baek_7662_이중_우선순위_큐 {
	static int T, N, num, n;
	static char oper;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> treeMap = new TreeMap<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				oper = st.nextToken().charAt(0);
				num = Integer.parseInt(st.nextToken());

//                지정된 키의 값을 반환하며 찾지 못하면 기본값(defaultValue)로 지정된 객체를 반환한다
				if (oper == 'I')
					treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
				else {
					if (treeMap.isEmpty())
						continue;
					n = (num == 1) ? treeMap.lastKey() : treeMap.firstKey();
					if (treeMap.put(n, treeMap.get(n) - 1) == 1)
						treeMap.remove(n);
				}
			}

			if (treeMap.isEmpty()) {
				sb.append("EMPTY").append("\n");
			} else {
				sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
