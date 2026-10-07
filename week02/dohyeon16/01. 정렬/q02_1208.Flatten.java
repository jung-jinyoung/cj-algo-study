/*
   문제 유형: 정렬 후 최솟값·최댓값을 반복적으로 사용
   문제2 : <SWEA> - 1208. Flatten
   난이도: D3
*/

/*
   전략

   1. 총 10개의 테스트 케이스를 순서대로 처리한다.
   2. 각 테스트 케이스에서 덤프할 수 있는 최대 횟수 dumpCount를 입력받는다.
   3. 가로 길이는 항상 100이므로 상자 높이 100개를 저장할 배열을 만든다.
   4. 100개의 상자 높이를 배열에 저장한다.
   5. 배열을 오름차순으로 정렬한다.
   -> 가장 낮은 높이는 배열의 첫 번째 값이 된다.
   -> 가장 높은 높이는 배열의 마지막 값이 된다.
   6. 주어진 dumpCount만큼 평탄화 작업을 반복한다.
   7. 현재 최고점과 최저점의 차이가 1 이하이면 이미 평탄화가 완료된 것이므로 반복을 종료한다.
   8. 가장 높은 곳의 상자 하나를 빼고 가장 낮은 곳에 상자 하나를 추가한다.
   -> 최고 높이는 1 감소시킨다.
   -> 최저 높이는 1 증가시킨다.
   9. 높이가 변경되었으므로 다시 오름차순으로 정렬한다.
   10. 덤프가 끝나면 최고점 - 최저점을 계산한다.
   11. "#테스트케이스번호 결과" 형식으로 출력한다.
*/

import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public static void main(String[] args) throws Exception {
        // 표준 입력을 위한 Scanner 생성
        Scanner sc = new Scanner(System.in);
        // 총 10개의 테스트 케이스 처리
        for (int test_case = 1; test_case <= 10; test_case++) {
            // 덤프 가능 횟수 입력
            int dumpCount = sc.nextInt();
            // 100개의 상자 높이를 저장할 배열 생성
            int[] boxes = new int[100];
            // 상자 높이 100개 입력
            for (int i = 0; i < 100; i++) {
                boxes[i] = sc.nextInt();
            }
            // 상자 높이를 오름차순으로 정렬
            Arrays.sort(boxes);
            // 주어진 덤프 횟수만큼 평탄화 작업 반복
            for (int dump = 0; dump < dumpCount; dump++) {
                // 최고점과 최저점 차이가 1 이하면 평탄화 완료
                if (boxes[99] - boxes[0] <= 1) {
                    break;
                }
                // 가장 높은 곳에서 상자 하나 제거
                boxes[99]--;
                // 가장 낮은 곳에 상자 하나 추가
                boxes[0]++;
                // 변경된 높이를 다시 오름차순으로 정렬
                Arrays.sort(boxes);
            }
            // 최종 최고점과 최저점의 차이 계산
            int result = boxes[99] - boxes[0];
            System.out.println("#" + test_case + " " + result);
        }
        sc.close();
    }
}