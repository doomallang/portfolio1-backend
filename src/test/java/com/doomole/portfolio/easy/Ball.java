package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ball {
    static int M;
    static int[] cup = new int[4];

    public static void main(String[] args) throws Exception {
        cup[1] = 1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int t1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());

            int temp1 = cup[t1];
            cup[t1] = cup[t2];
            cup[t2] = temp1;
        }

        for(int i = 0; i < 4; i++) {
            if(cup[i] == 1) {
                System.out.println(i);
            }
        }
    }
}
