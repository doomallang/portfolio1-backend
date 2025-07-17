package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem4880 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        while(true) {
            st = new StringTokenizer(br.readLine());
            int a1 = Integer.parseInt(st.nextToken());
            int a2 = Integer.parseInt(st.nextToken());
            int a3 = Integer.parseInt(st.nextToken());

            if(a1 == 0 && a2 == 0 && a3 == 0) {
                break;
            }

            if(a3 - a2 == a2 - a1) {
                sb.append("AP ").append(a3 + a3 - a2).append("\n");
            } else if(a3 / a2 == a2 / a1) {
                sb.append("GP ").append(a3 * (a3 / a2)).append("\n");
            }
        }
        System.out.println(sb);
    }
}
