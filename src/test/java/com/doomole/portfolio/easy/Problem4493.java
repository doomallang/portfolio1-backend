package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem4493 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int result = 0;

            for(int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());

                result += compare(st.nextToken(), st.nextToken());
            }
            sb.append(result > 0 ? "Player 1" : result == 0 ? "TIE" : "Player 2").append("\n");
        }
        System.out.println(sb);
    }

    static int compare(String p1, String p2) {
        if (p1.equals(p2)) return 0;
        return (p1.equals("R") && p2.equals("S")) ||
                (p1.equals("P") && p2.equals("R")) ||
                (p1.equals("S") && p2.equals("P")) ? 1 : -1;
    }
}
