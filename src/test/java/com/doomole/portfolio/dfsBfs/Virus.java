package com.doomole.portfolio.dfsBfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Virus {
    static int[][] node;
    static boolean[] visited;

    static int N;
    static int M;
    static int count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        node = new int[N + 1][N + 1];
        visited = new boolean[N + 1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            node[a][b] = node[b][a] = 1;
        }
        visited[1] = true;
        find(1);
        System.out.println(count);
    }

    static void find(int start) {
        for(int i = 1; i <= N; i++) {
            if(node[start][i] == 1 && !visited[i]) {
                visited[i] = true;
                count++;
                find(i);
            }
        }
    }
}
