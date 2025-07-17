package com.doomole.portfolio.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Gifticon {
    static List<Integer> A = new ArrayList<>();
    static List<Integer> B = new ArrayList<>();

    static int date = 0;

    static int tempA;
    static int tempB;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            B.add(Integer.parseInt(st.nextToken()));
        }

        int i = 0;
        while(!B.isEmpty()) {
            sortList();

            tempA = A.get(i);
            tempB = B.get(i);

            if(tempB > tempA) {
                int x = 1;
                x += (tempB - tempA) / 30;
                A.set(i, tempA + (30 * x));
                date += x;
                tempA = A.get(i);
            }
            for(int j = i + 1; j < B.size(); j++) {
                addCount(j);
            }
            A.remove(i);
            B.remove(i);
        }

        System.out.println(date);
    }

    static void addCount(int i) {
        int exA = A.get(i);
        int exB = B.get(i);
        if(exB > exA || exA < tempA) {
            A.set(i, exA + 30);
            date++;
            addCount(i);
        }
    }

    static void sortList() {
        // 인덱스와 값 쌍을 리스트로 생성
        List<int[]> pairList = new ArrayList<>();
        for (int i = 0; i < B.size(); i++) {
            pairList.add(new int[]{B.get(i), A.get(i)});
        }

        // B를 기준으로 오름차순 정렬
        pairList.sort((o1, o2) -> {
            if (o1[0] != o2[0]) {
                return Integer.compare(o1[0], o2[0]);  // B 기준 정렬
            } else {
                return Integer.compare(o1[1], o2[1]);  // A 기준 정렬
            }
        });

        // 정렬된 결과를 A와 B에 반영
        for (int i = 0; i < pairList.size(); i++) {
            B.set(i, pairList.get(i)[0]);
            A.set(i, pairList.get(i)[1]);
        }
    }
}
