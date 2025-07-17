package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Problem2743 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] score = new int[8][2];

        for(int i = 0; i < 8; i++) {
            score[i][0] = Integer.parseInt(br.readLine());
            score[i][1] = i + 1;
        }

        Arrays.sort(score, (a, b) -> Integer.compare(a[0], b[0]));

        int sum = 0;
        int[] rank = new int[5];
        StringBuilder sb = new StringBuilder();
        for(int i = 3; i < 8; i++) {
            sum += score[i][0];
            rank[i - 3] = score[i][1];
        }
        Arrays.sort(rank);
        for(int i = 0; i < 5; i++) {
            sb.append(rank[i]).append(" ");
        }
        System.out.println(sum);
        System.out.println(sb);
    }
}
