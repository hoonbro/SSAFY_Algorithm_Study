# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/10.svg" width="30"> [2583] 영역구하기
> https://www.acmicpc.net/problem/2583
## 알고리즘 분류
> 너비우선 탐색

## 코드
```java
package _202308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_2583_영역구하기 {
    static int N, M, K;
    static int[] X = {0, 1, 0, -1};
    static int[] Y = {-1, 0, 1, 0};
    static boolean[][] map;
    static List<Integer> al;
    static Queue<Pos> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        al = new ArrayList<>();
        q = new LinkedList<>();

        while(K-- > 0){
            st = new StringTokenizer(br.readLine());

            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            for(int i = x1; i < x2; i++){
                for(int j = y1; j < y2; j++){
                   map[i][j] = true;
                }
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j])
                    continue;

                al.add(bfs(i, j));
            }
        }

        Collections.sort(al);
        sb.append(al.size()).append("\n");

        for(int size : al){
            sb.append(size).append(" ");
        }

        System.out.println(sb);
    }

    static int bfs(int x, int y){
        int size = 1;
        map[x][y] = true;

        q.offer(new Pos(x, y));

        while(!q.isEmpty()){
            Pos p = q.poll();

            for(int i = 0; i <4; i++){
                int nx = p.x + X[i];
                int ny = p.y + Y[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny]){
                    continue;
                }

                map[nx][ny] = true;
                size++;
                q.offer(new Pos(nx, ny));
            }
        }

        return size;
    }

    static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

```

