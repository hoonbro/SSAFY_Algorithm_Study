package _202308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_20207_달력 {
    static int N, ans = 0;
    static int[] depth;
    static boolean[] connected;
    static boolean[][] calender;
    static Input[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        N = Integer.parseInt(br.readLine());

        arr = new Input[N];
        connected = new boolean[366];
        calender = new boolean[N+1][366];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            arr[i] = new Input(s, e);
        }

        Arrays.sort(arr);

        for(Input input : arr){
            int depth = findDepth(input);

            for (int i = input.s; i <= input.e; i++) {
                calender[depth][i] = true;
                connected[i] = true;
            }
        }

        for(int i = 1; i <= 365; i++){
            if(!connected[i])
                continue;

            i = attach(i);
        }

        System.out.println(ans);
    }

    static int attach(int s){
        int e = s;

        for(; e <= 365; e++){
            if(!connected[e]) {
                break;
            }
        }

        e--;

        int maxDepth = 1;

        for(int i = s; i <= e; i++){
            for(int j = 0; j <= N; j++){
                if(!calender[j][i])
                    break;

                maxDepth = Integer.max(maxDepth, j+1);
            }
        }

        ans += maxDepth * (e-s+1);
        return e;
    }

    static int findDepth(Input input){
        int depth = 0;


        loop:
        while(depth <= N) {
            for (int i = input.s; i <= input.e; i++) {
                if(calender[depth][i]){
                    depth++;
                    continue loop;
                }
            }

            break;
        }

        return depth;
    }

    static class Input implements Comparable<Input>{
        int s, e;

        public Input(int s, int e){
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Input o) {
            if(this.s == o.s)
                return (o.e - o.s) - (this.e - this.s);

            return this.s - o.s;
        }
    }
}
