/*
   문제 유형: 정렬 후 현재 값과 남은 원소 개수를 비교해 조건 만족 지점 찾기
   문제3 : <프로그래머스> - H-Index
   난이도: Level 2
*/

/*
   전략

   1. 논문의 인용 횟수가 들어있는 citations 배열을 오름차순으로 정렬한다.
   2. 정렬된 배열을 처음부터 끝까지 하나씩 확인한다.
   3. 현재 위치 i부터 마지막까지 남아 있는 논문의 개수를 구한다.
   -> 전체 논문 수 - 현재 인덱스 i
   4. 현재 논문의 인용 횟수가 남아 있는 논문 개수 이상인지 확인한다.
   5. 조건을 만족하면 현재 위치부터 뒤에 있는 모든 논문도
      해당 개수 이상 인용된 것이므로 그 개수가 H-Index가 된다.
   6. 조건을 처음 만족한 순간의 값을 바로 반환한다.
   7. 끝까지 조건을 만족하지 못하면 0을 반환한다.
*/

import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        // 인용 횟수를 오름차순으로 정렬
        Arrays.sort(citations);
        // 정렬된 배열을 처음부터 끝까지 확인
        for (int i = 0; i < citations.length; i++) {
            // 현재 위치부터 마지막까지 남아 있는 논문 개수
            int paperCount = citations.length - i;
            // 현재 논문의 인용 횟수가 남은 논문 개수 이상인지 확인
            if (citations[i] >= paperCount) {
                return paperCount;
            }
        }
        return 0;
    }
}