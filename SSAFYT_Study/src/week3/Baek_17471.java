package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//개리맨더링
public class Baek_17471 {
	static int N;
	static int[] population;
	static boolean[] check, visited;
	static int min = 987654321;
	static ArrayList<ArrayList<Integer>> al;

	//각각의 구가 연결되어있는지 확인
	static void isLinked(int now, boolean flag) {
		if (check[now] == flag && visited[now])
			return;
		visited[now] = true;

		for (int i = 0; i < al.get(now).size(); i++) {
			if (check[al.get(now).get(i)] == flag && !visited[al.get(now).get(i)])
				isLinked(al.get(now).get(i), flag);
		}
	}

	//2개로 나눠진 선거구의 차 
	static int sub() {
		int result = 0;
		for (int i = 1; i <= N; i++) {
			if (!check[i])
				result -= population[i];
			else
				result += population[i];
		}
		return Math.abs(result);
	}

	static void dfs(int start, int cnt, int bCnt) {
		if (cnt == bCnt) {
			int a = 0;
			int b = 0;
			visited = new boolean[N + 1];
			for (int i = 1; i <= N; i++) {
				boolean flag = false;
				if (!check[i] && !visited[i]) {
					isLinked(i, flag);
					a++;
				} else if (check[i] && !visited[i]) {
					flag = true;
					isLinked(i, flag);
					b++;
				}
			}
			//1,2번 선거구 모두 각각 연결이 되어있다면 sub수행
			if (a == 1 && b == 1) {
				min = Math.min(min, sub());
			}
			return;
		}

		//선거구 조합
		for (int i = start; i <= N; i++) {
			check[i] = true;
			dfs(i + 1, cnt + 1, bCnt);
			check[i] = false;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		population = new int[N + 1];
		check = new boolean[N + 1];

		al = new ArrayList<ArrayList<Integer>>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		al.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			al.add(new ArrayList<>());
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int near = Integer.parseInt(st.nextToken());

			for (int j = 0; j < near; j++) {
				al.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}

		for (int i = 1; i < N; i++) {
			dfs(1, 0, i);
		}
		
		//선거구를 나눌 수 없다면 -1 출력
		if (min == 987654321)
			System.out.println(-1);
		else {
			System.out.println(min);
		}
	}
}
