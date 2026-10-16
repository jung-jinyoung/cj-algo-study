/**
 * 
 * 1. 접근 전략 
 * - 최대 힙(Max-Heap) 기반의 `PriorityQueue` 활용
 * - `Collections.reverseOrder()` 설정을 통한 내림차순(최대값 우선) 정렬 구조 적용
 * - 모든 돌의 무게를 힙에 삽입한 후, 힙의 크기가 2 이상일 동안 반복문 수행
 * - 매 라운드마다 가장 무거운 돌 두 개(`stone1`, `stone2`)를 `poll()`하여 추출
 * - 두 돌의 무게가 다를 경우, 그 차이값(`stone1 - stone2`)을 다시 힙에 삽입하여 시뮬레이션 지속
 * - 최종적으로 남은 돌이 있는 경우 무게 반환, 비어있는 경우 `0` 반환
 * 
 * 2. 복잡도 분석 
 * - 시간 복잡도: O(N log N)
 *   - N개의 돌을 힙에 넣고 빼는 연산(`add`, `poll`)이 각각 O(log N) 소요되며, 최악의 경우 모든 돌에 대해 반복 수행
 * - 공간 복잡도: O(N)
 *   - 우선순위 큐(Priority Queue)에 모든 돌의 개수(N개)만큼 공간 소모
 */


import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int lastStoneWeight(int[] stones) {
        // 1. 최대 힙 선언
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // 2. 모든 돌 힙에 삽입
        for (int stone : stones) {
            maxHeap.add(stone);
        }

        // 3. 둘이 2개 이상일 동안 부딪히기 시뮬레이션
        while(maxHeap.size() > 1) {
            int stone1 = maxHeap.poll();
            int stone2 = maxHeap.poll();

            if (stone1 != stone2) {
                maxHeap.add(stone1 - stone2);
            }
        }

        // 4. 남은 돌이 있으면 해당 무게 반환, 없으면 0 반환
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }
}