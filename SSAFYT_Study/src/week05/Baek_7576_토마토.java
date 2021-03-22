package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek_7576_토마토 {
    static int green = 0;
    static int n;
    static int m;
    static int[][] box;
    static LinkedList<tomato> q;

    static class tomato {
        int x;
        int y;
        int day;

        public tomato(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }

    static void bfs() {
        int dx;
        int dy;
        int[] X = {0, 1, 0, -1};
        int[] Y = {-1, 0, 1, 0};
        while (!q.isEmpty()) {
            if(green ==0){
                System.out.println(q.pollLast().day);
                break;
            }
            tomato t = q.poll();

            for (int i = 0; i < 4; i++) {
                dx = t.x + X[i];
                dy = t.y + Y[i];

                if (dx >= n || dx < 0 || dy >= m || dy < 0 || box[dx][dy] == -1)
                    continue;

                if(box[dx][dy] ==0){
                    box[dx][dy] = t.day + 1;
                    green--;
                    q.offer(new tomato(dx, dy, t.day+1));
                }
            }


        }
        if(green>0)
            System.out.println("-1");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        box = new int[n][m];
        tomato toma;

        q = new LinkedList<tomato>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 0)
                    green++;
                else if (box[i][j] == 1) {
                    toma = new tomato(i, j, 0);
                    q.add(toma);
                }
            }
        }
        bfs();
    }
}