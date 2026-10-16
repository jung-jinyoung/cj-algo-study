# 전략: 힙(우선순위 큐). 매번 '가장 무거운 돌 두 개'를 꺼내야 하므로 최대 힙을 쓴다.
#       파이썬 heapq는 가장 작은 값이 먼저 나오는 최소 힙이라, 무게에 -를 붙여 넣으면
#       가장 무거운 돌이 먼저 나온다. 두 개를 꺼내 무게가 다르면 차이를 다시 넣고, 돌이 1개 이하가 되면 끝.
# 시간복잡도: O(N log N)  — 돌을 최대 N번 꺼내고 넣는데, 힙에서 꺼내기/넣기가 각각 log N

from typing import List  

import heapq

class Solution:
    def lastStoneWeight(self, stones: List[int]) -> int:
        answer = 0
        heap = []
        for s in stones:
            heapq.heappush(heap, -s)             # 최대 힙처럼 쓰려고 음수로 넣기

        while len(heap) > 1:                     # 돌이 2개 이상 남아 있는 동안
            y = -heapq.heappop(heap)             # 가장 무거운 돌
            x = -heapq.heappop(heap)             # 두 번째로 무거운 돌
            if y != x:                           # 무게가 다르면 차이만큼 남음
                heapq.heappush(heap, -(y - x))

        if heap:                                 # 돌이 하나 남았으면 그 무게
            answer = -heap[0]
        return answer