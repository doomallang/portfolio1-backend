package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Problem5704 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            String s = br.readLine();
            if(s.equals("*")) {
                break;
            }

            sb.append(isFangram(s) ? "Y" : "N").append("\n");
        }
        System.out.println(sb);
    }

    static boolean isFangram(String s) {
        Set<Character> set = new HashSet<>();

        for(char c : s.toCharArray()) {
            if(Character.isLetter(c)) {
                set.add(Character.toLowerCase(c));
            }
        }
        return set.size() == 26;
    }
}
