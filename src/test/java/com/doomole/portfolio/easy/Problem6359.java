package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem6359 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append((int) Math.sqrt(n)).append("\n"); // 열린 방의 개수는 n의 제곱근 개수와 동일
        }

        System.out.print(sb);
    }
}
