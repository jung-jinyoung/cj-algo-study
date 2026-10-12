/*
   문제4 : <SWEA> - 1204. 최빈수 구하기
   난이도: D2
*/

import java.util.Scanner;

class Solution {
    public static void main(String args[]) throws Exception {
        // 표준 입력을 위한 Scanner 객체 생성
        Scanner sc = new Scanner(System.in);

        // 전체 테스트 케이스 개수 입력
        int T = sc.nextInt();

        // 각 테스트 케이스 순차 처리
        for (int test_case = 1; test_case <= T; test_case++) {
            // 현재 테스트 케이스 번호 입력
            int caseNum = sc.nextInt();

            // 0점부터 100점까지 각 점수의 등장 횟수 저장
            int[] count = new int[101];

            // 학생 1000명의 점수 입력 및 빈도 계산
            for (int i = 0; i < 1000; i++) {
                int score = sc.nextInt();
                count[score]++;
            }

            // 가장 높은 등장 횟수와 최빈수 저장
            int maxCount = 0;
            int mode = 0;

            // 점수별 등장 횟수 비교 및 최빈수 탐색
            for (int score = 0; score <= 100; score++) {
                // 등장 횟수가 같을 경우 더 큰 점수 선택
                if (count[score] >= maxCount) {
                    maxCount = count[score];
                    mode = score;
                }
            }

            // 테스트 케이스 번호와 최빈수 출력
            System.out.println("#" + caseNum + " " + mode);
        }

        // Scanner 종료
        sc.close();
    }
}