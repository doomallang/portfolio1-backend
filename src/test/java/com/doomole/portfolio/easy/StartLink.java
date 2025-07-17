package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class StartLink {
    static int F;
    static int S;
    static int G;
    static int U;
    static int D;
    static Queue<Integer> queue = new LinkedList<>();
    static int[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        visited = new int[F + 1];
        queue.add(S);

        if(S == G) {
            System.out.println(0);
            return;
        }

        bfs();

        if(visited[G] == 0) {
            System.out.println("use the stairs");
        }
    }

    static void bfs() {
        while(!queue.isEmpty()) {
            int q = queue.poll();

            if(q == G) {
                System.out.println(visited[q]);
                return;
            }
            int up = q + U;
            int down = q - D;

            if(U > 0 && up <= F && visited[up] == 0) {
                visited[up] = visited[q] + 1;
                queue.add(up);
            }
            if(F > 0 && down > 0 && visited[down] == 0) {
                visited[down] = visited[q] + 1;
                queue.add(down);
            }
        }
    }
}
