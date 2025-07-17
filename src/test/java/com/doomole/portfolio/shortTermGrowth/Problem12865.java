package com.doomole.portfolio.shortTermGrowth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem12865 {
    static int[] W;
    static int[] V;
    static int[][] dp;

    static int count = 0;
    static int weight = 0;
    static int value = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        W = new int[N];
        V = new int[N];

        dp = new int[N][K + 1];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getData(N - 1, K));
        for(int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
    }

    static int getData(int i, int k) {
        //물건 번호가 범위 밖일 때
        if(i < 0)
            return 0;

        if(dp[i][k] == 0){ //탐색하지 않았다면,
            //현재 물건 i를 추가로 못담는 경우(무게 초과일 경우)
            if(W[i] > k) {
                //이전 값을 넣는다.
                dp[i][k] = getData(i - 1, k);
            } else{ // 현재 물건 i을 담을 수 았는 경우
                // 이전 i값과 이전 i값에 대해 가치 값 + 현재 물건 i의 가치 값 중 큰 값을 저장
                dp[i][k] = Math.max(getData(i-1, k), getData(i-1, k-W[i]) + V[i]);
            }
        }

        return dp[i][k];
    }
}
