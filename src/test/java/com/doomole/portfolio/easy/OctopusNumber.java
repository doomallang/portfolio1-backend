package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class OctopusNumber {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Character[] num = {'-', '\\', '(', '@', '?', '>', '&', '%', '/'};

        while(true) {
            String temp = br.readLine();
            if(temp.equals("#")) {
                break;
            }

            int sum = 0;
            int square = temp.length() - 1;
            for(int i = 0; i < temp.length(); i++) {
                char c = temp.charAt(i);
                int index = Arrays.asList(num).indexOf(c);
                if(index == 8) {
                    index = -1;
                }
                for(int j = 0; j < square; j++) {
                    index *= 8;
                }
                sum += index;
                square--;
            }

            System.out.println(sum);
        }
    }
}
