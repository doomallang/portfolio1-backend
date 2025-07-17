package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EnglishStudy {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            String temp = br.readLine();
            if(temp.equals("#")) {
                break;
            }
            char c = temp.charAt(0);
            int count = 0;
            for(int i = 2; i < temp.length(); i++) {
                if(temp.toLowerCase().charAt(i) == c) {
                    count++;
                }
            }
            System.out.println(c + " " + count);
        }
    }
}
