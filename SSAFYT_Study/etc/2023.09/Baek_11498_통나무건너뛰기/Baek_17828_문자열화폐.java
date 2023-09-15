package _2023_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_11498_통나무건너뛰기 {
    static int N, M;
    static int[] input, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        while(N-- > 0){
            M = Integer.parseInt(br.readLine());
            input = new int[M];
            arr = new int[M];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++){
                input[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(input);

            boolean flag = true;
            for(int i = 0, j = 0; i < M; i++){
                if(flag){
                    arr[j] = input[i];
                }else{
                    arr[M-1-j] = input[i];
                    j++;
                }

                flag = !flag;
            }

            int max = Math.abs(arr[M-1] - arr[0]);
            for(int i = 1; i < M; i++){
                max = Math.max(Math.abs(arr[i] - arr[i-1]), max);
            }
            sb.append(max).append("\n");
        }
        System.out.println(sb);
    }
}
