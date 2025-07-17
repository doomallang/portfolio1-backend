package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem4504 {
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(true) {
            int num = Integer.parseInt(br.readLine());
            if(num == 0) {
                break;
            }

            sb.append(isMultiple(num)).append("\n");
        }
        System.out.println(sb);
    }

    static String isMultiple(int num) {
        if(num % n == 0) {
            return num + " is a multiple of " + n + ".";
        } else {
            return num + " is NOT a multiple of " + n + ".";
        }
    }
}
