package com.doomole.portfolio.backTracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sudoku {
    static int[][] map = new int[9][9];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
    }

    static void dfs(int row, int col) {
        //해당 행의 열이 모두 채워졌을 경우 다음 행 첫번째 부터 시작한다.
        if(col == 9){
            dfs(row+1, 0);
            return;
        }

        //행과 열이 모두 채워졌을 경우 결과 스도쿠 출력
        if(row == 9){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    sb.append(map[i][j]).append(' ');
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);// 경우의 수가 많을 경우 '한 개'만 출력해야하기 때문 => 하나를 출력했다면 바로 시스템 종료
        }

        //만약 해당 위치의 값이 0(=없는 수)라면 가능한 수를 찾음
        if(map[row][col] == 0){
            for(int i = 1; i <= 9; i++){
                if(checkSameNum(row, col, i)){ //넣을 수 있는 숫자이면
                    map[row][col] = i; //숫자를 넣고
                    dfs(row, col+1); //다음 열의 수를 확인
                }
            }
            map[row][col] = 0; //다른 경우의 수를 확인하기 위해 다시 0으로 돌려놓는다.
            return;
        }

        //숫자가 존재할 경우 다음 열 검사
        dfs(row, col+1);
    }

    // 같은 열/행에 해당 숫자가 있는지 없는 지 체크하여 있으면 false, 없으면 true
    public static boolean checkSameNum(int row, int col, int num){
        /* 열에 해당 숫자가 있는지 체크 */
        for(int i = 0; i < 9; i++){
            if(map[row][i] == num){ //열을 증가시키면서 열에 존재하는지 확인
                return false;
            }
        }
        /* 행에 해당 숫자가 있는지 체크 */
        for(int i = 0; i < 9; i++) {
            if (map[i][col] == num) {
                return false;
            }
        }
        int rowLocation = (row/3)*3; //행의 첫 위치
        int colLocation = (col/3)*3; //열의 첫 위치
        /* 존재하는 값의 위치에 해당하는 3x3 행렬에 있는지 체크 */
        for(int i = rowLocation; i < rowLocation+3; i++){
            for(int j = colLocation; j < colLocation+3; j++){
                if(map[i][j] == num){
                    return false;
                }
            }
        }
        return true;
    }
}
