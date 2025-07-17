package com.doomole.portfolio.backTracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Operator {
    static int N;
    static int[] arr;
    static int[] operator = new int[4];
    static int maxValue = Integer.MIN_VALUE;
    static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        solution(arr[0], 1);

        System.out.println(maxValue);
        System.out.println(minValue);
    }

    static void solution(int num, int index) {
        if (index == N) {
            maxValue = Math.max(maxValue, num);
            minValue = Math.min(minValue, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            // 연산자 개수가 1개 이상인 경우
            if (operator[i] > 0) {
                // 해당 연산자를 1 감소시킨다.
                operator[i]--;
                switch (i) {
                    case 0: //더하기일 경우
                        solution(num + arr[index], index + 1);
                        break;
                    case 1: //빼기일 경우
                        solution(num - arr[index], index + 1);
                        break;
                    case 2: //곱하기일 경우
                        solution(num * arr[index], index + 1);
                        break;
                    case 3: //나누기일 경우
                        solution(num / arr[index], index + 1);
                        break;
                }
                // 재귀호출이 종료되면 다시 해당 연산자 개수를 복구한다.
                operator[i]++;
            }
        }
    }
}
