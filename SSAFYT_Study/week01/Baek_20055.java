package week01;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_20055 {
	static int[] belt;
	static boolean isRobot[]; 
	static int N,K;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belt = new int[N*2+1];
		isRobot = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= N*2; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
			
		}
		
		int cnt = 0;
		while(true) {
			rotate();
			robot();
			cnt++;
			
			if(Kcount() >= K) {
				System.out.println(cnt);
				break;
			}
		}
	}
	
	static int Kcount() {
		int cnt = 0;
		for(int i = 1; i <= N*2; i++) {
			if(belt[i] == 0)
				cnt++;
		}
		return cnt;
	}
	
	static void robot() {
		isRobot[N] = false;
		
		for(int i = N; i >= 2; i--) {
			if(isRobot[i-1]) {
				if(isRobot[i] || belt[i] == 0)
					continue;
				isRobot[i] = true;
				isRobot[i-1] = false;
				belt[i]--;
			}
		}
		
		if(!isRobot[1] && belt[1] !=0) {
			isRobot[1] = true;
			belt[1]--;
		}
	}
	
	static void rotate() {
		int a = belt[N*2];
		for(int i = N*2; i>=2; i--) {
			belt[i] = belt[i-1];
		}
		
		for(int i = N; i >= 2; i--) {
			isRobot[i] = isRobot[i-1];
		}
		belt[1] = a;
		isRobot[1] = false;
	}
}
