package week01;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//종이의 개수

public class Baek_1780 {
	static int[][] paper;
	static int[] cnt = new int[3];
	
	//종이가 모두 같은수로 되어있다면 그 종이를 사용한다.
	static boolean usable(int w, int h, int len) {
		for(int i = h; i < h+len; i++) {
			for(int j = w; j < w+len; j++) {
				if(paper[h][w] != paper[i][j])
					return false;
			}
		}
		return true;
	}
	
	//종이를 자르는 메서드
	static void cut(int w, int h, int len) {
		//usable이 true라면 그 종이 사용
		if(usable(w, h, len)) {
			cnt[paper[h][w]+1]++;
			return;
		}
		
		//아니라면 9개로 자르고 계속해서 반복
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				cut(w + len/3 * j, h + len/3 * i, len/3);
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		paper = new int[n][n];
		
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < n; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cut(0,0,n);
		
		for(int i = 0; i < cnt.length; i++) {
			System.out.println(cnt[i]);
		}
	}
	

}
