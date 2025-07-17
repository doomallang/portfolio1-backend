package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SafetyZone {
    static int N;
    static int count;
    static int[][] map;
    static int MAX = Integer.MIN_VALUE;
    static boolean[][] visited;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int maxNum = 0;
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > maxNum) {
                    maxNum = map[i][j];
                }
            }
        }

        for(int i = 0; i < maxNum; i++) {
            visited = new boolean[N][N];
            count = 0;
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    if(map[j][k] > i && !visited[j][k]) {
                        count++;
                        visited[j][k] = true;
                        bfs(i, j, k);
                    }
                }
            }
            if(count > MAX) {
                MAX = count;
            }
        }

        System.out.println(MAX);
    }

    static void bfs(int i, int j, int k) {
        for(int l = 0; l < 4; l++) {
            int newJ = j + dx[l];
            int newK = k + dy[l];

            if(newJ >= 0 && newJ < N && newK >= 0 && newK < N && !visited[newJ][newK] && map[newJ][newK] > i) {
                visited[newJ][newK] = true;
                bfs(i, newJ, newK);
            }
        }
    }
}
