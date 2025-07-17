package com.doomole.portfolio.backTracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NAndM2 {
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        visited = new boolean[N];
        dfs(N, M, 0);
    }

    static void dfs(int N, int M, int depth) {
        if(M == depth) {
            for(int n : arr) {
                System.out.print(n + " ");
            }
            System.out.println();
            return;
        }

        for(int i = 0; i < N; i++) {
            if (!visited[i] && (depth == 0 || arr[depth - 1] < i + 1)) {
                visited[i] = true;
                arr[depth] = i + 1;
                dfs(N, M, depth + 1);
                visited[i] = false;
            }
        }
    }
}
