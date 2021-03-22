package week6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//bfs
public class Baek_9019_DSLR {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		String[] s;
		boolean[] visited;
		Queue<Integer> q;
		
		while(T-- > 0) {
			s = new String[10000];
			Arrays.fill(s, "");
			visited = new boolean[10000];
			q = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			q.offer(from);
			visited[from] = true;
			while(!q.isEmpty()) {
				int now = q.poll();
				if(now == to)
					break;
				int D = (now * 2) % 10000;
				int S = now - 1;
				int L = (now%1000)*10 + now/1000;
				int R = (now/10) + (now%10) * 1000;
				if(S == -1)
					S = 9999;
				
				//썼던수 체크, String 배열에 해당하는 위치 가는 방법 적기
				if(!visited[D]) {
					visited[D] = true;
					q.add(D);
					s[D] = s[now] + "D";
				}
				
				if(!visited[S]) {
					visited[S] = true;
					q.add(S);
					s[S] = s[now] + "S";
				}
				
				if(!visited[L]) {
					visited[L] = true;
					q.add(L);
					s[L] = s[now] + "L";
				}
				
				if(!visited[R]) {
					visited[R] = true;
					q.add(R);
					s[R] = s[now] + "R";
				}
			}
			sb.append(s[to] + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
