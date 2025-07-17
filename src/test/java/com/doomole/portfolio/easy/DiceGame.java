package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DiceGame {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int price = 0;

            if (A == B && B == C) {
                price = 10000 + (A * 1000);
            } else if (A != B && B != C && A != C) {
                price = Math.max(A, Math.max(B, C)) * 100;
            } else {
                if (A == B)
                    price = 1000 + (A * 100);
                else if (B == C)
                    price = 1000 + (B * 100);
                else
                    price = 1000 + (C * 100);
            }
            if (price > max)
                max = price;
        }
        System.out.println(max);
    }
}
