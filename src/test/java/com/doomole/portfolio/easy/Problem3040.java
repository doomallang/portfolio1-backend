package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem3040 {
    static int[] people = new int[9];
    static int sum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 9; i++) {
            people[i] = Integer.parseInt(br.readLine());
            sum += people[i];
        }

        for (int i = 0; i < 8; i++) {
            for (int j = i+1; j < 9; j++) {
                int current = people[i] + people[j];
                //두 난쟁이의 숫자를 합에서 뺐을 때 100이 될 때
                if (sum - current == 100) {
                    for (int k = 0; k < 9; k++) {
                        if (k != i && k != j) System.out.println(people[k]);
                    }
                }
            }
        }
    }
}
