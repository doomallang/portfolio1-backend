package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem5063 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken());
            if(a < b) {
                sb.append("advertise\n");
            } else if(a == b) {
                sb.append("does not matter\n");
            } else if(a > b) {
                sb.append("do not advertise\n");
            }
        }
        System.out.println(sb);
    }
}
