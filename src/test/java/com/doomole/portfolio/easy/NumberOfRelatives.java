package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumberOfRelatives {
    static int member;
    static int[][] family;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        member = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int count = Integer.parseInt(br.readLine());

        dist = new int[member + 1];
        family = new int[member + 1][member + 1];

        for(int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int C = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());

            family[C][D] = family[D][C] = 1;
        }

        dfs(A, B);

        System.out.println(dist[B] == 0 ? -1 : dist[B]);
    }

    static void dfs(int x, int y) {
        if(x == y) {
            return;
        }

        for(int i = 1; i <= member; i++) {
            if(family[x][i] == 1 && dist[i] == 0){
                dist[i] = dist[x]+1;
                dfs(i, y);
            }
        }
    }
}
