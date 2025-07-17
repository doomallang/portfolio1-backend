package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem2846 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int maxDiff = 0;
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = start;

        for(int i = 1; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());

            // 시작값이 더 크거나 같으면
            if (cur > end) {
                end = cur;

            } else {
                maxDiff = Math.max(maxDiff, end - start);
                start = cur;
                end = cur;
            }
        }

        maxDiff = Math.max(maxDiff, end - start);

        System.out.println(maxDiff);
    }
}
