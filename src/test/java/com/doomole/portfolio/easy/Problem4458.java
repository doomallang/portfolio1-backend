package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem4458 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            sb.append(line.substring(0, 1).toUpperCase()).append(line.substring(1)).append("\n");
        }

        System.out.println(sb);
    }
}
