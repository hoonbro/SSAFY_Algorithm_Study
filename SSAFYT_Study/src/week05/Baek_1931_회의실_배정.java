package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Baek_1931_회의실_배정 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int arr[][] = new int[N][2];
		
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int diff =  o1[1] - o2[1];
				if(diff == 0)
					return o1[0] - o2[0];
				return diff;
			}
		});
		
		
		int cnt = 1;
		int end = arr[0][1];
		for(int i = 1; i < N; i++) {
			if(end <= arr[i][0]) {
				cnt++;
				end = arr[i][1];
			}
		}
		System.out.println(cnt);
	}
}
