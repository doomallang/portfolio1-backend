package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem2857 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String name = "FBI";
        int count = 0;
        for(int i = 1; i <= 5; i++) {
            String word = br.readLine();
            if(word.contains(name)) {
                System.out.println(i);
                count++;
            }
        }
        if(count == 0) {
            System.out.println("HE GOT AWAY!");
        }
    }
}
