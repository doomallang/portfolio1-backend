package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DivideEqualsNumber {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long N = Long.parseLong(br.readLine());
        long sum = 0;

        for (int i = 0; i < N; i++) {
            sum += (N + 1) * i;
        }

        System.out.println(sum);
    }
}
