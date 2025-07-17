package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IceBerg {
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while(true) {
            visited = new boolean[N][M];
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0 && !visited[i][j]) {
                        count++;
                        visited[i][j] = true;
                        find(i, j);
                    }
                }
            }
            if(count > 1) {
                System.out.println(day);
                break;
            }
            day++;
            boolean isChange = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0) {
                        isChange = true;
                        melt(i, j);
                    }
                }
            }

            if(!isChange) {
                System.out.println(0);
                break;
            }
        }
    }

    static void melt(int i, int j) {
        for(int k = 0; k < 4; k++) {
            int newI = i + dx[k];
            int newJ = j + dy[k];

            if(newI >= 0 && newI < N && newJ >= 0 && newJ < M && map[newI][newJ] == 0 && map[i][j] > 0 && !visited[newI][newJ]) {
                map[i][j]--;
            }

        }
    }

    static void find(int i, int j) {
        for(int k = 0; k < 4; k++) {
            int newI = i + dx[k];
            int newJ = j + dy[k];
            if (newI >= 0 && newI < N && newJ >= 0 && newJ < M && map[newI][newJ] > 0 && map[i][j]  > 0 && !visited[newI][newJ]) {
                visited[newI][newJ] = true;
                find(newI, newJ);
            }
        }
    }
}
