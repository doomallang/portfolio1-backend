package com.doomole.portfolio.dfsBfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MazeSearch {
    static int N;
    static int M;
    static int[][] maze;

    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, -1, 0, 1 };
    static int[][] visited;
    static Queue<int[]> queue = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new int[N][M];
        visited = new int[N][M];
        for(int i = 0; i < N; i++) {
            String[] str = br.readLine().split("");
            for(int j = 0; j < M; j++) {
                maze[i][j] = Integer.parseInt(str[j]);
            }
        }
        visited[0][0] = 1;
        find(0, 0);

        System.out.println(visited[N - 1][M - 1]);
    }

    static void find(int x, int y) {
        queue.add(new int[]{x, y});

        while(!queue.isEmpty()) {
            int[] arr = queue.poll();
            int c = arr[0];
            int d = arr[1];
            for(int i = 0; i < 4; i++) {
                int a = dx[i] + c;
                int b = dy[i] + d;
                if(a >= 0 && a < N && b >= 0 && b < M && visited[a][b] == 0 && maze[a][b] == 1) {
                    visited[a][b] = visited[c][d] + 1;
                    queue.add(new int[]{a, b});
                }
            }
        }
    }
}
