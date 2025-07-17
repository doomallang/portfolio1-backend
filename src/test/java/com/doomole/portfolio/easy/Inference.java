package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Inference {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int distance = 0;
        boolean arithmetic = false;
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if(i != 0) {
                if(distance == 0) {
                    distance = arr[i] - arr[i - 1];
                } else {
                    int temp = arr[i] - arr[i - 1];
                    if(temp == distance) {
                        arithmetic = true;
                    } else {
                        distance = arr[i] / arr[i - 1];
                    }
                }
            }
        }

        if(arithmetic) {
            System.out.println(arr[N - 1] + distance);
        } else {
            System.out.println(arr[N - 1] * distance);
        }
    }
}
