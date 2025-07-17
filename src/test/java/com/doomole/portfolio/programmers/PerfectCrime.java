package com.doomole.portfolio.programmers;

import java.util.Arrays;

public class PerfectCrime {
    static int[] memo = new int[100];
    public static void main(String[] args) {
        bottom(5);
    }

    static int fib(int n) {
        System.out.println("n : " + n + " : " + Arrays.toString(memo));
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n]; // 이미 계산한 값이면 재사용
        return memo[n] = fib(n - 1) + fib(n - 2);
    }

    static int bottom(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
