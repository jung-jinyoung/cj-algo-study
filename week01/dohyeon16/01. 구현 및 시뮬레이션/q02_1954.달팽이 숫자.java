/*
   문제2 : <SWEA> - 1954. 달팽이 숫자
   난이도: D2
*/

import java.util.Scanner;

class Solution {

    public static void main(String args[]) throws Exception {

        // 표준 입력을 위한 Scanner 객체 생성
        Scanner sc = new Scanner(System.in);

        // 전체 테스트 케이스 개수 입력
        int T;
        T = sc.nextInt();

        // 각 테스트 케이스 순차 처리
        for (int test_case = 1; test_case <= T; test_case++) {

            // 달팽이 배열 크기 입력
            int N = sc.nextInt();
            int[][] snail = new int[N][N];

            // 오른쪽, 아래, 왼쪽, 위 방향 설정
            int[] dr = {0, 1, 0, -1};
            int[] dc = {1, 0, -1, 0};

            // 시작 위치 및 방향 설정
            int row = 0;
            int col = 0;
            int dir = 0;

            // 1부터 N x N까지 달팽이 형태로 숫자 입력
            for (int num = 1; num <= N * N; num++) {
                snail[row][col] = num;

                int nextRow = row + dr[dir];
                int nextCol = col + dc[dir];

                // 배열 범위를 벗어나거나 이미 숫자가 존재하는 경우 방향 변경
                if (nextRow < 0 || nextRow >= N ||
                    nextCol < 0 || nextCol >= N ||
                    snail[nextRow][nextCol] != 0) {

                    dir = (dir + 1) % 4;

                    nextRow = row + dr[dir];
                    nextCol = col + dc[dir];
                }

                row = nextRow;
                col = nextCol;
            }

            // 테스트 케이스 번호 출력
            System.out.println("#" + test_case);

            // 완성된 달팽이 배열 출력
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(snail[i][j] + " ");
                }
                System.out.println();
            }
        }

        // Scanner 종료
        sc.close();
    }
}