package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem3035 {
    static int ZR;
    static int ZC;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        ZR = Integer.parseInt(st.nextToken());
        ZC = Integer.parseInt(st.nextToken());

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            StringBuilder strLine = new StringBuilder();

            // 각 문자 ZC만큼 반복 (String.repeat() 대체)
            for (int j = 0; j < C; j++) {
                char ch = str.charAt(j);
                for (int k = 0; k < ZC; k++) {
                    strLine.append(ch);
                }
            }

            // 행을 ZR만큼 반복 추가
            String expandedStr = strLine.toString();
            for (int j = 0; j < ZR; j++) {
                result.append(expandedStr).append("\n");
            }
        }

        // 최종 출력 (한 번에 출력하여 성능 향상)
        System.out.print(result);
    }
}
