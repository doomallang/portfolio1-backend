package com.doomole.portfolio.recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Palindrome {
    static int count = 0;
    static int recursion(String s, int l, int r){
        count++;
        if(l >= r) {
            return 1;
        } else if(s.charAt(l) != s.charAt(r)) {
            return 0;
        } else {
            return recursion(s, l+1, r-1);
        }
    }

    static int isPalindrome(String s){
        return recursion(s, 0, s.length()-1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {
            count = 0;
            int result = isPalindrome(br.readLine());
            System.out.println(result + " " + count);
        }
    }
}
