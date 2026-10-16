import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        
        int answer = 0;
        // 1. 최소힙 초기화
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        // 2. 스코빌 지수 모두 최소힙에 추가
        for (int s : scoville) { 
            minHeap.add((long) s);
        }
        
        while (minHeap.peek() < K && minHeap.size() >= 2) {
            // 3. 가장 작은 스코빌 지수 확인
            long s1 = minHeap.poll();
            long s2 = minHeap.poll();
            // 4. 섞어서 추가
            minHeap.add(s1 + s2 * 2);
            answer++;
        }
        
        // 5. 최솟값이 K 이상인지 확인 후 정답 Return 
        if (minHeap.peek() >= K) {
            return answer;
        } else {
            return -1;
        }
    }
}