package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BeerWalking {
    static int t;
    static int n;
    static int sx;
    static int sy;
    static int dx;
    static int dy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            List<int[]> list = new ArrayList<>();
            for (int j = 0; j < n + 2; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                // 출발지라면
                if (j == 0) {
                    sx = x;
                    sy = y;
                // 도착지라면
                } else if (j == n + 1) {
                    dx = x;
                    dy = y;
                } else {
                    list.add(new int[]{x, y});
                }
            }
            System.out.println(bfs(list) ? "happy" : "sad");
        }
    }

    static boolean bfs(List<int[]> list) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        q.add(new int[] {sx,sy});
        while(!q.isEmpty()) {
            int[] pos = q.poll();
            int px = pos[0], py = pos[1];

            // 출발지에서 바로 도착지로 갈 수 있다면
            if(Math.abs(px-dx) + Math.abs(py-dy) <= 1000) {
                return true;
            }

            for(int i=0; i<n; i++) {
                if(!visited[i]) {
                    int nx = list.get(i)[0], ny = list.get(i)[1];
                    int dis = Math.abs(px - nx) + Math.abs(py - ny);
                    if(dis <= 1000) {
                        visited[i] = true;
                        q.add(new int[]{nx,ny});
                    }
                }
            }
        }
        return false;
    }
}
