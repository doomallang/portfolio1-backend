package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem5613 {
    static int sum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sum = Integer.parseInt(br.readLine());
        String sign = null;
        while(true) {
            String line = br.readLine();
            if(line.equals("=")) {
                break;
            }

            if(sign == null) {
                sign = line;
            } else {
                calc(sign, Integer.parseInt(line));
                sign = null;
            }
        }
        System.out.println(sum);
    }

    static void calc(String sign, int num) {
        switch(sign) {
            case "+":
                sum += num;
                break;
            case "-":
                sum -= num;
                break;
            case "*":
                sum *= num;
                break;
            case "/":
                sum /= num;
                break;
        }
    }
}
