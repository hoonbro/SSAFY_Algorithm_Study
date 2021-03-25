package week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//창고 다각형
public class Baek_2304 {
	static int[] arr = new int[1001];
	static int start = Integer.MAX_VALUE;
	static int end = Integer.MIN_VALUE;
	static int max = 0;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int l = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			arr[l] = h;
			
			start = Math.min(start,  l);
			end = Math.max(end,  l);
			if(arr[max] < arr[l])
				max = l;
		}
		

		System.out.println(cal());
	}
	
	//오목하게 들어간 부분이 없는 천막을 만드므로 max좌표를 기억해두고 앞, 뒤에서 확인한다.
	static int cal() {
		int ans = 0;
		int now_val = arr[start];
		for(int i = start; i <= max; i++) {
			ans += now_val;
			
			if(now_val < arr[i+1]) {
				now_val = arr[i+1];
			}			
		}
		
		now_val = arr[end];
		for(int i = end; i >= max; i--) {	
			ans += now_val;
			
			if(now_val < arr[i-1]) {
				now_val = arr[i-1];
			}
		}
		
		return ans - arr[max];
	}
}
