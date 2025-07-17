package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SevenDwarf {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] height = new int[9];
        int sum = 0;
        for(int i = 0; i < 9; i++) {
            height[i] = Integer.parseInt(br.readLine());
            sum += height[i];
        }

        int[] remove = new int[2];
        outerLoop:
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (sum - (height[i] + height[j]) == 100) {
                    remove[0] = i;
                    remove[1] = j;
                    break outerLoop;  // 중첩 반복문 탈출
                }
            }
        }

        int[] result = new int[7];
        int indexInResult = 0;
        for (int i = 0; i < 9; i++) {
            if (Arrays.binarySearch(remove, i) < 0) {
                result[indexInResult++] = height[i];
            }
        }

        Arrays.sort(result);
        for(int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
