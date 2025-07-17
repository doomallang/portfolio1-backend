package com.doomole.portfolio.dfsBfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DfsBfs {
    static int N;
    static int M;
    static int V;
    static int count;
    static int[][] mArr;
    static boolean[] visited;
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        mArr = new int[1001][1001];
        visited = new boolean[1001];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            mArr[a][b] = mArr[b][a] = 1;
        }

        DFS(V);
        System.out.println();

        visited = new boolean[1001];
        BFS(V);
    }

    static void DFS(int start) {
        visited[start] = true;
        System.out.print(start + " ");

        if(count == N) {
            return;
        }
        count++;

        for(int i = 1; i <= N; i++) {
            if(mArr[start][i] == 1 && !visited[i]) {
                DFS(i);
            }
        }
    }

    static void BFS(int start) {
        queue.offer(start);
        visited[start] = true;
        System.out.print(start + " ");

        while(!queue.isEmpty()) {
            start = queue.poll();
            for(int i = 1; i <= N; i++) {
                if(mArr[start][i] == 1 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                    System.out.print(i + " ");
                }
            }
        }
    }
}
