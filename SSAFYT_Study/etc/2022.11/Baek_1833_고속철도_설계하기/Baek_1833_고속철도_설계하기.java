package _2022_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek_1833_고속철도_설계하기 {
    static int N, ans;
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int totalCost = 0, totalCnt = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                int cost = Integer.parseInt(st.nextToken());

                if (i < j) {
                    if (cost < 0) {
                        union(i, j);
                        totalCost -= cost;
                    } else {
                        pq.offer(new Edge(i, j, cost));
                    }
                }
            }
        }


        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if (union(e.from, e.to)) {
                totalCnt++;
                totalCost += e.cost;
                sb.append(e.from).append(" ").append(e.to).append("\n");
            }
        }

        System.out.println(totalCost + " " + totalCnt);
        System.out.println(sb.toString());
    }


    static int find(int a) {
        if (a == parent[a])
            return a;

        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b)
            return false;

        parent[b] = a;
        return true;
    }

    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
