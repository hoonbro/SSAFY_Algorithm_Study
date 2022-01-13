package etc._2021_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1079_마피아 {
	static int N, enjin, ans = 0;
	static int[] guilty;
	static int[][] R;
	static boolean[] isLive;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		guilty = new int[N];
		R = new int[N][N];
		isLive = new boolean[N];
		
		Arrays.fill(isLive, true);
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			guilty[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				R[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		enjin = Integer.parseInt(br.readLine());
		play(N, 0);
		System.out.println(ans);
	}

	static void play(int cnt, int day) {
		if(!isLive[enjin] || cnt == 1) {
			ans = Math.max(ans, day);
			return;
		}
		//짝수(밤)일 경우 랜덤으로 한명을 죽이다.
		if(cnt %2 == 0) {
			for(int i = 0; i < N; i++) {
				if(!isLive[i] || i == enjin)
					continue; 
				
				//guilty 바꾸기
				for(int j = 0; j < N; j++) {
					if(!isLive[j])
						continue;
					guilty[j] += R[i][j];
				}
				
				isLive[i] = false;
				play(cnt-1, day+1);
				isLive[i] = true;
				
				//guilty 복구
				for(int j = 0; j < N; j++) {
					if(!isLive[j])
						continue;
					guilty[j] -= R[i][j];
				}
			}
		}else {
			int max = 0, idx = N-1;
			
			for(int i = 0; i < N; i++) {
				if(isLive[i] && max < guilty[i]) {
					max = guilty[i];
					idx = i;
				}else if(isLive[i] && max == guilty[i]) {
					max = guilty[i];
					idx = Math.min(i, idx);
				}
			}
			
			isLive[idx] = false;
			play(cnt-1, day);
			isLive[idx] = true;
		}
	}
}
