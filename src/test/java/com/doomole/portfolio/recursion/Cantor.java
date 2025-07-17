package com.doomole.portfolio.recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cantor {
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            sb = new StringBuilder();
            String temp = br.readLine();
            if(temp == null || temp.isEmpty()) {
                break;
            }

            int N = Integer.parseInt(temp);
            if(N == 0) {
                System.out.print("-");
            } else {
                int length = (int) Math.pow(3, N);
                cantor(length);
            }
            System.out.println(sb.toString());
        }
    }

    static void cantor(int n) {
        int m = n / 3;
        if(m == 1) {
            sb.append("- -");
        } else {
            cantor(m);
            for(int i = 0; i < m; i++) {
                sb.append(" ");
            }
            cantor(m);
        }
    }
}
