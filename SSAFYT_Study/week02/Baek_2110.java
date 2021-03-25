package week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//공유기 설치
//이분 탐색문제
//참고 사이트 
//http://blog.naver.com/PostView.nhn?blogId=pjok1122&logNo=221652210187&categoryNo=0&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView
public class Baek_2110 {
	static int[] map;
	static int n, c;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 집의 개수
		n = Integer.parseInt(st.nextToken());
		// 공유기의 개수
		c = Integer.parseInt(st.nextToken());

		map = new int[n];

		int max = 0;

		for (int i = 0; i < n; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(map);

		// 오늘의 팁 ㅈㄴ 막풀면 안된다
		// 될거같다고 깝치지 않기
		System.out.println(Wifi());
	}

	static int Wifi() {
		int result = 0;
		int start = 1;
		int end = map[n - 1] - map[0]; // 최대 길이

		while (start <= end) {
			int mid = (start + end) / 2;
			int curWifi = map[0]; // 현재 공유기 설치하는 집
			int cnt = 1;

			for (int i = 0; i < n; i++) {
				int distance = map[i] - curWifi;

				if (distance >= mid) {
					cnt++;
					curWifi = map[i];
				}
			}
			if(cnt >= c) { //cnt가 주어진 갯수보다 많다면 정답일 수 있음
				result = mid;
				start = mid+1;
			}
			else // cnt가 모자라면 간격을 좁힌다
				end = mid -1;
		}
		return result;
	}
}
