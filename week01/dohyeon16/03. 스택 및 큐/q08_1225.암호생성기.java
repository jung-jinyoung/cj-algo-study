/*
   문제 유형: 큐(Queue)
   문제8 : SWEA 1225 - 암호생성기
   난이도: D3
*/

import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        // 표준 입력을 위한 Scanner 객체 생성
        Scanner sc = new Scanner(System.in);
        // 총 10개의 테스트 케이스 순차 처리
        for (int test_case = 1; test_case <= 10; test_case++) {
            // 현재 테스트 케이스 번호 입력
            int caseNum = sc.nextInt();
            // 8개의 숫자 저장을 위한 큐 생성
            Queue<Integer> queue = new ArrayDeque<>();
            // 초기 8개 숫자 입력 및 큐 저장
            for (int i = 0; i < 8; i++) {
                queue.offer(sc.nextInt());
            }
            // 암호 생성 종료 여부 저장
            boolean finished = false;
            // 0 이하의 숫자 발생 전까지 사이클 반복
            while (!finished) {
                // 1부터 5까지 순서대로 감소
                for (int decrease = 1; decrease <= 5; decrease++) {
                    // 큐의 맨 앞 숫자 제거 후 현재 감소값 적용
                    int num = queue.poll() - decrease;
                    // 감소 결과가 0 이하인 경우 0 저장 후 암호 생성 종료
                    if (num <= 0) {
                        queue.offer(0);
                        finished = true;
                        break;
                    }
                    // 감소된 숫자를 큐의 맨 뒤에 저장
                    queue.offer(num);
                }
            }
            // 테스트 케이스 번호 출력
            System.out.print("#" + caseNum);
            // 완성된 8자리 암호 순서대로 출력
            while (!queue.isEmpty()) {
                System.out.print(" " + queue.poll());
            }
            System.out.println();
        }
        // Scanner 종료
        sc.close();
    }
}