package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FourDice {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int result = 0;

        for(int i = 0; i < N; i++) {
            int[] dice = new int[7];
            int price = 0;
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 4; j++) {
                dice[Integer.parseInt(st.nextToken())]++;
            }

            int maxCount = 0;
            int num = 0;
            for(int k = 1; k <= 6; k++) {
                if(dice[k] > maxCount) {
                    maxCount = dice[k];
                    num = k;
                }
            }

            if(maxCount == 4) {
                price = 50000 + (num * 5000);
            } else if(maxCount == 3) {
                price = 10000 + (num * 1000);
            } else if(maxCount == 2) {
                int firstPair = 0, secondPair = 0;
                for (int k = 1; k <= 6; k++) {
                    if (dice[k] == 2) {
                        if (firstPair == 0) firstPair = k;
                        else secondPair = k;
                    }
                }
                price = (secondPair == 0) ? 1000 + (firstPair * 100) : 2000 + (firstPair + secondPair) * 500;
            } else {
                int maxNum = 0;
                for(int m = 0; m < 7; m++) {
                    if(dice[m] == 1) {
                        if(m > maxNum) {
                            maxNum = m;
                        }
                    }
                }
                price = maxNum * 100;
            }
            result = Math.max(result, price);
        }
        System.out.println(result);
    }
}
