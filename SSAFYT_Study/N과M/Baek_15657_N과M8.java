package N과M;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Baek_15657_N과M8 {
	static int N, M;
	static int[] arr, temp;
	static boolean visited[];
	static StringBuilder sb = new StringBuilder();
	static LinkedHashSet<String> set = new LinkedHashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		temp = new int[M];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		subset(0);
		for(String s : set) {
			sb.append(s).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void subset(int cnt) {
		if(cnt == M) {
			for(int i = 0; i < M; i++) {
				sb.append(temp[i]).append(" ");
			}
			set.add(sb.toString());
			sb.delete(0, sb.length());
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(visited[i])
				continue;
			
			temp[cnt] = arr[i];
			visited[i] = true;
			subset(cnt + 1);
			visited[i] = false;
		}
	}
}