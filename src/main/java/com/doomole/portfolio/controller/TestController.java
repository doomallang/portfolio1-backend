package com.doomole.portfolio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.Queue;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public void bfs(int[][] maps, int[][] visited){
        int x = 0;
        int y = 0;
        visited[x][y] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while(!queue.isEmpty()){
            int[] current = queue.remove();
            int cX = current[0];
            int cY = current[1];
            for(int i = 0; i < 4; i++){
                int nX = cX + dx[i];
                int nY = cY + dy[i];
                if(nX < 0 || nX > maps.length-1 || nY < 0 || nY > maps[0].length-1)
                    continue;
                if(visited[nX][nY] == 0 && maps[nX][nY] == 1){
                    visited[nX][nY] = visited[cX][cY] + 1;
                    queue.add(new int[]{nX, nY});
                }
            }
        }
    }

    public int solution(int[][] board) {
        int answer = 0;

        for(int i = 0; i < board.length; i++){
            int width = 0;
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == 1){

                }
            }
        }

        return answer;
    }

    @GetMapping("/temp")
    public void test1() {
        int[][] board = {{0,1,1,1}, {1,1,1,1}, {1,1,1,1}, {0,0,1,0}};

        solution(board);
    }
}
