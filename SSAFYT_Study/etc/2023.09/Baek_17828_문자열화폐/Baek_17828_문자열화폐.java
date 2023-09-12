package _2023_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_17828_문자열화폐 {
    static int N, M;
    static int[] input, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if(M < N || M > N*26) {
            System.out.println("!");
            System.exit(0);
        }

        M -= N;
        char c;
        for(int i = 0; i < N; i++){
            c = 'A';
            if(M >= 25) {
                M-= 25;
                c += 25;
            }else if (M > 0 && M < 25){
                c += M;
                M = 0;
            }
            sb.append(c);
        }

        System.out.println(sb.reverse());
    }
}
