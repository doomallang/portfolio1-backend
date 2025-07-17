package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem2953 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int order = 0;
        int maxScore = 0;
        for(int i = 0; i < 5; i++) {
            int sum = 0;
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++) {
                sum += Integer.parseInt(st.nextToken());
            }
            if(sum > maxScore) {
                maxScore = sum;
                order = i + 1;
            }
        }
        System.out.println(order + " " + maxScore);
    }
}
