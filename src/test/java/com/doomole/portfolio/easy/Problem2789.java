package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Problem2789 {
    public static void main(String[] args) throws Exception {
        List<String> list = Arrays.asList("CAMBRIDGE".split(""));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            if(!list.contains(Character.toString(str.charAt(i)))) {
                sb.append(str.charAt(i));
            }
        }

        System.out.println(sb);
    }
}
