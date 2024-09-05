package com.doomole.portfolio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    /*
    static char[][] graph;
    static int n, m = 0;
    static boolean[][] visited;
    static Queue<int[]> queue = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0}; // 상, 하
    static int[] dy = {0, 0, -1, 1}; // 좌, 우 4방탐색
    static boolean lever = false;
     */
    static int X = 0;
    static int Y = 0;
    static int day = 0;

    public List<Integer> solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();

        String reToday = today.replaceAll("\\.", "");

        Map<String, Integer> map = new HashMap<>();
        for(String term : terms) {
            String[] t = term.split(" ");
            map.put(t[0], Integer.parseInt(t[1]));
        }

        int index = 0;
        for(String privacy : privacies) {
            String[] priv = privacy.split(" ");
            int period = map.get(priv[1]);
            if(!getTime(priv[0], period, reToday)) {
                answer.add(index + 1);
            }
            index++;
        }

        return answer;
    }

    private boolean getTime(String date, int period, String today) {
        String[] dateArr = date.split("\\.");
        int year = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]);
        int day = Integer.parseInt(dateArr[2]);
        int m = month + period;

        if(day == 1) {
            day = 28;
            m--;
        } else {
            day = day - 1;
        }

        if(m > 12) {
            m = m % 12;
            year = year + (m / 12);
        }

        System.out.println("m : " + m);
        System.out.println("year : " + year);
        String returnMonth = m < 10 ? "0" + m : m + "";
        String returnDay = day < 10 ? "0" + day : day + "";

        return Integer.parseInt(year + returnMonth + returnDay) >= Integer.parseInt(today);
    }

    private void dfs(int[][] map, boolean[][] visited, int x, int y) {
        // 머물를 수 있는 날짜 계산
        day += map[x][y];
        // 해당 위치 방문
        visited[x][y] = true;
        // 상, 하, 좌, 우
        int[] upAndDown = {1, -1, 0, 0};
        int[] leftAndRight = {0, 0, 1, -1};

        for(int i = 0; i < 4; i++) {
            // 상, 하, 좌, 우 적용
            int newX = x + upAndDown[i];
            int newY = y + leftAndRight[i];

            // 범위 체크
            if(newX < 0 || newY < 0 || newX >= X || newY >= Y) {
                continue;
            }

            // 방문 이력이 없고 섬이면
            if(!visited[newX][newY] && map[newX][newY] > 0) {
                dfs(map, visited, newX, newY);
            }
        }
    }

    @GetMapping("/temp")
    public void test1() {
        String today = "2020.01.01";
        String[] terms = {"Z 3", "D 5"};
        String[] privacies = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};
        solution(today, terms, privacies);
    }
}
