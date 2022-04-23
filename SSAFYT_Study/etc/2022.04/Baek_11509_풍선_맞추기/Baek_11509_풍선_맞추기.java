package etc._2022_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_11509_풍선_맞추기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[1000002];
		
		int cnt = 0;
	    StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int h = Integer.parseInt(st.nextToken());
			
			arr[h]++;
			if(arr[h+1] == 0)
				cnt++;
			else
				arr[h+1]--;
		}
		
		System.out.println(cnt);
	}
}