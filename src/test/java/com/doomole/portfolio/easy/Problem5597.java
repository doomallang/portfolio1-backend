package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem5597 {
    static int[] member = new int[31];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 28; i++) {
            member[Integer.parseInt(br.readLine())] = 1;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < member.length; i++) {
            if(member[i] == 0) {
                sb.append(i).append("\n");
            }
        }
        System.out.println(sb);
    }
}
