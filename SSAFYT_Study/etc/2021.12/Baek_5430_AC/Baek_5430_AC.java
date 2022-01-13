package etc._2021_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Baek_5430_AC {
	static int T, N;
	static String P, str;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		
		loop:
		while (T-- > 0) {
			P = br.readLine();
			N = Integer.parseInt(br.readLine());
			str = br.readLine().replace("[", "").replace("]", "");
			int len = P.length();
			
			// Deque에 입력받은 배열 담기
			String[] arr = str.split(",");
			Deque<String> dq = new ArrayDeque<>();
			for (int i = 0; i < arr.length; i++) {
				if(arr[i].equals(""))
					continue;
				dq.offer(arr[i]);
			}
			// 함수 실행
			boolean flag = true;
			for (int i = 0; i < len; i++) {
				char cmd = P.charAt(i);

				// 뒤집기
				if (cmd == 'R') {
					flag = !flag;
				}
				// 삭제
				else {
					if (dq.isEmpty()) {
						result.append("error\n");
						continue loop;
					}
					// 정방향
					if (flag) {
						dq.removeFirst();
					}
					// 역방향
					else {
						dq.removeLast();
					}
				}
			}

			StringBuilder sb = new StringBuilder();
			while (!dq.isEmpty()) {
				if(flag)
					sb.append(dq.pollFirst());
				else 
					sb.append(dq.pollLast());
				if(!dq.isEmpty())
					sb.append(",");
			}

			result.append("[").append(sb).append("]\n");
		}
		System.out.println(result.toString());
	}

}
