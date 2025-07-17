package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem4447 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String hero = br.readLine();
            result.append(hero).append(" ").append(discrimination(hero)).append("\n");
        }

        // 최종 출력 (한 번에 출력하여 성능 향상)
        System.out.print(result);
    }

    static String discrimination(String text) {
        int gCount = 0, bCount = 0;
        text = text.toLowerCase(); // 한 번만 소문자로 변환하여 성능 최적화

        for (char ch : text.toCharArray()) {
            if (ch == 'g') gCount++;
            else if (ch == 'b') bCount++;
        }

        return gCount > bCount ? "is GOOD" :
                bCount > gCount ? "is A BADDY" :
                        "is NEUTRAL";
    }
}
