/*
   문제 유형: 최소 힙(우선순위 큐)으로 스코빌 지수가 가장 낮은 음식 2개를 반복해서 섞어 최소 횟수 계산
   문제1 : <프로그래머스> - 더 맵게
   난이도: Level 2
*/

/*
   전략

   1. 스코빌 배열에는 각 음식의 스코빌 지수가 저장되어 있다.
   -> ex) [1, 2, 3, 9, 10, 12] = 총 6개 음식의 스코빌 지수
   2. 가장 낮은 스코빌 지수를 빠르게 꺼내기 위해 PriorityQueue를 사용한다.
   -> 기본 PriorityQueue = 작은 값부터 꺼내는 최소 힙
   3. scoville 배열의 모든 값을 PriorityQueue에 추가한다.
   -> offer() = 큐에 값 추가
   -> long = 음식을 섞은 결과가 int 범위를 초과하는 경우 방지
   4. 스코빌 지수가 K보다 낮은 음식이 있고 음식이 2개 이상이면 섞는 과정을 반복한다.
   -> peek() = 가장 낮은 스코빌 지수를 제거하지 않고 확인
   -> queue.size() >= 2 = 섞을 음식이 2개 이상 남은 상태
   5. poll()을 두 번 사용해 스코빌 지수가 가장 낮은 음식 2개를 꺼낸다.
   -> first = 가장 낮은 스코빌 지수
   -> second = 두 번째로 낮은 스코빌 지수
   6. 문제에서 주어진 계산식으로 새로운 스코빌 지수를 구한다.
   -> 새로운 스코빌 지수 = first + (second * 2)
   -> ex) 1과 2를 섞으면 1 + (2 * 2) = 5
   7. 새롭게 만든 음식의 스코빌 지수를 PriorityQueue에 추가한다.
   8. 음식을 섞은 횟수 answer를 1 증가시킨다.
   9. 모든 음식의 스코빌 지수가 K 이상이면 반복을 종료한다.
   -> 가장 낮은 값이 K 이상이면 나머지 값도 모두 K 이상
   10. 가장 낮은 스코빌 지수가 K 이상인지 최종 확인한다.
   -> 조건을 만족하면 섞은 횟수 answer 반환
   -> 음식이 부족해 조건을 만족할 수 없으면 -1 반환
*/

import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        // 작은 값이 먼저 나오는 최소 힙 생성
        PriorityQueue<Long> queue = new PriorityQueue<>();
        // 모든 음식의 스코빌 지수를 큐에 추가
        for (int value : scoville) {
            queue.offer((long) value);
        }
        // 음식을 섞은 횟수
        int answer = 0;
        // 섞을 음식이 2개 이상이고 가장 낮은 스코빌 지수가 K보다 작은 동안 반복
        while (queue.size() >= 2 && queue.peek() < K) {
            // 스코빌 지수가 가장 낮은 음식 2개 꺼내기
            long first = queue.poll();
            long second = queue.poll();
            // 새로운 음식의 스코빌 지수 계산
            long mixed = first + (second * 2);
            // 섞은 음식을 큐에 추가
            queue.offer(mixed);
            // 섞은 횟수 증가
            answer++;
        }
        // 모든 음식의 스코빌 지수가 K 이상이면 횟수 반환
        if (!queue.isEmpty() && queue.peek() >= K) {
            return answer;
        }
        // 조건을 만족할 수 없는 경우
        return -1;
    }
}