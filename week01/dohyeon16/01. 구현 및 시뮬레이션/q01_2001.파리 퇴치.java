/*
   문제1 : <SWEA> - 2001. 파리 퇴치
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

            // N: 전체 배열 크기, M: 파리채 크기
            int N = sc.nextInt();
            int M = sc.nextInt();

            // 각 위치의 파리 수를 저장할 N x N 크기의 2차원 배열 생성
            int[][] flies = new int[N][N];

            // 각 칸의 파리 수 입력 및 저장
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    flies[i][j] = sc.nextInt();
                }
            }

            // 한 번의 공격으로 잡을 수 있는 최대 파리 수 저장
            int max = 0;

            // M x M 크기의 파리채를 놓을 수 있는 모든 시작 위치 탐색
            for (int i = 0; i <= N - M; i++) {
                for (int j = 0; j <= N - M; j++) {

                    // 현재 위치에서 잡히는 파리 수 합계
                    int sum = 0;

                    // 현재 시작 위치 기준 M x M 영역의 파리 수 합산
                    for (int row = i; row < i + M; row++) {
                        for (int col = j; col < j + M; col++) {
                            sum += flies[row][col];
                        }
                    }

                    // 현재 합계와 기존 최대값 비교 후 최대값 갱신
                    if (sum > max) {
                        max = sum;
                    }
                }
            }

            // 테스트 케이스 번호와 최대 파리 수 출력
            System.out.println("#" + test_case + " " + max);
        }

        // Scanner 종료
        sc.close();
    }
}