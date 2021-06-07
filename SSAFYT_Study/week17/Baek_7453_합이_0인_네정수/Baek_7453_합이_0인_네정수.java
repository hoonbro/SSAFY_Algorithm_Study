package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_7453_합이_0인_네정수 {
	static int N;
	static int[] AB, CD;
	static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][4];
		AB = new int[N*N];
		CD = new int[N*N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 4; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int idx = 0;
		for (int i = 0; i < N; i++) {
		      for (int j = 0; j < N; j++) {
		        AB[idx] = arr[i][0] + arr[j][1];
		        CD[idx] = arr[i][2] + arr[j][3];
		        idx++;
		      }
		    }
		Arrays.sort(AB);
		Arrays.sort(CD);
		
		long ans = 0;
		int l = 0;
        int r = N*N-1;

        while(l< N*N && r>=0){
            long left = AB[l];
            long right = CD[r];

            if(left + right == 0){
                long left_count = 0;

                while(l<AB.length && AB[l]==left){
                    left_count++;
                    l++;
                }

                long right_count = 0;

                while(r >= 0 && CD[r] == right){
                    right_count++;
                    r--;
                }

                ans += right_count * left_count;

            }
            if(left + right < 0){
                l++;
            }

            if(left + right > 0){
                r--;
            }
        }
        System.out.println(ans);
	}
	
	static int upper(int[] arr, int val) {
		int left = 0;
		int right = arr.length-1;
		int mid;
		while(left < right) {
			mid = (left+right)/2;
			if(arr[mid] <= val) {
				left = mid+1;
			}
			else
				right = mid;
		}
		return left;
	}
	static int lower(int[] arr, int val) {
		int left = 0;
		int right = arr.length-1;
		int mid;
		while(left < right) {
			mid = (left+right)/2;
			
			if(arr[mid] < val) {
				left =  mid+1;
			}
			else
				right = mid;
		}
		return left;
	}
}
