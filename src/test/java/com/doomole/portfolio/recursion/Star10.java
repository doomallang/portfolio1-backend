package com.doomole.portfolio.recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Star10 {
    static char[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        arr = new char[T][T];

        star(0, 0, T, false);

        for(int i = 0; i < T; i++) {
            for(int j = 0; j < T; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    static void star(int x, int y, int n, boolean blank) {
        //공백일 경우
        if (blank) {
            //시작 점 x, y 부터 해당 구역의 크기 만큼(N)
            for (int i = x; i < x + n; i++) {
                for (int j = y; j < y + n; j++) {
                    arr[i][j] = ' ';
                }
            }
            return;
        }

        //더이상 쪼갤 수 없을 때
        if(n <= 1){
            //더이상 쪼갤 수 없는 값은 중간 값도 구할 수 없기 때문에 무조건 별(*)
            arr[x][y] = '*';
            return;
        }

        int blockSize = n/3; //N은 3의 거듭제곱임으로 한 블록의 사이즈를 구하기 위해선  3으로 나눈다
        int startCount = 0; //별 공백 기준을 체크하는 변수, 별 구역 누적

        //구역으로 구분하는 것이므로 증가값이 블록사이즈만큼 증가해야한다.
        for(int i = x; i < x + n; i+=blockSize){
            for(int j = y; j < y + n;j+=blockSize){
                startCount++;
                //N이 3, 9, 27 .. 3의 거듭제곱일 때 9개의 구역을 나눈다.
                // => 9개의 구역으로 나눴을 때 5번째가 무조건 공백이다.
                if(startCount == 5){
                    star(i, j, blockSize, true);
                }else{
                    star(i, j, blockSize, false);
                }
            }
        }
    }
}
