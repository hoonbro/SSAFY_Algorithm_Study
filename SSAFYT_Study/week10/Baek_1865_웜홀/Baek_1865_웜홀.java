package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer; 

public class Baek_1865_웜홀 {
	static int T, N, M, W, s, e, t, INF = 987654321;
	static int[] dis;
	static ArrayList<ArrayList<Node>> al;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 정점
			M = Integer.parseInt(st.nextToken()); // 도로
			W = Integer.parseInt(st.nextToken()); // 웜홀

			dis = new int[N + 1];
			al = new ArrayList<ArrayList<Node>>();

			for (int i = 0; i <= N; i++) {
				al.add(new ArrayList<>());
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				s = Integer.parseInt(st.nextToken());
				e = Integer.parseInt(st.nextToken());
				t = Integer.parseInt(st.nextToken());
				al.get(s).add(new Node(e, t));
				al.get(e).add(new Node(s, t));
			}
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				s = Integer.parseInt(st.nextToken());
				e = Integer.parseInt(st.nextToken());
				t = Integer.parseInt(st.nextToken());
				al.get(s).add(new Node(e, -t));
			}

			if (bellmanFord())
				sb.append("YES");
			else
				sb.append("NO");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	static boolean bellmanFord() {
		Arrays.fill(dis, INF);
		dis[1] = 0;
		boolean flag = false;
		for (int i = 1; i < N; i++) { // 정점수-1만큼 반복
			flag = false;
			for (int j = 1; j <= N; j++) { // 현재 정점
				for (Node n : al.get(j)) {
					if (dis[n.to] > dis[j] + n.weight) {
						dis[n.to] = dis[j] + n.weight;
						flag = true;
					}
				}
			}
			if (!flag) // 업데이트가 안되어있으면 다음에도 안되므로 break;
				break;
		}
		if(flag) { // 음수사이클 확인하기
			for (int j = 1; j <= N; j++) { // 현재 정점
				for (Node n : al.get(j)) {
					if (dis[n.to] > dis[j] + n.weight) {
						return true; //음수 사이클이 생긴경우
					}
				}
			}
		}
		return false;
	}

	static class Node {
		int to, weight;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
}
