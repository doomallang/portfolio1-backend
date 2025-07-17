package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem5988 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            String S = br.readLine();
            String s = S.substring(S.length() - 1);
            sb.append(Integer.parseInt(s) % 2 == 1 ? "odd" : "even").append("\n");
        }
        System.out.println(sb);
    }
}
