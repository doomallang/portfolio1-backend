package com.doomole.portfolio.easy;

import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@SpringBootTest
class PortfolioApplicationTests {
    static int[][] map;
    static boolean[] check;
    static int count;
    static int network;
    static int result = 0;

    static boolean[][] visited;

    static int[] dx = {1, 0 , -1, 0};       // 상하좌우 계산할 x좌표
    static int[] dy = {0, 1, 0, -1};        // 상하좌우 계산할 y좌표

    static int tagCount = 1;
    static int wormCount = 0;

    public static void bfs(int N, int M, int[][] map) {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];

            // 상, 하, 좌, 우를 계산해준다.
            for(int i=0; i<4; i++){
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M)
                    continue;
                if (visited[nextX][nextY] || map[nextX][nextY] == 0)
                    continue;

                queue.add(new int[] {nextX, nextY});
                map[nextX][nextY] = map[nowX][nowY] + 1;
                visited[nextX][nextY] = true;
            }
        }

        System.out.print(map[N-1][M-1]);
    }

    static void bfsCall() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");	// 문자열 분리
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = line.charAt(j) - '0';
            }
        }

        bfs(N, M, map);
    }

    static void virusCall() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        count = Integer.parseInt(br.readLine());
        network = Integer.parseInt(br.readLine());

        map = new int[count + 1][count + 1];
        check = new boolean[count + 1];

        for(int i=0; i<network; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = map[b][a] = 1;
        }

        virusDfs(1);

        System.out.println(result - 1);
    }

    static void virusDfs(int start) {
        check[start] = true;
        result++;

        for(int i = 0 ; i <= count ; i++) {
            if(map[start][i] == 1 && !check[i])
                virusDfs(i);
        }
    }

    static void tagNumber() throws Exception {
        List<Integer> result = new LinkedList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        count = Integer.parseInt(br.readLine());

        map = new int[count][count];
        visited = new boolean[count][count];
        for(int i=0; i<count; i++){
            String temp = br.readLine();
            for(int j=0; j<count; j++){
                map[i][j] = temp.charAt(j) - '0';
            }
        }

        for(int i=0; i<count; i++) {
            for(int j=0; j<count; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    tagDfs(i, j);
                    System.out.println("i : " + i + " j : " + j);
                    result.add(tagCount);
                    tagCount = 1;
                }
            }
        }

        Collections.sort(result);
        System.out.println(result.size());
        for(int r : result) {
            System.out.println(r);
        }
    }

    static void tagDfs(int i, int j) {
        visited[i][j] = true;

        for(int k=0; k<4; k++) {
            int nx = dx[k]+i;
            int ny = dy[k]+j;

            if(nx>=0 && ny>=0 && nx<count && ny<count && !visited[nx][ny] && map[nx][ny]==1) {
                tagCount++;
                tagDfs(nx,ny);
            }
        }
    }

    static void cabbage() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        count = Integer.parseInt(br.readLine());
        for(int m = 0 ; m<count; m++){
            wormCount = 0;

            st = new StringTokenizer(br.readLine(), " ");
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            map = new int[M][N];
            visited = new boolean[M][N];
            for(int i=0; i<K; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
            }

            for(int i = 0; i < M; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == 1 && !visited[i][j]) {
                        cabbageDfs(i, j);
                        wormCount++;
                    }
                }
            }

            System.out.println(wormCount);
        }
    }

    static void cabbageDfs(int i, int j) {
        visited[i][j] = true;
        for(int k=0; k<4; k++) {
            int nx = dx[k]+i;
            int ny = dy[k]+j;
            if(nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length && map[nx][ny] == 1 && !visited[nx][ny]) {
                visited[nx][ny] = true;
                cabbageDfs(nx,ny);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        cabbage();
    }
}
