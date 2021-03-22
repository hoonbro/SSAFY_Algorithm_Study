package week01;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//직사각형으로 나누기
public class Baek_1451 {
	static int rec[][];
	static long sum[][];
	static long max = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		rec = new int[n+1][m+1];
		sum = new long[n+1][m+1];
		
		//직사각형 입력
		for(int i = 1; i <= n; i++) {
			String s = br.readLine();
			for(int j = 1; j <= m; j++) {
				rec[i][j] = s.charAt(j-1) - '0';
			}
		}
		
		//모든 직사각형의 합 구해두기
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <=m; j++) {
			sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + (long)rec[i][j];
			}
		}
		
		
		rect(n,m);
		
		System.out.println(max);
		
	}
	
	static void rect(int n, int m) {
		//모두 세로
		for (int i = 1; i <=m-2 ; i++) {
            for (int j = i+1; j <=m-1 ; j++) {
                long r1 = sum(1,1,n,i);
                long r2 = sum(1,i+1,n,j);
                long r3 = sum(1,j+1,n,m);
				max = Math.max(max, (r1*r2*r3));
			}
		}
		
		//모두 가로
		for(int i = 1; i <= n-2; i++) {
			for(int j = i+1; j <= n-1; j++) {
				long r1 = sum(1,1,i,m);
				long r2 = sum(i+1,1,j,m);
				long r3 = sum(j+1,1,n,m);
				max = Math.max(max, (r1*r2*r3));
			}
		}
		
		for(int i = 1; i <= n-1; i++) {
			for(int j = 1; j <= m-1; j++) {
				//왼쪽1 세로, 오른쪽 2가로
				long r1 = sum(1,1,n,j);
				long r2 = sum(1,j+1,i,m);
				long r3 = sum(i+1,j+1,n,m);
				max = Math.max(max, (r1*r2*r3));
				
				//왼쪽 2 가로, 오른쪽 1 세로
				r1 = sum(1,1,i,j);
				r2 = sum(i+1,1,n,j);
				r3 = sum(1,j+1,n,m);
				max = Math.max(max, (r1*r2*r3));
				
				//위1 가로, 아래 2 세로
				r1 = sum(1,1,i,m);
				r2 = sum(i+1,1,n,j);
				r3 = sum(i+1,j+1,n,m);
				max = Math.max(max, (r1*r2*r3));
				
				//위2 세로, 아래 1 가로
				r1 = sum(1,1,i,j);
				r2 = sum(1,j+1,i,m);
				r3 = sum(i+1,1,n,m);
				max = Math.max(max, (r1*r2*r3));
			}
		}
	}
	
	static long sum(int x1, int y1, int x2, int y2) {
		return sum[x2][y2] - sum[x2][y1-1] - sum[x1-1][y2] + sum[x1-1][y1-1];
	}
}
