package com.doomole.portfolio.backTracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NQueen {
    static int N;
    static int[] arr;
    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        nQueen(0);
        System.out.println(count);
    }

    static void nQueen(int depth) {
        if(depth == N) {
            count++;
            return;
        }

        for(int i = 0; i < N; i++) {
            arr[depth] = i;

            if(possibility(depth)) {
                nQueen(depth + 1);
            }
        }
    }

    static boolean possibility(int depth) {
        for(int i = 0; i < depth; i++) {
            if(arr[depth] == arr[i]) {
                return false;
            } else if(Math.abs(depth - i) == Math.abs(arr[depth] - arr[i])) {
                return false;
            }
        }

        return true;
    }

}
