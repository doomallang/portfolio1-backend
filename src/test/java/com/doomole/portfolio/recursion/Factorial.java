package com.doomole.portfolio.recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Factorial {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        if(N == 0) {
            System.out.println(1);
        } else {
            long result = factorial(N);
            System.out.println(result);
        }
    }

    static long factorial(long n) {
        if(n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
