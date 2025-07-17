package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem3047 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        String[] order = br.readLine().split("");

        StringBuilder sb = new StringBuilder();
        int max = Math.max(a, Math.max(b, c));
        int min = Math.min(a, Math.min(b, c));
        for (String string : order) {
            switch (string) {
                case "C":
                    sb.append(max).append(" ");
                    break;
                case "B":
                    if (a != max && a != min) {
                        sb.append(a).append(" ");
                    } else if (b != max && b != min) {
                        sb.append(b).append(" ");
                    } else if (c != max && c != min) {
                        sb.append(c).append(" ");
                    }
                    break;
                case "A":
                    sb.append(min).append(" ");
                    break;
            }
        }
        System.out.println(sb);
    }
}
