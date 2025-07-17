package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem2754 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        switch(str.charAt(0)) {
            case 'A':
                switch(str.charAt(1)) {
                    case '+':
                        System.out.println(4.3);
                        break;
                    case '0':
                        System.out.println(4.0);
                        break;
                    case '-':
                        System.out.println(3.7);
                        break;
                }
                break;
            case 'B':
                switch(str.charAt(1)) {
                    case '+':
                        System.out.println(3.3);
                        break;
                    case '0':
                        System.out.println(3.0);
                        break;
                    case '-':
                        System.out.println(2.7);
                        break;
                }
                break;
            case 'C':
                switch(str.charAt(1)) {
                    case '+':
                        System.out.println(2.3);
                        break;
                    case '0':
                        System.out.println(2.0);
                        break;
                    case '-':
                        System.out.println(1.7);
                        break;
                }
                break;
            case 'D':
                switch(str.charAt(1)) {
                    case '+':
                        System.out.println(1.3);
                        break;
                    case '0':
                        System.out.println(1.0);
                        break;
                    case '-':
                        System.out.println(0.7);
                        break;
                }
                break;
            case 'F':
                System.out.println(0.0);
                break;
        }
    }
}
