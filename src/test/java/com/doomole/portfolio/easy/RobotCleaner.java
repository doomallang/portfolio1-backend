package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RobotCleaner {
    static int N;
    static int M;

    static int sx;
    static int sy;

    static int direction;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {1, 0 , -1, 0};       // 상하좌우 계산할 x좌표
    static int[] dy = {0, 1, 0, -1};        // 상하좌우 계산할 y좌표

    static int cleanCount = 0;

    static int nowX;
    static int nowY;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        direction = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cleanCount++;
        nowX = sx;
        nowY = sy;
        visited[nowX][nowY] = true;
        clean();

        System.out.println(cleanCount);
    }

    static void clean() {
        boolean notClean = false;
        for(int i = 0; i < 4; i++) {
            int nextX = dx[i] + nowX;
            int nextY = dy[i] + nowY;
            if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && map[nextX][nextY] == 0 && !visited[nextX][nextY]) {
                notClean = true;
            }
        }
        if(notClean) {
            direction = direction - 1 < 0 ? 3 : direction - 1;
            drive();
        } else {
            back();
        }
    }

    static void drive() {
        int moveX = 0;
        int moveY = 0;
        if(direction == 0) {
            moveX = nowX - 1;
            moveY = nowY;
        } else if(direction == 1) {
            moveX = nowX;
            moveY = nowY + 1;
        } else if(direction == 2) {
            moveX = nowX + 1;
            moveY = nowY;
        } else if(direction == 3) {
            moveX = nowX;
            moveY = nowY - 1;
        }
        if(moveX >= 0 && moveX < N && moveY >= 0 && moveY < M && map[moveX][moveY] == 0 && !visited[moveX][moveY]) {
            visited[moveX][moveY] = true;
            cleanCount++;
            nowX = moveX;
            nowY = moveY;
        }
        clean();
    }

    static void back() {
        int moveX = 0;
        int moveY = 0;
        if(direction == 0) {
            moveX = nowX + 1;
            moveY = nowY;
        } else if(direction == 1) {
            moveX = nowX;
            moveY = nowY - 1;
        } else if(direction == 2) {
            moveX = nowX - 1;
            moveY = nowY;
        } else if(direction == 3) {
            moveX = nowX;
            moveY = nowY + 1;
        }
        if(moveX >= 0 && moveX < N && moveY >= 0 && moveY < M && map[moveX][moveY] == 0) {
            nowX = moveX;
            nowY = moveY;
            clean();
        }
    }
}
