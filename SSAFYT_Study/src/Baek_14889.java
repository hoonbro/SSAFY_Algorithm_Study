import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//스타트와 링크(백트래킹 문제)
public class Baek_14889 {
	static int n;
	static int[][] team;
	static boolean[] check;
	static int min = Integer.MAX_VALUE;

	//재귀
	static void select(int idx, int cnt) {
		//n/2명이 채워지면 시작
		if (n / 2 == cnt) {
			ArrayList<Integer> start = new ArrayList<Integer>();
			ArrayList<Integer> link = new ArrayList<Integer>();
			int start_sum = 0;
			int link_sum = 0;

			//각 팀에 n/2명씩 할당
			for (int i = 0; i < n; i++) {
				if (check[i])
					start.add(i);
				else
					link.add(i);
			}

			
			for (int i = 0; i < start.size(); i++) {
				for (int j = 0; j < start.size(); j++) {
					start_sum += team[start.get(i)][start.get(j)];
					link_sum += team[link.get(i)][link.get(j)];
				}
				
			}
			min = Math.min(min, Math.abs(start_sum - link_sum));
			return;
		}
		
		//사람 채우기
		for (int i = idx; i < n; i++) {
			//이미 사용된 사람이면 x
			if (check[i])
				continue;

			check[i] = true;
			select(i, cnt+1);
			//사용후 check false
			check[i] = false;
		}
	}
	
	//check배열과 재귀를 통해서 문제를 품
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		team = new int[n][n];
		check = new boolean[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				team[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		select(0, 0);
		System.out.println(min);
	}
}
