package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem2966 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Character[] S = {'A', 'B', 'C'};
        Character[] C = {'B', 'A', 'B', 'C'};
        Character[] H = {'C', 'C', 'A', 'A', 'B', 'B'};

        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();

        int sSum = 0;
        int cSum = 0;
        int hSum = 0;
        for(int i = 0; i < N; i++) {

            if(s.charAt(i) == S[i % 3]) {
                sSum++;
            }
            if(s.charAt(i) == C[i % 4]) {
                cSum++;
            }
            if(s.charAt(i) == H[i % 6]) {
                hSum++;
            }
        }

        System.out.println(Math.max(sSum, Math.max(cSum, hSum)));
        if(sSum >= cSum && sSum >= hSum) {
            System.out.println("Adrian");
        }
        if(cSum >= sSum && cSum >= hSum) {
            System.out.println("Bruno");
        }
        if(hSum >= sSum && hSum >= cSum) {
            System.out.println("Goran");
        }
    }
}
