package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class PointXYZ{
    int height;
    int row;
    int col;

    public PointXYZ(int height, int row, int col){
        this.height = height;
        this.row = row;
        this.col = col;
    }
}

public class Tomato {
    // 상, 하, 좌, 우, 위, 아래를 계산할 배열
    static int rowArr[] = {-1, 0, 1, 0, 0, 0};
    static int colArr[] = {0, 1, 0, -1, 0, 0};
    static int heightArr[] = {0, 0, 0, 0, 1, -1};

    static int[][][] box;

    static int M;
    static int N;
    static int H;

    static Queue<PointXYZ> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H + 1][N + 1][M + 1];

        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= M; k++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    // bfs를 시작하는 노드를 큐에 추가
                    if (box[i][j][k] == 1) queue.add(new PointXYZ(i, j, k));
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        while (!queue.isEmpty()){
            PointXYZ point = queue.poll();

            int height = point.height;
            int row = point.row;
            int col = point.col;

            for(int i = 0 ; i < 6; i++){
                int moveHeight = height + heightArr[i];
                int moveRow = row + rowArr[i];
                int moveCol = col + colArr[i];
                // 6방향으로 토마토가 익을 수 있는지 여부 확인
                if(checkPoint(moveHeight, moveRow, moveCol)){
                    // 익은 토마토를 큐에 추가
                    queue.add(new PointXYZ(moveHeight, moveRow, moveCol));
                    // 익은 토마토의 값 = 이전에 익은 토마토의 값 + 1
                    box[moveHeight][moveRow][moveCol] = box[height][row][col] + 1;
                }
            }
        }
        // 최대 값을 구하기 위한 변수
        int result = Integer.MIN_VALUE;

        for(int i = 1; i <= H; i++){
            for(int j = 1; j <= N; j++){
                for(int k = 1; k <= M; k++){
                    // 하나라도 익지 않은 토마토가 있다면 -1을 반환
                    if(box[i][j][k] == 0) {
                        return -1;
                    }
                    // 토마토가 익는데 걸리는 시간을 구함
                    result = Math.max(result, box[i][j][k]);
                }
            }
        }
        // 최대 값이 1이라면 원래부터 모두 익어있었다는 것
        if(result == 1) {
            return 0;
        // (최대 값 - 1) --> 걸린 일수
        } else {
            return (result - 1);
        }
    }

    private static boolean checkPoint(int height, int row, int col){
        // 주어진 범위 밖인지 검사
        if(height < 1 || height > H || row < 1 || row > N || col < 1 || col > M) {
            return false;
        }
        // 아직 익지 않은 토마토라면 true 반환
        if(box[height][row][col] == 0) {
            return true;
        // 이미 익어있거나 빈 자리라면 false 반한
        } else {
            return false;
        }
    }
}
