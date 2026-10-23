/*
   문제 유형: 최대 힙(우선순위 큐)으로 가장 무거운 돌 2개를 반복해서 꺼내고 남은 돌의 무게 계산
   문제9 : <LeetCode> - 1046. Last Stone Weight
   난이도: Easy
*/

/*
   전략

   1. stones 배열에는 각 돌의 무게가 저장되어 있다.
   -> ex) [2, 7, 4, 1, 8, 1] = 무게가 서로 다른 6개의 돌
   2. 가장 무거운 돌을 빠르게 꺼내기 위해 PriorityQueue를 사용한다.
   -> PriorityQueue = 우선순위가 높은 값을 먼저 꺼내는 자료구조
   3. Collections.reverseOrder()를 사용해 큰 값이 먼저 나오는 최대 힙으로 만든다.
   -> 기본 PriorityQueue = 작은 값부터 꺼내는 최소 힙
   -> reverseOrder() = 큰 값부터 꺼내는 최대 힙 (내림차순)
   4. stones 배열의 모든 돌을 PriorityQueue에 추가한다.
   -> offer() = 큐에 값 추가
   5. 돌이 2개 이상 남아 있는 동안 반복한다.
   -> queue.size() >= 2
   6. poll()을 두 번 사용해 가장 무거운 돌 2개를 꺼낸다.
   -> first = 가장 무거운 돌
   -> second = 두 번째로 무거운 돌
   7. 두 돌의 무게가 같으면 모두 사라지므로 아무것도 추가하지 않는다.
   -> ex) 5와 5를 부딪히면 남는 돌 없음
   8. 두 돌의 무게가 다르면 무게 차이만큼 새로운 돌을 추가한다.
   -> ex) 8과 7을 부딪히면 무게 1인 돌 추가
   -> first - second = 남은 돌의 무게
   9. 돌이 1개 이하로 남을 때까지 같은 과정을 반복한다.
   10. 큐가 비어 있으면 0을 반환하고, 돌이 남아 있으면 해당 무게를 반환한다.
*/

import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public int lastStoneWeight(int[] stones) {
        // 큰 값이 먼저 나오는 최대 힙 생성
        // 기본 PriorityQueue는 가장 작은 값을 먼저 꺼내지만,
        // Collections.reverseOrder()를 사용하면 가장 큰 값을 먼저 꺼내도록 설정 가능
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        // 모든 돌의 무게를 큐에 추가
        for (int stone : stones) {
            queue.offer(stone);
        }
        // 돌이 2개 이상 남아 있는 동안 반복
        while (queue.size() >= 2) {
            // 가장 무거운 돌 2개 꺼내기
            int first = queue.poll();
            int second = queue.poll();
            // 무게가 다르면 남은 무게만큼 새로운 돌 추가
            if (first != second) {
                queue.offer(first - second);
            }
        }
        // 남은 돌이 없으면 0 반환
        if (queue.isEmpty()) {
            return 0;
        }
        // 마지막 남은 돌의 무게 반환
        return queue.poll();
    }
}