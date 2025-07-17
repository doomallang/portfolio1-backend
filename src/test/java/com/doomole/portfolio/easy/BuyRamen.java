package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BuyRamen {
    static int[] factory;
    static int money;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        factory = new int[N + 2];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            factory[i] = Integer.parseInt(st.nextToken());
        }

        int i = 0;
        while(i < N) {
            if(factory[i] > 0) {
                int temp = factory[i];
                money += 3 * temp;
                temp = Math.min(temp, factory[i + 1]);
                money += 2 * temp;
                factory[i + 1] -= temp;
                temp = Math.min(temp, factory[i + 2] - Math.min(factory[i + 1], factory[i + 2]));
                money += 2 * temp;
                factory[i + 2] -= temp;
            }
            i++;
        }

        System.out.println(money);
    }

    // i번째 공장을 방문할 때 i+1번째 공장에서 구매할 남은 라면의 개수가
    // i+2번째 공장에서 구매해야 할 남은 라면의 개수보다 더 많다면
    // i+2번째 공장에서 라면을 구매하지 않고 다음 차례로 넘긴다.
}
