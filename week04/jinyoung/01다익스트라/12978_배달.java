/**
     * =====================================================================
     * [알고리즘 전략 및 복잡도 정리 / 회고]
     * =====================================================================
     * 1. 핵심 전략
     *    - 다익스트라 알고리즘 및 우선순위 큐 활용
     *    - 출발지(1번 마을)에서 각 마을까지의 최단 배달 시간 산출
     *    - 인접 리스트(List<List<int[]>>)를 통한 그래프 표현으로 효율성 극대화
     *    - 그리디 방식의 최소 비용 노드 우선 탐색
     *    - 기존 최단 거리 초과 시 탐색 생략(Pruning) 및 시간 제한(K) 조건 필터링
     * 
     * 2. 시간 복잡도: O((V + E) log V)
     *    - V: 정점(마을) 개수, E: 간선(도로) 개수
     *    - 우선순위 큐를 이용한 정점 삽입 및 추출 연산: O(V log V)
     *    - 인접 리스트를 통한 간선 순회 및 큐 갱신 연산: O(E log V)
     *    - 인접 리스트 활용으로 희소 그래프 환경에서 최적화된 성능 달성
     * 
     * 3. 공간 복잡도: O(V + E)
     *    - 인접 리스트(graph) 저장 공간: O(V + E)
     *    - 최단 거리 배열(distances) 및 우선순위 큐(pq): O(V)
     * 
     * 4. 회고
     *    - 이전 인접 행렬 방식 대비 O(V^2)에서 O((V + E) log V)로 시간 복잡도 대폭 개선
     *    - 메모리 효율성과 연산 속도가 향상된 인접 리스트 템플릿 성공적 적용
     * =====================================================================
     */

import java.util.List;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.ArrayList;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int c = r[2];
            // 양방향 간선 로드 저장 
            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }
        
        int[] distances = new int[N+1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[1] = 0 ; // 1번 마을 거리 초기화
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a,b) -> Integer.compare(a[1], b[1])
        );
        pq.offer(new int[]{1, 0});
        
        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            // 현재 노드까지 걸린 시간
            int v = curr[0];
            int t = curr[1];
            if (distances[v] < t) {
                continue;
            }
            
            for (int[] neighbor : graph.get(v)) {
                int nxv = neighbor[0];
                int nxt = neighbor[1];
                
                int newTime = t + nxt;
                if (newTime <= K && newTime < distances[nxv]){
                    distances[nxv] = newTime;
                    pq.offer(new int[]{nxv, newTime});
                }
            }
        }
        
        for (int d : distances) {
            if (d <= K) {
                answer++;
            }
        }

        return answer;
    }
}