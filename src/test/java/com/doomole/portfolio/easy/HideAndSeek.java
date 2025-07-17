package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HideAndSeek {
    static int N;
    static int K;
    static Queue<Integer> queue = new LinkedList<>();
    static int[] check = new int[100001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        queue.add(N);
        if (N == K) {
            System.out.println(0);
        } else {
            queue.add(N);
            check[N] = 1;
            bfs();
        }
    }

    static void bfs() {
        while(!queue.isEmpty()) {
            int q = queue.poll();
            for (int i = 0; i < 3; i++) {
                int next;
                if (i == 0) {
                    next = q + 1;
                } else if (i == 1) {
                    next = q - 1;
                } else {
                    next = q * 2;
                }
                if (next == K) {
                    System.out.println(check[q]);
                    return;
                }
                if (next >= 0 && next < check.length && check[next] == 0) {
                    queue.add(next);
                    check[next] = check[q] + 1;
                }
            }
        }
    }
}
