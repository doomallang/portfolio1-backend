package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Easy {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder input = new StringBuilder();

        // 모든 줄을 읽을 때까지 반복
        String line;
        while ((line = br.readLine()) != null) {
            input.append(line).append("\n");  // 각 줄을 읽어와서 추가
        }

        String[] sentence = input.toString().trim().split("");

        String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        int[] alphaCount = new int[alphabet.length];
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < alphabet.length; i++) {
            int count = 0;
            for(int j = 0; j < sentence.length; j++) {
                if(sentence[j].equals(alphabet[i])) {
                    count++;
                }
            }
            alphaCount[i] = count;
            if(count > max) {
                max = count;
            }
        }

        for(int i = 0; i < alphaCount.length; i++) {
            if(alphaCount[i] == max) {
                System.out.print(alphabet[i]);
            }
        }
    }
}
