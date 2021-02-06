package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//공 모으기
public class Baek_17165 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[] ball = new char[n];
		//앞 뒤를 기준으로 r b가 있으면 서로 다른걸 만날때까지 간 후 그 이후에 동일한 색상이 몇개있는지 탐색
		//앞 뒤를 기준으로 첫공과 다르면 무조건 그 방향으로 다뺌
		String s = br.readLine();
		for(int i = 0; i < n; i++) {
			ball[i] = s.charAt(i);
		}
		
		int min = 987654321;
		char start = ball[0];
		int cnt = 0;
		//앞
		for(int i = 1; i < n; i++) {
			if(ball[i] != start) {
				for(int j = i; j < n; j++) {
					if(ball[j] == start)
						cnt++;
				}
				break;
			}
		}
		min = Math.min(min, cnt);
		
		//무조건 앞으로
		for(int i = 1; i < n ; i++) {
			if(ball[i] != start)
				cnt++;
		}
		min = Math.min(min, cnt);
		
		start = ball[n-1];
		cnt = 0;
		//뒤
		for(int i = n-2; i >= 0; i--) {
			if(ball[i] != start) {
				for(int j = i; j >= 0; j--) {
					if(ball[j] == start)
						cnt++;
				}
				break;
			}
		}
		min = Math.min(min, cnt);
		
		
		cnt = 0;
		//무조건 뒤로
		for(int i = n-2; i >= 0; i--) {
			if(ball[i] != start)
				cnt++;
		}
		min = Math.min(min, cnt);
		
		System.out.println(min);
	}
}
