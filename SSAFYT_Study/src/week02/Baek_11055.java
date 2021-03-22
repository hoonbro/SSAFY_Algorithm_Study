package week2;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//가장 큰 증가 부분 수열
public class Baek_11055 {
	static int n;
	static int[] arr;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		dp = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(dp());
	}

	// 재귀 ^^ㅣ발
//	static void dfs(int i, int now, int cost) {
//		if (i == n-1) {
//			max = Math.max(max, cost);
//			return;
//		}
//		for (int j = i + 1; j < n; j++) {
//			if (arr[j] > now) {		
//				cost += arr[j];
//				dfs(j, arr[j], cost);
//				cost -= arr[j];
//			}
//			
//			if (j == n-1) {
//				max = Math.max(max, cost);
//				return;
//			}
//	
//		}
//	}
	
	//dp씀
	static int dp() {
		int max = Integer.MIN_VALUE;
		
		//배열의 0번째부터 시작
		for (int i = 0; i < n; i++) {
			dp[i] = arr[i];
			//dp배열의 0부터 i번째까지 확인한다.
			for(int j = 0; j < i; j++) {
				//arr[i]더 크면 수열이므로 갱신
				if(arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + arr[i]);	
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			max = Math.max(max, dp[i]);
		}
		
		return max;
	}
}
