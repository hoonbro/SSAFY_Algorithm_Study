package _202308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_12886_돌그룹 {
    static int ans = 0;
    static boolean[][] visited = new boolean[1501][1501];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        if((a+b+c) % 3 == 0){
            dfs(a, b, c);
        }

        System.out.println(ans);
    }

    static void dfs(int a, int b, int c){
        if(ans == 1 || (a == b && b == c)) {
            ans = 1;
            return;
        }

        calc(a, b, c);
        calc(b, c, a);
        calc(c, a, b);
    }

    static void calc(int a, int b, int c){
        int na = Math.max(a, b) - Math.min(a, b);
        int nb = Math.min(a, b) * 2;

        if(!visited[na][nb]){
            visited[na][nb] = visited[nb][na] = true;
            dfs(na, nb, c);
        }
    }
}
